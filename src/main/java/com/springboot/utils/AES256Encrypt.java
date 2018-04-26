package com.springboot.utils;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
public class AES256Encrypt {
	/**
	 * 密钥算法 java6支持56位密钥，bouncycastle支持64位
	 * */
	public static final String KEY_ALGORITHM = "AES";

	/**
	 * 加密/解密算法/工作模式/填充方式
	 * 
	 * JAVA6 支持PKCS5PADDING填充方式 Bouncy castle支持PKCS7Padding填充方式
	 * */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

	/**
	 * 
	 * 生成密钥，java6只支持56位密钥，bouncycastle支持64位密钥
	 * 
	 * @return byte[] 二进制密钥
	 * */
	public static byte[] initkey() throws Exception {

		// //实例化密钥生成器
		// Security.addProvider(new
		// org.bouncycastle.jce.provider.BouncyCastleProvider());
		// KeyGenerator kg=KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
		// //初始化密钥生成器，AES要求密钥长度为128位、192位、256位
		// // kg.init(256);
		// kg.init(128);
		// //生成密钥
		// SecretKey secretKey=kg.generateKey();
		// //获取二进制密钥编码形式
		// return secretKey.getEncoded();
		// 为了便于测试，这里我把key写死了，如果大家需要自动生成，可用上面注释掉的代码
		return new byte[] { 0x01, 0x01, 0x04, 0x0b, 0x02, 0x0f, 0x0b, 0x0c,
				0x01, 0x03, 0x09, 0x07, 0x0c, 0x03, 0x07, 0x0a, 0x04, 0x0f,
				0x06, 0x0f, 0x0e, 0x09, 0x05, 0x01, 0x0a, 0x0a, 0x01, 0x09,
				0x06, 0x07, 0x09, 0x0d };
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @return Key 密钥
	 * */
	public static Key toKey(byte[] key) throws Exception {
		// 实例化DES密钥
		// 生成密钥
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @return String 加密后的数据
	 * */
	public static String encrypt(String data) throws Exception {
		// 密钥
		Key k = toKey(AES256Encrypt.initkey());
		/**
		 * 实例化 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
		 */
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return bytes2hex01(cipher.doFinal(data.getBytes()));
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @return String解密后的数据
	 * */
	public static String decrypt(String data) throws Exception {
		// 密钥
		Key k = toKey(AES256Encrypt.initkey());
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		/**
		 * 实例化 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return new String(cipher.doFinal(str2Bytes(data)), "utf-8");
	}

	/**
	 * @Title: str2Bytes
	 * @Description: String to bytes
	 * @author ydfeng
	 * @param s
	 * @return
	 * @throws
	 */
	public static byte[] str2Bytes(String s) {
		int len_to_bytes = s == null ? 0 : s.length() / 2;
		byte[] bs = new byte[len_to_bytes];
		for (int i = 0; i < len_to_bytes; i++) {
			String hex = s.substring(i * 2, i * 2 + 2);
			int num = Integer.parseInt(hex, 16);
			bs[i] = new Byte((byte) num);
		}
		return bs;
	}

	/**
	 * @Title: bytes2hex01
	 * @Description: bytes to hex01
	 * @author ydfeng
	 * @param bytes
	 * @return
	 * @throws
	 */
	public static String bytes2hex01(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		//mv /Users/xuxiong/Documents/uhoer/local_policy.jar /Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/
		// mv /Users/xuxiong/Documents/uhoer/US_export_policy.jar /Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/
		//String str = "{\"limit\":\"10\",\"page\":\"1\",\"aid\":\"a47bd14d-24aa-11e6-946c-086266346e42\"}";
		//String str = "{\"aid\":\"a47ba7b1-24aa-11e6-946c-086266346e42\"}";
		 String str =
		// "{\"serviceType\":\"0\",\"houseKeeperId\":\"201709051108\",\"status\":\"0\",\"startPage\":\"0\",\"pageSize\":\"100\",\"orderType\":\"5\"}";
				 "{\"userId\":\"337\"}";		 
				 
		System.out.println("原文：" + str);
		try {
			String data = AES256Encrypt.encrypt(str);
			System.out.println("加密后：" + data);
			//System.out.println("解密后：" + AES256Encrypt.decrypt("55E27C49EA679BAF6D83F83EEF8795DC11D84190"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
