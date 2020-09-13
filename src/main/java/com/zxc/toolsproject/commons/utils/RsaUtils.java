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

	public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt2BCc8GMkAiHNqAerbcK5SYKaPTgnOJwR40FaBeMGw5OuN1G/iLThL/RI6ejBTwuByzJbNnm4kQO6LdOd5mosmgA18SBwLg0umXh4yp+ZiALFWJ1HA3VHDTqNLuKthCEWYhvaX9ahGviBpNhA13l5/vYDw7A5sVFql6dDGWSPqUHnXi78pwS/sfgNyTHtMEJ4KAWn+UXI9IK3xZA8V9jHalvDYDMDmqoJ99QdfyezhVMQDdeAfWtt3gWxmr3Wz+OsSngh+2gSmUNaxfrYlydwZpCaLPq8rJB3PQpkolKuR7Owg2Hx3O97MnFwZmJW6bGL5tNqMT5QQVhVf0+P1ZOsQIDAQAB";
	public static final String PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC3YEJzwYyQCIc2oB6ttwrlJgpo9OCc4nBHjQVoF4wbDk643Ub+ItOEv9Ejp6MFPC4HLMls2ebiRA7ot053maiyaADXxIHAuDS6ZeHjKn5mIAsVYnUcDdUcNOo0u4q2EIRZiG9pf1qEa+IGk2EDXeXn+9gPDsDmxUWqXp0MZZI+pQedeLvynBL+x+A3JMe0wQngoBaf5Rcj0grfFkDxX2MdqW8NgMwOaqgn31B1/J7OFUxAN14B9a23eBbGavdbP46xKeCH7aBKZQ1rF+tiXJ3BmkJos+ryskHc9CmSiUq5Hs7CDYfHc73sycXBmYlbpsYvm02oxPlBBWFV/T4/Vk6xAgMBAAECggEBAIjMs+ZdPrp40j5LJ3fatLMMNPnlrOb8+lw2dREZd/j7LHk3Am42lutSmffqaHBQW9oYbE/bIyYrNaAgl9YbeMFXW0GcJFwcT6G6vdBVdCRFxV73q3v5ScGYLByYyDtYiROt98eRLXrabK0RJ292jjwJIj7hd8Dw7C7UFH8aCwbVme7zhllNqofQQA1CebIbMwDH9N+kyiS+86HGm8pE05CftI85Zhiuxx5ahX7GJfs/B9b4EUVUfdLUUj2PCzoyx/gCeJGcT/lQzcJ69S5dMGsINrHkIq9/QlnKlLHnCuXJ6wVJJmIOl8EbIhAUU28GtC/q7FM8KIpHlNOCA3ZvDw0CgYEA+oq1kcrjZii14SFlMT5ozZEltNTGIhRzQHM5KfqZOWTl2Lq4fVH5SJ1b1UqV22pXiaSifaC/L3vxZDROWBtsckuS+c5fa8WzmRMl3Z1Uv9WItahmEI3k3pOYRbE9fDyBYygIPgKjpJ7oU/E5a8JVtktbZW55p9sIeYdSRRMC8p8CgYEAu172IwuedBRG2cewxmK3Blonnb57jzmOra5+AkrWR9p1B0nFcmuCL/8KXWUzJDV+4QOVCvPjh+ruR0htaVuTfbDLNmXAcV08HLsimYvxxYKWp2MJ9qu7NsjL4iOeUD6P+us8zFYG3VYYw3Rn6NfMe2GKE2TDEdGka2Fn/iktDK8CgYEAv2TRehOlDfjRoK9TT4AerdNRG8hjp8s5lCWxqKkA/oBeuedCYrIJnnhfnBwDSP/+6W50l0YCZ3LmoCb5JoOkn9et6tnFuV3pamnfRmIXSQkGC8geWE0dZw8Xhz3z+4F5UldVnJGFk4+1adLgi7qEoeMFagIwO5Ou8K/4oD36xX8CgYA9GwoQgdCKMLaufWZUPItN4gb7dcyb9/xk/fwQSVG6my8BUni3CQKrHnTF4qQcEeov/dgUe1df0ykkTCWKb5gZLJgJRIPPNMXO5OSyyKXvcNgq/KyaKoVip3d+Z3lQ2CZigWcRiJiyhEqNK6Bbmk0Qi83Y50qKvCDv+BNPGySa9wKBgQDw35nt5iMfNJFkw+dCMweKAcuyavJeVCiNHPoOVGxKi0hbMPQgFTsdyl6fSHxVB4jiMYzc25R8LuigeEjTUVC8al9ivXTIHSXZxGlvAYETaMYROOfHb9PwQ/vD0gvI6EA4i/8wpWI1mxF3Czb1ZfiWPZh+u+P277XhU7SoXydMng==";

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