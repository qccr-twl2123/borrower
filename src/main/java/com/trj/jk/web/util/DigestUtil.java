package com.trj.jk.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

/**
 * 密码加密工具
 *
 * @date 2011-2-11 下午04:04:17
 *
 */
public class DigestUtil {
    public static final Logger logger = LoggerFactory.getLogger(DigestUtil.class);
 	/**
 	 * 做SHA加密
 	 * @param password
 	 * @return
 	 */
    public static String getSHA(String password) {
        try {
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(password.getBytes());
            byte[] digesta = alga.digest();
            String digesta_last = byte2hex(digesta);
            return digesta_last;
        }
        catch (NoSuchAlgorithmException ex) {
        }
        return "";
    }

    /**
     * 二进制转化成16进制表示，每个数字之间加入":"作为分割
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) { //二行制转字符串
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
            if (n < b.length - 1) hs = hs + ":";
        }
        return hs.toUpperCase();
    }


    /**
     * 做MD5加密
     * @param str
     * @return
     */
    public static String getMD5(String str){
        logger.info("MD5 str:"+str);

    	try {
            MessageDigest alga = MessageDigest.getInstance("MD5");
            alga.update(str.getBytes());
            byte[] digesta = alga.digest();
            String digesta_last = byte2hex2(digesta);
            logger.info("MD5:"+ digesta_last);
            return digesta_last;
        }
        catch (NoSuchAlgorithmException ex) {
        }
        return "";
    }

    /**
     * 二进制转化成16进制表示
     * @param b
     * @return
     */
    private static String byte2hex2(byte[] b) { //二行制转字符串
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
        }
        return hs;
    }

    static String hmacsha256(String secret, Map<String, String> headers) {
        try {
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            byte[] keyBytes = secret.getBytes("UTF-8");
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));

            return new String(Base64.encodeBase64(hmacSha256.doFinal(buildStringToSign(headers).getBytes("UTF-8"))), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static String buildStringToSign(Map<String, String> headers) {
        StringBuilder sb = new StringBuilder();
        String[] sortedHeader = new String[headers.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            sortedHeader[i++] = entry.getValue();
        }
        Arrays.sort(sortedHeader);
        for (String s : sortedHeader) {
            sb.append(s);
        }
        return sb.toString();
    }


    public static void main(String[] args){
       // System.out.println("pwd:"+DigestUtil.getSHA("123"));
        System.out.println("MD5 pwd:"+DigestUtil.getMD5("m123456"));
    }



}