package com.futurenet.cotree.admin.util;

public class RatioUtil {
    //비율 구하는 함수 numerator = 분자, denominator = 분모
    public static double calculateRatio(int numerator, int denominator) {
        if (denominator == 0) return 0.0;
        return Math.round(numerator * 1000.0 / denominator) / 10.0;
    }
}
