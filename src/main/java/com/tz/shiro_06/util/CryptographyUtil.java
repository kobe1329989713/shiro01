package com.tz.shiro_06.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
/**
 * shiro 加密。
 * @author lu
 *
 */
public class CryptographyUtil {

	/**
	 * base64加密
	 * @param str
	 * @return
	 */
	public static String encBase64(String str){
		return Base64.encodeToString(str.getBytes());
	}
	
	/**
	 * base64解密
	 * @param str
	 * @return
	 */
	public static String decBase64(String str){
		return Base64.decodeToString(str);
	}
	
	/**
	 * Md5加密，这个是没有解密的。
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="123456";
		System.out.println("Base64加密："+CryptographyUtil.encBase64(password));
		System.out.println("Base64解密："+CryptographyUtil.decBase64(CryptographyUtil.encBase64(password)));
		
		
		// 注册时，加密存，后面加的哪个，可以配置到配置文件里面去。
		System.out.println("Md5加密："+CryptographyUtil.md5(password, "java1234"));
	}
	
	
	
	
}
