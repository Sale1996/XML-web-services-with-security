package com.tim9.pkiapi.util;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyConverters {

	/************************* METODE ZA KONVERTOVANJE KLJUCEVA U STRINGOVE I OBRNUTO *****************/
	public static PublicKey loadPublicKey(String stored) throws GeneralSecurityException {
	    byte[] data = Base64.getDecoder().decode(stored);
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    return fact.generatePublic(spec);
	}


	public static String savePublicKey(PublicKey publ) throws GeneralSecurityException {
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    X509EncodedKeySpec spec = fact.getKeySpec(publ, X509EncodedKeySpec.class);
	    return Base64.getEncoder().encodeToString(spec.getEncoded());
	}
}
