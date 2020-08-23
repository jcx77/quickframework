package com.zxc.toolsproject.commons.utils;

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

	public static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAO7D2X9C4UatG62g3j0nG3gNeSkgT0rQu4r0PUETsCRKvfaojuFCWCo9DGSzly6JL8V7GDaM3KTxBUvrsq0duAMCAwEAAQ==";
	public static final String PRIVATE_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA7sPZf0LhRq0braDePScbeA15KSBPStC7ivQ9QROwJEq99qiO4UJYKj0MZLOXLokvxXsYNozcpPEFS+uyrR24AwIDAQABAkA7ghidPUAEa3Swi52YRDz2aPU/qMvaa7eyZDk4FP4brQHoOnTRbwwRjMo/oE2W3edFuGImB0bZj9TxVARLz0YhAiEA/8OCIDStGjTEcWYC0PpFY9+Gwm/7pwvHZYPCkn/mR5kCIQDu/FIkoy5voePfmqBki0nQCrJqzVjOBOMOoh+SNwDN+wIhAO059xFOKpzbArY3qzcQW5pGMuVYHu+TAlCLRMbDcOFJAiBY9ytpIiouOnkHC5v78fJXdTP4GFufzifskaPpHXq6MwIhAK4sfGgVDIXpOecuuYaZapwlava/d3X/kUIikEEFSuA4";

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