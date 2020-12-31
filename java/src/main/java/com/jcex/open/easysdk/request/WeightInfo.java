package com.jcex.open.easysdk.request;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Xie
 * @Date: 2020/12/11 11:37
 */
@Data
public class WeightInfo {

    private String weightMethod;

    private Integer totalPackages;

    private String itemType;

    private BigDecimal totalWeight;

    private BigDecimal totalVolumeWeight;

    private BigDecimal totalChargeableWeight;
}
