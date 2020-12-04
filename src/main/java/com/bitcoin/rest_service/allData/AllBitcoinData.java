package com.bitcoin.rest_service.allData;

import java.util.ArrayList;
import java.util.List;

import com.bitcoin.rest_service.pojo.Bitcoin;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AllBitcoinData {
    private List<Bitcoin> bitcoins = new ArrayList<Bitcoin> ();
}
