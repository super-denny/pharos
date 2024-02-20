package com.pharos.common.encryption;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5Util {
    // 获得MD5摘要算法的 MessageDigest 对象
    private static MessageDigest _mdInst = null;
    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static MessageDigest getMdInst() {
        if (_mdInst == null) {
            try {
                _mdInst = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                log.warn("MessageDigest 对象为空");
            }
        }
        return _mdInst;
    }

    public static String pwdEncrypt(String password, String salt, Integer times) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：相同密码使用不同的盐加密后的结果不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        SimpleHash result = new SimpleHash(hashAlgorithmName, password, byteSalt, times);
        return result.toString();
    }

    public final static String encode(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 使用指定的字节更新摘要
            getMdInst().update(btInput);
            // 获得密文
            byte[] md = getMdInst().digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.warn("加密失败");
            return null;
        }
    }

    public static void main(String[] args) {
        String salt = SaltUtil.generateSalt();
        System.out.println(salt);
        int encryptTimes = SaltUtil.getEncryptTimes();
        System.out.println(encryptTimes);
        System.out.println(pwdEncrypt("123456",salt, encryptTimes));
    }
}
