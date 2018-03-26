package com.hanson.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class Md5Util {

	/**
	 * 利用MD5进行加密
	 * @param str 待加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException 没有这种产生消息摘要的算法
	 * @throws UnsupportedEncodingException
	 */
	public static String encoderByMd5(String str) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
//		BASE64Encoder base64en = new BASE64Encoder();
//		// 加密后的字符串
//		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		md5.update(str.getBytes());
		byte b[] = md5.digest(); 
		int i; 
		StringBuffer buf = new StringBuffer(""); 
		for (int offset = 0; offset < b.length; offset++) { 
		i = b[offset]; 
		if(i<0) i+= 256; 
		if(i<16) 
		buf.append("0"); 
		buf.append(Integer.toHexString(i)); 
		} 
		return buf.toString();
	}
	
	public static void main(String[] args) {
		try {
			String str = Md5Util.encoderByMd5("13504018400");
			System.out.println(str);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void j(){
		
			System.out.println(Md5Util.getMd5("81e6c005a1613b39196be75e1fd3643c"));
		
	}
	
	public static String getMd5(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
}
	
	
	   /**判断用户密码是否正确 
	  * @param newpasswd   用户输入的密码 
	  * @param oldpasswd   数据库中存储的密码－－用户密码的摘要 
	  * @return 
	  * @throws NoSuchAlgorithmException 
	  * @throws UnsupportedEncodingException 
	 */ 
	public static boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{ 
		if(encoderByMd5(newpasswd).equals(oldpasswd)) 
			return true; 
		else 
			return false; 
	} 
}
