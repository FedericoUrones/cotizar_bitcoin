package com.bitcoin.rest_service.allData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bitcoin.rest_service.pojo.Bitcoin;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AllBitcoinData {
    private List<Bitcoin> bitcoins = new ArrayList<Bitcoin>();
    private BigDecimal maxPrice = BigDecimal.valueOf(0);

    public Optional<Bitcoin> getBitcoinByTimeStamp(long timestamp) {
        return bitcoins.stream().filter(
                bitcoin -> timestamp >= bitcoin.getTimeStampSeconds() && timestamp <= bitcoin.getTimeStampSeconds() + 9)
                .findFirst();
    }

    public void addBitcoin(Bitcoin bitcoin) {
        bitcoins.add(bitcoin);
        maxPrice = (bitcoin.getLprice().compareTo(maxPrice) > 0) ? bitcoin.getLprice() : maxPrice;
    }

    public BigDecimal getAverageBetweenTimeStamps(long timestampStart, long timestampEnd) {

        List<BigDecimal> bitcoinPriceFilteredList = bitcoins.stream()
                .filter(bitcoin -> bitcoin.getTimeStampSeconds() >= timestampStart
                        && (bitcoin.getTimeStampSeconds() <= timestampEnd + 9))
                .map(Bitcoin::getLprice)
                .collect(Collectors.toList());
        BigDecimal sum = bitcoinPriceFilteredList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        return (bitcoinPriceFilteredList.size() > 0)? sum.divide(BigDecimal.valueOf(bitcoinPriceFilteredList.size()))
            : BigDecimal.ZERO;
    }
}
