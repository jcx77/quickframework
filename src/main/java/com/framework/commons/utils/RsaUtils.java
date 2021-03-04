package com.framework.commons.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public abstract class RsaUtils {
	private static final Logger logger = LoggerFactory.getLogger(RsaUtils.class);

	public static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI6yOxts4AKy1uswEkPxBKW5LjVMoT2w7YzLDzRu0JB7UN9kJOeu96zwnAmcuyoeqTuvIVy+13Et3QJYjBNdfKMCAwEAAQ==";
	public static final String PRIVATE_KEY = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAjrI7G2zgArLW6zASQ/EEpbkuNUyhPbDtjMsPNG7QkHtQ32Qk5673rPCcCZy7Kh6pO68hXL7XcS3dAliME118owIDAQABAkANKyWJQmLp3Nc4nRcSbgufv5ckFA5NQyao95RPompc6uBviVM4nu94nuDK+ti/hj/5fmNUUCXDZtlhu4luUTsBAiEA6XbG8GxdMUN2g1ozUDf/BnVfj2E+ItyqBw+NJfoqAtcCIQCceHR0D4utCLNDnKImGs0IX3A2cIUFBcJHXOoUaVenFQIhANFwje3XuQ7dz67st4Xqhi2cFFtDSIPr49irudts/k1HAiBCQ/U1rdu+hT++M5isQI9yq1CPjamYDe9QX7Y2vMJFXQIgNQrmzby1aILEY+apZciCZKlyDFuMDiHSXbkXekPAsS8=";

	private static final String KEY_ALGORITHM = "RSA";
	private static final int KEY_SIZE = 512;

	private static String publicKey;
	private static String privateKey;

	public static String getPublicKey() {
		return publicKey;
	}

	public static String getPrivateKey() {
		return privateKey;
	}

	static {
		initKey();
	}

	public static void initKey() {
		initKey(KEY_SIZE);
	}

	public static void initKey(int keysize) {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(keysize);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			publicKey = encodeBase64String(keyPair.getPublic().getEncoded());
			privateKey = encodeBase64String(keyPair.getPrivate().getEncoded());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static String encrypt(String data, String key) {
		byte[] result = encrypt(data.getBytes(StandardCharsets.UTF_8), decodeBase64(key));
		return encodeBase64String(result);
	}

	public static String encrypt(String data) {
		byte[] result = encrypt(data.getBytes(StandardCharsets.UTF_8), decodeBase64(PUBLIC_KEY));
		return encodeBase64String(result);
	}

	public static byte[] encrypt(byte[] data, byte[] key) {
		try {
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String decrypt(String data, String key) {
		byte[] result = decrypt(decodeBase64(data), decodeBase64(key));
		if (result == null) {
			return null;
		}
		return new String(result, StandardCharsets.UTF_8);
	}

	public static String decrypt(String data) {
		byte[] result = decrypt(decodeBase64(data), decodeBase64(PRIVATE_KEY));
		if (result == null) {
			return null;
		}
		return new String(result, StandardCharsets.UTF_8);
	}

	public static byte[] decrypt(byte[] data, byte[] key) {
		try {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	private static String encodeBase64String(byte[] data) {
		return Base64.encodeBase64String(data);
	}

	private static byte[] decodeBase64(String data) {
		return Base64.decodeBase64(data);
	}
}