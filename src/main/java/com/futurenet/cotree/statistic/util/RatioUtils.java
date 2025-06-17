package com.futurenet.cotree.statistic.util;

public class RatioUtils {

    //비율 계산 함수 numerator = 분자, denominator = 분모
    public static double calculateRatio(double numerator, double denominator) {
        if (denominator == 0) return 0.0;
        return Math.round(numerator * 1000.0 / denominator) / 10.0;
    }
}
