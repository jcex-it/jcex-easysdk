package com.jcex.open.easysdk.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Xie
 * @Date: 2020/12/11 11:37
 */
@Data
public class ParcelInfo {


    private String childNumber;

    private BigDecimal actualWeight;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal volume;


    private BigDecimal volumeWeight;

    private String trackNo;

    private List<InvoiceInfo> itemList;
}
