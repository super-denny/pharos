package com.pharos.common.encryption;

import java.util.Random;

/**
 * @author wcj
 * @ClassName SaltUtil
 * @Description 加密盐
 * @createTime 2020-08-24
 */
public class SaltUtil {

    private static final Integer SALT_SIZE = 8;

    public static int getEncryptTimes() {
        int encryptTimes = (int) (1 + Math.random() * 4);
        return encryptTimes;
    }

    public static String generateSalt() {
        String s = "";
        Random r = new Random();
        for (int i = 0; i < SALT_SIZE; i++) {
            s += (char) (48 + r.nextInt(43));
        }
        return s.toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(generateSalt());
    }
}
