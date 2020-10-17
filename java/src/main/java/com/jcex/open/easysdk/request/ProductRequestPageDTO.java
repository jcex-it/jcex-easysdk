package com.jcex.open.easysdk.request;


import lombok.Data;

import java.io.Serializable;


@Data
public class ProductRequestPageDTO implements Serializable {

    long page;


    long size;
}
