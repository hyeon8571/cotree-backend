package com.futurenet.cotree.payment.repository;

import com.futurenet.cotree.payment.dto.request.PaymentRegisterRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

@Mapper
public interface PaymentRepository {
    int savePayment(@Param("request") PaymentRegisterRequest paymentRegisterRequest);
    long getMonthlyRevenue(@Param("from") LocalDate from, @Param("to") LocalDate to);
    long getMonthlyOrderCount(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
