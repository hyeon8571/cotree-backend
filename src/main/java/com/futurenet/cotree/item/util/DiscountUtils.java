package com.futurenet.cotree.item.util;

public class DiscountUtils {

    private DiscountUtils() {}

    //할인율 계산
    public static int calculateDiscountRate(int price, int discount) {
        if (price == 0) return 0;
        return (int) Math.round((double) discount / price * 100);
    }
}
