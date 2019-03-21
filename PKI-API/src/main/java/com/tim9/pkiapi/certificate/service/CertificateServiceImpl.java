package com.tim9.pkiapi.certificate.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.certificate.dto.CertificateDTO;
import com.tim9.pkiapi.certificate.dto.CertificateDTOConverter;
import com.tim9.pkiapi.certificate.model.Certificate;
import com.tim9.pkiapi.certificate.model.CertificateType;
import com.tim9.pkiapi.certificate.repository.CertificateRepository;
import com.tim9.pkiapi.certificate.model.Certificate;

@Component
public class CertificateServiceImpl implements ICertificateService {

	@Autowired
	CertificateRepository certificateRepository;
	
	@Autowired
	CertificateDTOConverter certificateConverter;
	
	
	KeyStore keyStore;
	
	@Override
	public CertificateDTO findOneById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CertificateDTO findOneBySerialNumber(String serialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertificateDTO> findAll() {
		List<CertificateDTO> certs = new ArrayList<CertificateDTO>();
		
		for (Certificate c  : certificateRepository.findAll()) {
			CertificateDTO cert = certificateConverter.convertToDTO(c);
			certs.add(cert);
 		}
		return certs;
	}

	@Override
	public CertificateDTO save(CertificateDTO certificateToSave) {
		try {
			//1. Instanciramo JKS keystore objekat od SUN provajdera
			this.keyStore = KeyStore.getInstance("JKS","SUN");
			
			//2. Generisemo par kljuceva za nas novi sertifikat
			Integer duzinaKljuca = 2048; //ovde ce ici duzina iz DTO koju ce Administrator odabrati
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(duzinaKljuca, random);
			KeyPair keyPairSubject = keyGen.generateKeyPair();
			
			//3.Generisemo X500Name za nas sertifikat
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, certificateToSave.getCommonName());
		    builder.addRDN(BCStyle.O, certificateToSave.getOrganisation());
		    builder.addRDN(BCStyle.C, certificateToSave.getCountry());
			X500Name xname = builder.build();
			
			//4. Izvlacimo podatke o issueru  iz baze i privatni kljuc iz javaKeyStore-a i takodje pravi cert issuera!
			
			
			Optional<Certificate> issuerCertificate = certificateRepository.findById(certificateToSave.getIssuer().getId());
			
			if ( !issuerCertificate.isPresent() ) {
				
				return new CertificateDTO();
				
			}
			
				//vadimo kljuc od issuera iz key-store
			
			String oznakaSertifikataUKeyStore = issuerCertificate.get().getSerialNumber().toString();
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream("classpath:files/rootKeyStore.jks"));
			
			keyStore.load(in, "kljuc".toCharArray()); //postavicemo neki tezi kljuc...
			
			PrivateKey privatniKljucIssuera = (PrivateKey) keyStore.getKey(oznakaSertifikataUKeyStore, "kljuc".toCharArray()); //postavicemo neki tezi kljuc
			
			X509Certificate issuerCertificatedata = (X509Certificate) keyStore.getCertificate(oznakaSertifikataUKeyStore);
			
			//5. Pravimo potpis issuer-a
			JcaContentSignerBuilder singerBuilder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			
			singerBuilder = singerBuilder.setProvider("BC");
			
			ContentSigner contentSigner = singerBuilder.build(privatniKljucIssuera);
			
			
			//6. Instanciramo subject certificate generator
				//generisemo x500 name za issuera
			X500NameBuilder builderIssuerX500Name = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, certificateToSave.getIssuer().getCN());
		    builder.addRDN(BCStyle.O, certificateToSave.getIssuer().getO());
		    builder.addRDN(BCStyle.C, certificateToSave.getIssuer().getC());
			X500Name issuerX500Name = builderIssuerX500Name.build();
			
			//Datumi iz modela su pretvoreni iz LocalDate u Date!
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerX500Name,
					new BigInteger(certificateToSave.getSerialNumber()),
					 Date.from(Instant.from(certificateToSave.getValidFromDate().atStartOfDay(ZoneId.of("GMT")))),
					 Date.from(Instant.from(certificateToSave.getValidToDate().atStartOfDay(ZoneId.of("GMT")))),
					xname,
					keyPairSubject.getPublic());
			
			//7. Postavljanje ekstenzija na sertifikat
			
				//7.1 AiA (Authority Information Access)
			
			AccessDescription caIssuers = new AccessDescription(AccessDescription.id_ad_caIssuers,
			        new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String("http://localhost:8080/certificates/") + certificateToSave.getSerialNumber() + ".crt"));
			
			AccessDescription ocsp = new AccessDescription(AccessDescription.id_ad_ocsp,
			        new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String("http://localhost:8080/pki/ocsp")));
			 
			ASN1EncodableVector aia_ASN = new ASN1EncodableVector();
			aia_ASN.add(caIssuers);
			aia_ASN.add(ocsp);
			
			certGen.addExtension(Extension.authorityInfoAccess, false, new DERSequence(aia_ASN));
			
				//7.2 Podesavanje tipa sertifikata
			if(certificateToSave.getType().equals(CertificateType.INTERMEDIATE) || certificateToSave.getType().equals(CertificateType.ROOT)) {
				
				certGen.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));  // CA i dubina 0 if 0, if TRUE then NONE
				
			}
			else {
				certGen.addExtension(Extension.basicConstraints, true, new BasicConstraints(false));  // FINAL certificate

			}
			
				//7.3 Subject Key Identifier (SKI)
			
			MessageDigest sha256 = MessageDigest.getInstance("SHA-1");
			
			byte[] dataHash1 = sha256.digest(keyPairSubject.getPublic().getEncoded());
			
			SubjectKeyIdentifier ski = new SubjectKeyIdentifier(dataHash1);
			
			certGen.addExtension(Extension.subjectKeyIdentifier, false, ski);
			
				//7.4 Authority Key Identifier
			
			byte[] dataHash2 = sha256.digest(issuerCertificatedata.getPublicKey().getEncoded()); // TODO PROMENI NA JAVNI KLJUC ISSUER-a
			
			AuthorityKeyIdentifier aki = new AuthorityKeyIdentifier(dataHash2);
			
			certGen.addExtension(Extension.authorityKeyIdentifier, false, aki);
			
				//7.5 Key Usage
			if(certificateToSave.getType().equals(CertificateType.INTERMEDIATE) || certificateToSave.getType().equals(CertificateType.ROOT)) {
			
				certGen.addExtension(Extension.keyUsage, false, new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyCertSign));

			}
			else {
			
				certGen.addExtension(Extension.keyUsage, false, new KeyUsage(KeyUsage.digitalSignature));
	
			}
			
			
				//7.6 Extended Key Usage			 
			
			KeyPurposeId[] EKU = new KeyPurposeId[2];
			
			EKU[0] = KeyPurposeId.id_kp_OCSPSigning;
			EKU[1] = KeyPurposeId.id_kp_serverAuth;
			 
			certGen.addExtension(Extension.extendedKeyUsage, false, new ExtendedKeyUsage(EKU));
			
			
			//8. Povezujemo sertifikat i potpis
			
			X509CertificateHolder certHolder = certGen.build(contentSigner);
			 
			//9. Pravimo konacnu verziju sertifikata
			
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			
			certConverter = certConverter.setProvider("BC");

			//Konvertuje objekat u sertifikat
			X509Certificate finalCertificate = certConverter.getCertificate(certHolder);
			
			
			//10. Cuvanje sertifikata na fajl sistem (ili server jajajajaja)
			
			FileOutputStream fop = null;

			File file;

			file = new File("classpath:/META-INF/certificates/"+ finalCertificate.getSerialNumber() +".crt");
			fop = new FileOutputStream(file);

			// if file does'nt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = finalCertificate.getEncoded();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			
			//11. Cuvanje privatnog kljuca na key store
						
			keyStore.load(null, "kljuc".toCharArray());
		
			keyStore.setKeyEntry(finalCertificate.getSerialNumber().toString(), (Key)keyPairSubject.getPrivate(),"kljuc".toCharArray(), new java.security.cert.Certificate[] {finalCertificate});
			
			keyStore.store(new FileOutputStream("C:\\Users\\Admin\\Desktop\\XML_bezbednost_projekat\\rootKeyStore.jks"), "kljuc".toCharArray());
						
			//12. Cuvanje na bazu sertifikata
			
				//postavljanje public key-a
			certificateToSave.setPublicKey(finalCertificate.getPublicKey().toString());
			
			Certificate databaseCertificate = certificateConverter.convertFromDTO(certificateToSave);
			
			certificateRepository.save(databaseCertificate);
			
			
			
			
		} catch (KeyStoreException | NoSuchProviderException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException | OperatorCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

	@Override
	public CertificateDTO deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CertificateDTO revoke(String serialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertificateDTO> findAllIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
