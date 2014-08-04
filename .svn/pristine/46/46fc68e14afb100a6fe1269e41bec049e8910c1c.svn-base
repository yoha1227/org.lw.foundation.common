package org.hy.foundation.utils.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密常用类之 MD5 的整理
 * 
 * @date 2011-4-20
 * @author MipatchTeam#chenc
 * 
 */
public class MD5Builder {

	private String encryptFile(File file, String enc) throws Exception {
		FileInputStream fis = null;
		MessageDigest md = null;
		String strDes = null;
		try {
			md = MessageDigest.getInstance(enc);
			fis = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			byte[] bt = md.digest();

			md = MessageDigest.getInstance(enc);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (Exception e) {
			throw e;
		}
		return strDes;
	}

	private String encryptString(String strSrc, String enc)
			throws NoSuchAlgorithmException {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance(enc);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
		return strDes;
	}

	private String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public String getMD5File(File file) throws Exception {
		return this.encryptFile(file, "MD5");
	}

	public String getMD5String(String str) throws Exception {
		return this.encryptString(str, "MD5");
	}

	public String getSHA1File(File file) throws Exception {
		return this.encryptFile(file, "SHA-1");
	}

	public String getSHA1String(String str) throws Exception {
		return this.encryptString(str, "SHA-1");
	}
}