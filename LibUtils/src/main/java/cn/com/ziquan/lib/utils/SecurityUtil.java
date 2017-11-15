package cn.com.ziquan.lib.utils;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/11/15.
 * MD5加密
 */

public class SecurityUtil {

    private SecurityUtil() {
        throw new UnsupportedOperationException("u can't instantiate here!");
    }

    /**
     * 采用MD5加密
     *
     * @param text
     * @return
     */
    public static String encodeMD5(String text) {
        return SecurityUtil.encodeMD5(text, false);
    }

    /**
     * 采用MD5加密字符串
     *
     * @param text        要加密的文本
     * @param toUpperCase 是否转换成大写
     * @return
     */
    public static String encodeMD5(String text, boolean toUpperCase) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(text.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                if (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            if (toUpperCase) {
                return sb.toString().toUpperCase();
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 采用Base64加密
     *
     * @param input
     * @return
     */
    public static byte[] encodeBase64(byte[] input) {
        return Base64.encode(input, Base64.DEFAULT);
    }

    /**
     * 采用Base64加密
     *
     * @param s
     * @return
     */
    public static String encodeBase64(String s) {
        return Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
    }

    /**
     * 解密Base64加密过的密文
     *
     * @param input
     * @return
     */
    public static byte[] decodeBase64(byte[] input) {
        return Base64.decode(input, Base64.DEFAULT);
    }

    /**
     * 解密Base64加密过的密文
     *
     * @param s
     * @return
     */
    public static String decodeBase64(String s) {
        return new String(Base64.decode(s, Base64.DEFAULT));
    }
}
