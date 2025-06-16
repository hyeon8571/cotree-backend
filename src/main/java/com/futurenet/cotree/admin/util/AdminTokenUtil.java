package com.futurenet.cotree.admin.util;

import java.util.Base64;

public class AdminTokenUtil {
    public static String encode(Long id) {
        return Base64.getEncoder().encodeToString(String.valueOf(id).getBytes());
    }

    public static Long decode(String token) {
        return Long.parseLong(new String(Base64.getDecoder().decode(token)));
    }
}
