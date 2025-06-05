package com.futurenet.cotree.auth.constant;

public class JwtConstants {
    public static final Long ACCESS_EXPIRED = 3 * 60 * 60 * 1000L;
    public static final Long REFRESH_EXPIRED = 14 * 24 * 60 * 60 * 1000L;

    public static final int ACCESS_COOKIE_EXPIRED =  3 * 60 * 60;
    public static final int REFRESH_COOKIE_EXPIRED =  14 * 24 * 60 * 60;
}
