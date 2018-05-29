package com.util.math;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EncryptUtil {
    static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    static Charset UTF_8 = Charset.forName("UTF-8");
    /**
     * MD5编码 默认为小写 utf-8格式
     * @author: wulang
     * @date: 2017/9/28 17:35
     * @param source
     * @modify by user: {修改人}  2017/9/28 17:35
     * @modify by reason:
     * @return
     */
    public static String MD5Encode(String source) {
        return hashEncode(source, "MD5", UTF_8);
    }

    public static String MD5Encode(String source, String charset) {
        return hashEncode(source, "MD5", Charset.forName(charset));
    }

    public static String SHA_256Encode(String source) {
        return hashEncode(source, "SHA-256", UTF_8);
    }

    public static String SHA_256Encode(String source, String charset) {
        return hashEncode(source, "SHA-256", Charset.forName(charset));
    }

    public static String SHA_512Encode(String source) {
        return hashEncode(source, "SHA-512", UTF_8);
    }

    public static String SHA_512Encode(String source, String charset) {
        return hashEncode(source, "SHA-512", Charset.forName(charset));
    }

    public static String ContentEncode(String source) {
        byte bytes[] = Base64.encodeBase64(renderBytes(source));
        return bytesToHex(bytes);
    }

    public static String ContentDecoder(String source) {
        byte bytes[] = Base64.decodeBase64(renderBytes(new String(hexToBytes(source))));
        return new String(bytes, UTF_8);
    }

    private static byte[] renderBytes(String source) {
        byte[] bytes = null;
        bytes = source.getBytes(UTF_8);
        for (byte b : bytes) {
            b = (byte) ~b;
        }
        return bytes;
    }

    private static String hashEncode(String source, String algorithm, Charset charset) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(source.getBytes(charset));
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String bytesToHex(byte[] bytes) {
        char[] charArr = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            charArr[i << 1] = hexDigits[bytes[i] >>> 4 & 0xf];
            charArr[(i << 1) + 1] = hexDigits[bytes[i] & 0xf];
        }
        return new String(charArr);
    }

    private static byte[] hexToBytes(String source) {
        byte[] bytes = new byte[source.length() / 2];
        for (int i = 0; i < source.length(); i += 2) {
            int value = (Integer.valueOf(source.charAt(i) + "" + source.charAt(i + 1), 16) & 0xff);
            bytes[i / 2] = (byte) value;
        }
        return bytes;
    }

}
