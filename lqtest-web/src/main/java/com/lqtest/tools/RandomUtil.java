package com.lqtest.tools;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    public static Object UUID(Object val) {
        if (val instanceof String && "UUID".equalsIgnoreCase((String) val)) {
            return UUID.randomUUID().toString();
        }
        return val;
    }

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHARARR.charAt(RANDOM.nextInt(62)));
        }
        return sb.toString();
    }

    public static final Random RANDOM = new Random();
    public static final String CHARARR = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
}
