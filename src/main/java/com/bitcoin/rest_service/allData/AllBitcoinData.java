package com.bitcoin.rest_service.allData;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bitcoin.rest_service.pojo.AverageAndPercentResult;
import com.bitcoin.rest_service.pojo.Bitcoin;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AllBitcoinData {
    private List<Bitcoin> bitcoins = new ArrayList<Bitcoin>();
    private BigDecimal maxPrice = BigDecimal.valueOf(0);
    private MathContext mathContext = new MathContext(2, RoundingMode.CEILING); 
    private BigDecimal ONE_HUNDRED_BIG_DECIMAL = BigDecimal.valueOf(100);

    /**
     * Returns a bitcoin prive filtering by timestamp
     * @param timestamp
     * @return Optional Bitcoin
     */
    public Optional<Bitcoin> getBitcoinByTimeStamp(long timestamp) {
        return bitcoins.stream().filter(
                bitcoin -> timestamp >= bitcoin.getTimeStampSeconds() && timestamp <= bitcoin.getTimeStampSeconds() + 9)
                .findFirst();
    }

    /**
     * Adds a bitcoin to bitcoin List
     * @param bitcoin
     */
    public void addBitcoin(Bitcoin bitcoin) {
        bitcoins.add(bitcoin);
        maxPrice = (bitcoin.getLprice().compareTo(maxPrice) > 0) ? bitcoin.getLprice() : maxPrice;
    }

    /**
     * Returns average price between two timestamps (in seconds)
     * @param timestampStart
     * @param timestampEnd
     * @return average bitcoin price (BigDecimal)
     */
    private BigDecimal getAverageBetweenTimeStamps(long timestampStart, long timestampEnd) {

        List<BigDecimal> bitcoinPriceFilteredList = bitcoins.stream()
                .filter(bitcoin -> bitcoin.getTimeStampSeconds() >= timestampStart
                        && (bitcoin.getTimeStampSeconds() <= timestampEnd + 9))
                .map(Bitcoin::getLprice).collect(Collectors.toList());
        BigDecimal sum = bitcoinPriceFilteredList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        return (bitcoinPriceFilteredList.size() > 0) ? sum.divide(BigDecimal.valueOf(bitcoinPriceFilteredList.size()))
                : BigDecimal.ZERO;
    }

    /**
     * Returns percentage difference between average value and max bitcoin price value
     * @param average
     * @return percentage difference String
     */
    private String getPercentBetweenAverageAndMax(BigDecimal average) {
        return average.multiply(ONE_HUNDRED_BIG_DECIMAL).divide(maxPrice, mathContext)
            .subtract(ONE_HUNDRED_BIG_DECIMAL, mathContext).toPlainString() + "%";
    }

    /**
     * Returns average price between two timestamps (in seconds) and
     * percentage difference between average value and max bitcoin price value
     * @param timestampStart
     * @param timestampEnd
     * @return average price and percentage difference
     */
    public AverageAndPercentResult getAverageAndPercent(long timestampStart, long timestampEnd) {
        BigDecimal average = getAverageBetweenTimeStamps(timestampStart, timestampEnd);
        return new AverageAndPercentResult(average, getPercentBetweenAverageAndMax(average));
    }
}
