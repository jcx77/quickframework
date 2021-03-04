package com.framework.commons.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public abstract class JwtUtils {

	public static String sign(String subject, String secret) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create().withSubject(subject).sign(algorithm);
	}

	public static String sign(String subject, String secret, long time) {
		Date expiresAt = new Date(System.currentTimeMillis() + time);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create().withSubject(subject).withExpiresAt(expiresAt).sign(algorithm);
	}

	public static boolean verify(String token, String subject, String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withSubject(subject).build();
			verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getSubject(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getSubject();
		} catch (Exception e) {
			return null;
		}
	}
}