package com.hptsec.vulnlab.Utilities;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import android.util.Log;

/**
 * Use to generate AES Encryption Key
 * 
 * @author whitehatpanda
 * 
 */
public class MyAESAlgorithmHandler {

	static final String TAG = "Algorithm AES Control";

	static SecretKeySpec sks = null;

	public static SecretKeySpec getAESSecretKeySpec() {
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed("M2-C4s35-P@nd4S33d".getBytes());
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128, sr);
			sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
			return sks;
		} catch (Exception e) {
			Log.d(TAG, "AES secret key spec error");
			return null;
		}

	}

	public static byte[] encryptData(byte[] originByteData, SecretKeySpec sks) {
		byte[] encryptedBytes = null;
		try {
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, sks);
			encryptedBytes = c.doFinal(originByteData);
		} catch (Exception e) {
			Log.e(TAG, "AES encryption error");
		}
		return encryptedBytes.length > 0 ? encryptedBytes : null;
	}

	public static byte[] decryptData(byte[] encryptedData, SecretKeySpec sks) {
		byte[] originBytes = null;
		try {
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, sks);
			originBytes = c.doFinal(encryptedData);
		} catch (Exception e) {
			Log.e(TAG, "AES decryption error");
		}
		return originBytes.length > 0 ? originBytes : null;
	}

}
