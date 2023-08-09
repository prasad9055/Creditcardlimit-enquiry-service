package com.tesco.enquiry.encrypt;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;

import org.jboss.resteasy.jose.jwe.JWEBuilder;
import org.springframework.security.crypto.codec.Base64;

public class StringToEncrypt {
	
	public String encrypt(String requestData) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		
		//InputStream jks = this.getClass().getClassLoader().getResourceAsStream("hdfc.jks");
		//	KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		//	keyStore.load(jks,"hdfcpass".toCharArray());
		//	Key publicKey = keyStore.getCertificate("amazon").getPublicKey();
		//String encrypted = new JWEBuilder().contentBytes(ss.getBytes()).RSA1_5((RSAPublicKey) publicKey);
		
		InputStream jks = this.getClass().getClassLoader().getResourceAsStream("hdfc.jks");
		String encrypted = "";
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		String password = "hdfcpass";
		char[] passwd = password.toCharArray();
		keystore.load(jks, passwd);
		String alias = "hdfc";
		Key key = keystore.getKey(alias, passwd);
		if (key instanceof PrivateKey) {
			// Get certificate of public key
			Certificate cert = keystore.getCertificate(alias);
			// Get public key
			PublicKey publicKey = cert.getPublicKey();

			encrypted = new JWEBuilder().contentBytes(requestData.getBytes()).RSA1_5((RSAPublicKey) publicKey);

		
	}
	        return encrypted ;

	}}
