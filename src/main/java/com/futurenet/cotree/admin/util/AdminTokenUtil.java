package com.futurenet.cotree.admin.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AdminTokenUtil {
    public static String encode(String code) {
        return Base64.getEncoder().encodeToString(code.getBytes(StandardCharsets.UTF_8));
    }

    public static Long decode(String token) {
        return Long.parseLong(new String(Base64.getDecoder().decode(token)));
    }
}
