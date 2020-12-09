package com.bitcoin.rest_service.pojo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Bitcoin {
    private String /*lprice, */curr1, curr2;
    private BigDecimal lprice;
    private long timeStampSeconds;
}
