package com.idus.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityManager {
	
	// md5 hashing(기타)
	public static String hashByMd5(String target) {
		String md5 = target;
		for(int i = 0; i < 5; i++) {
			md5 = md5(md5);
		}
		return md5;
	}
	
	
	// sha-256 hashing(비밀번호)
	public static String hashBySha(String target) {
		String sha = target;
		for(int i = 0; i < 5; i++) {
			sha = sha(sha);
		}
		return sha;
	}
	
	
	private static String md5(String target) {
		MessageDigest digest = null;
		String md5 = "";
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(target.getBytes());
			byte[] byteData = digest.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for(int i = 0; i < byteData.length; i++) {
				stringBuffer.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			md5 = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
	
	
	private static String sha(String target) {
		MessageDigest digest = null;
		String sha = "";
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.update(target.getBytes());
			byte[] byteData = digest.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for(int i = 0; i < byteData.length; i++) {
				stringBuffer.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			sha = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sha;
	}
	
}
