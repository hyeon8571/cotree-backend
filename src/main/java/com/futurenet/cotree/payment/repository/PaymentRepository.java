package com.futurenet.cotree.payment.repository;

import com.futurenet.cotree.payment.dto.request.PaymentRegisterRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentRepository {
    int savePayment(@Param("request") PaymentRegisterRequest paymentRegisterRequest);
}
