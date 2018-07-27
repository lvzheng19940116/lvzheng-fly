
package flylvzheng.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesCrypt 
{
	//默认密钥
	private static final String CRYPT_KEY = "softstore_soc_root";
	//默认des算法
	private final static String DES = "DES";

	/**
	 * 字符串加密
	 * @param data
	 * @return
	 */
	public static String encrypt(String data) 
	{
		try {
			return byte2hex(encrypt(data.getBytes(),CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 字节数组加密
	 * @param data
	 * @return
	 */
	public static byte[] encrypt(byte[] src) throws Exception
	{
		return encrypt(src, CRYPT_KEY.getBytes());
	}
	/**
	 * 自定义密钥
	 * 字符串加密
	 * @param data
	 * @return
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception 
	{
		Cipher cipher = getEnCipher(key);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}
	/**
	 * 流加密
	 * @param data
	 * @return
	 */
	public static void encrypt(InputStream in, OutputStream out)  throws Exception
	{
		out = new CipherOutputStream(out, getEnCipher(CRYPT_KEY.getBytes()));
		
		byte[] buf = new byte[1024];
	    int numRead = 0;
	    while ((numRead = in.read(buf)) >= 0) 
	    {
	      out.write(buf, 0, numRead);
	    }
	    out.close();
	}
	/**
	 * 输出流加密
	 * @param data
	 * @return
	 */
	public static OutputStream encrypt(OutputStream out)  throws Exception
	{
		return new CipherOutputStream(out, getEnCipher(CRYPT_KEY.getBytes()));
	}
	/**
	 * 字符串解密
	 * @param data
	 * @return
	 */
	public static String decrypt(String data) 
	{
		try {
			return new String(decrypt(hex2byte(data.getBytes()),CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 字节数组解密
	 * @param data
	 * @return
	 */
	public static byte[] decrypt(byte[] src) throws Exception 
	{
		return decrypt(src,CRYPT_KEY.getBytes());
	}
	/**
	 * 自定义密钥
	 * 字符串解密
	 * @param data
	 * @return
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception 
	{
		Cipher cipher = getDeCipher(key);
		return cipher.doFinal(src);
	}
	/**
	 * 
	 * 输入流解密
	 * @param data
	 * @return
	 */
	public static InputStream decrypt(InputStream in)  throws Exception
	{
		return new CipherInputStream(in, getDeCipher(CRYPT_KEY.getBytes()));
	}
	/**
	 * 
	 * 流解密
	 * @param data
	 * @return
	 */
	public static void decrypt(InputStream in, OutputStream out)  throws Exception
	{
	    in = new CipherInputStream(in, getDeCipher(CRYPT_KEY.getBytes()));
	    byte[] buf = new byte[1024];
	    int numRead = 0;
	    while ((numRead = in.read(buf)) >= 0) {
	      out.write(buf, 0, numRead);
	    }
	    out.close();
	}
	/** */
	/**
	 * 19 * 加密 20 *
	 *
	 * @param src
	 *            数据源 21 *
	 * @param key
	 *            密钥，长度必须是8的倍数 22 *
	 * @return 返回加密后的数据 23 *
	 * @throws Exception
	 *             24
	 */
	public static Cipher getEnCipher(byte[] key) throws Exception 
	{
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher;		
	}
	

	/** */
	/**
	 * 44 * 解密 45 *
	 *
	 * @param src
	 *            数据源 46 *
	 * @param key
	 *            密钥，长度必须是8的倍数 47 *
	 * @return 返回解密后的原始数据 48 *
	 * @throws Exception
	 *             49
	 */
	public static Cipher getDeCipher(byte[] key) throws Exception 
	{
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher;		
	}
	/** */
	/**
	 * 94 * 二进制转字符串 95 *
	 *
	 * @param b
	 *            96 *
	 * @return 97
	 */
	public static String byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1){
				hs = hs + "0" + stmp;
			}else{
			hs = hs + stmp;	
			}
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) 
	{
		if ((b.length % 2) != 0){
			throw new IllegalArgumentException("长度不是偶数");
		}			
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	


	/*public static void main(String[] args) 
	{
		String data = DesCrypt.encrypt("123456"); //decrypt
		System.out.println("加密后的数据=" + data);
		
		System.out.println("解密后的数据=" + DesCrypt.decrypt("C662E276423D1432"));
		
	}*/

}
