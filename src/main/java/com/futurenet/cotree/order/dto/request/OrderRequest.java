package com.futurenet.cotree.order.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    @NotEmpty
    private List<OrderItemRegisterRequest> orderItems;

    @NotNull
    private String destination;

    @NotNull
    private String receiverName;

    @NotNull
    @Pattern(regexp = "\\d{11}", message = "전화번호는 숫자 11자리여야 합니다.")
    private String receiverTel;

    private String request;

    @NotNull
    @Pattern(regexp = "\\d{16}", message = "카드번호는 숫자 16자리여야 합니다.")
    private String cardNumber;

    @NotNull
    private String bankName;

    @JsonProperty("isCart")
    private boolean isCart;
}
