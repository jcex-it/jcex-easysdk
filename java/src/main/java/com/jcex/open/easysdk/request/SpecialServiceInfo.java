package com.jcex.open.easysdk.request;


import lombok.Data;


import java.math.BigDecimal;

/**
 * @Author: Xie
 * @Date: 2020/12/11 11:40
 */
@Data
public class SpecialServiceInfo {



    private String serviceName;

    private BigDecimal costAmount;

    private String costCurrency;

    private String description;
}
