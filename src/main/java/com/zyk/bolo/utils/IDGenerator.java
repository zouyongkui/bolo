package com.zyk.bolo.utils;

import java.util.UUID;

public class IDGenerator {
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replaceAll("-", "");
    }
}
