package com.framework.commons.utils;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public abstract class JasyptUtils {
	private static StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
	static {
		stringEncryptor.setPassword(JasyptUtils.class.getName());
	}

	public static String encrypt(String message) {
		return stringEncryptor.encrypt(message);
	}

	public static String decrypt(String encryptedMessage) {
		return stringEncryptor.decrypt(encryptedMessage);
	}
}