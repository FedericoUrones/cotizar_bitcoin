package com.bitcoin.rest_service.pojo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AverageAndPercentResult {
    private BigDecimal average;
    private String percentBetweenAverageAndMax;
}
