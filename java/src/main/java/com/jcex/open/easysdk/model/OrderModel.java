package com.jcex.open.easysdk.model;


import lombok.Data;

import java.io.Serializable;

/**
 * dubbo 参数

 */
@Data
public class OrderModel implements Serializable {


    private String  apiPlatform;


    private String  jcexKey;


    private String  customerId;


    private String  customer;

    //@ApiModelProperty(value = "包裹明细")
    //private List<Package> packages;

}
