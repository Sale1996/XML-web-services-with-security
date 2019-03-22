package com.tim9.pkiapi.certificate.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
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
import org.bouncycastle.util.encoders.Base64Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.certificate.dto.CertificateDTO;
import com.tim9.pkiapi.certificate.dto.CertificateDTOConverter;
import com.tim9.pkiapi.certificate.model.Certificate;
import com.tim9.pkiapi.certificate.model.CertificateType;
import com.tim9.pkiapi.certificate.repository.CertificateRepository;
import com.tim9.pkiapi.util.KeyConverters;
import com.tim9.pkiapi.certificate.model.Certificate;
import com.tim9.pkiapi.revokedCertificate.model.RevokedCertificate;


@Component
public class CertificateServiceImpl implements ICertificateService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
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
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
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
		    builder.addRDN(BCStyle.OU, certificateToSave.getOrganisationUnit());
		    builder.addRDN(BCStyle.C, certificateToSave.getCountry());
		    builder.addRDN(BCStyle.L, certificateToSave.getLocality());
			X500Name xname = builder.build();
			
			//4. Izvlacimo podatke o issueru  iz baze i privatni kljuc iz javaKeyStore-a i takodje pravi cert issuera!
			
			
			Optional<Certificate> issuerCertificate = certificateRepository.findById(certificateToSave.getIssuer().getId());
			
			if ( !issuerCertificate.isPresent() ) {
				
				return new CertificateDTO();
				
			}
			
				//vadimo kljuc od issuera iz key-store
			
			String oznakaSertifikataUKeyStore = issuerCertificate.get().getSerialNumber().toString();
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\batman'sLAPTOP\\git\\XML-web-services-with-security\\PKI-API\\src\\main\\java\\META-INF\\certificates\\keyStore.jks"));
			
			keyStore.load(in, "demo".toCharArray()); //postavicemo neki tezi kljuc...
			
			// TODO koristim alias i sifru root-a koji sam izgenerisao custom aplikacijom (namesti za druge kako treba)
			PrivateKey privatniKljucIssuera = (PrivateKey) keyStore.getKey(oznakaSertifikataUKeyStore, "demo".toCharArray()); //postavicemo neki tezi kljuc
			X509Certificate issuerCertificatedata = (X509Certificate) keyStore.getCertificate(oznakaSertifikataUKeyStore);
			
			//5. Pravimo potpis issuer-a
			JcaContentSignerBuilder singerBuilder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			
			singerBuilder = singerBuilder.setProvider("BC");
			
			ContentSigner contentSigner = singerBuilder.build(privatniKljucIssuera);
			
			
			//6. Instanciramo subject certificate generator
				//generisemo x500 name za issuera
			X500NameBuilder builderIssuerX500Name = new X500NameBuilder(BCStyle.INSTANCE);
			CertificateDTO issuerDTOIzBaze = certificateConverter.convertToDTO(issuerCertificate.get());
			builderIssuerX500Name.addRDN(BCStyle.CN, issuerDTOIzBaze.getCommonName());
			builderIssuerX500Name.addRDN(BCStyle.O, issuerDTOIzBaze.getOrganisation());
			builderIssuerX500Name.addRDN(BCStyle.C, issuerDTOIzBaze.getCountry());
			X500Name issuerX500Name = builderIssuerX500Name.build();
			
			
			// TODO SerialNumber generisanje
			BigInteger serialNumber = new BigInteger(32, random);
			//Datumi iz modela su pretvoreni iz LocalDate u Date!
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerX500Name,
					serialNumber,
					 Date.from(Instant.from(certificateToSave.getValidFromDate().atStartOfDay(ZoneId.of("GMT")))),
					 Date.from(Instant.from(certificateToSave.getValidToDate().atStartOfDay(ZoneId.of("GMT")))),
					xname,
					keyPairSubject.getPublic());
			
			//7. Postavljanje ekstenzija na sertifikat
			
				//7.1 AiA (Authority Information Access)
			
			AccessDescription caIssuers = new AccessDescription(AccessDescription.id_ad_caIssuers,
			        new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String("http://localhost:8080/certificates/") + issuerDTOIzBaze.getSerialNumber() + ".crt"));
			
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
			log.info(issuerCertificatedata.getSerialNumber() + "");
			log.info(issuerCertificatedata.getPublicKey().getEncoded() + "PK");
			log.info(issuerCertificatedata.getPublicKey() + "PK non-encode");
			byte[] dataHash2 = sha256.digest(issuerCertificatedata.getPublicKey().getEncoded()); // TODO PROMENI NA JAVNI KLJUC ISSUER-a
			
			AuthorityKeyIdentifier aki = new AuthorityKeyIdentifier(dataHash2);
			log.info(aki + "Ovo je AKI");
			
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
			file = new File("C:\\Users\\batman'sLAPTOP\\git\\XML-web-services-with-security\\PKI-API\\src\\main\\java\\META-INF\\certificates\\"+ serialNumber +".crt");
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
						
//			keyStore.load(null, "demo".toCharArray());
			BufferedInputStream ksIn = new BufferedInputStream(new FileInputStream("C:\\Users\\batman'sLAPTOP\\git\\XML-web-services-with-security\\PKI-API\\src\\main\\java\\META-INF\\certificates\\keyStore2.jks"));
			keyStore.load(ksIn, "demo".toCharArray());
			keyStore.setKeyEntry(finalCertificate.getSerialNumber().toString(), (Key)keyPairSubject.getPrivate(),"demo".toCharArray(), new java.security.cert.Certificate[] {finalCertificate});
			keyStore.store(new FileOutputStream("C:\\Users\\batman'sLAPTOP\\git\\XML-web-services-with-security\\PKI-API\\src\\main\\java\\META-INF\\certificates\\keyStore2.jks"), "demo".toCharArray());
						
			//12. Cuvanje na bazu sertifikata
			
			//postavljanje public key-a
			certificateToSave.setPublicKey(finalCertificate.getPublicKey().toString());
			// TODO postavi sigurno pogresan ID, jer ce da pukne zbog LOSE implementacije konvertora...
			certificateToSave.setId(new Long(-1));
			certificateToSave.setSerialNumber(serialNumber.toString());
			
			Certificate databaseCertificate = certificateConverter.convertFromDTO(certificateToSave);
			
			certificateRepository.save(databaseCertificate);
			certificateToSave.setId((databaseCertificate.getId()));
			
			return certificateToSave;
			
			
			
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
	public CertificateDTO revoke(String serialNumber, String reason) {
		
		Optional<Certificate> certificateForRevoke = certificateRepository.findBySerialNumber(serialNumber);
		
		if(certificateForRevoke.isPresent()) {
			RevokedCertificate revokedCertificate = new RevokedCertificate();
			
			revokedCertificate.setSerialNumber(serialNumber);
			revokedCertificate.setReason(reason);
			
			certificateForRevoke.get().setActive(false);
			
			certificateRepository.save(certificateForRevoke.get());
			
			return certificateConverter.convertToDTO(certificateForRevoke.get());
			
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertificateDTO> findAllIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
