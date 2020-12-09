package com.bitcoin.rest_service.allData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bitcoin.rest_service.pojo.Bitcoin;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AllBitcoinData {
    private List<Bitcoin> bitcoins = new ArrayList<Bitcoin> ();
    private BigDecimal maxPrice = BigDecimal.valueOf(0);
    
    public Optional<Bitcoin> getBitcoinByTimeStamp(long timestamp) {
        return bitcoins.stream().filter(bitcoin -> timestamp >= bitcoin.getTimeStampSeconds() && timestamp <= bitcoin.getTimeStampSeconds() + 9)
            .findFirst();
    }

    public void addBitcoin(Bitcoin bitcoin) {
        bitcoins.add(bitcoin);
        maxPrice = (bitcoin.getLprice().compareTo(maxPrice) > 0)? bitcoin.getLprice() : maxPrice;
    }

    public Optional<Bitcoin> getBitcoinBetweenTimeStamps(long timestampStart, long timestampEnd) {
        return bitcoins.stream().filter(bitcoin -> timestamp >= bitcoin.getTimeStampSeconds() && timestamp <= bitcoin.getTimeStampSeconds() + 9)
            .findFirst();
    }
}
