/**
 * Copyright 2019 bejson.com
 */
package com.jcex.open.easysdk.request;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class OpenExpressRequest {

    private String product_id;

    private String jcex_key;

    private String plat_name;

    private Packages packages;

    public OpenExpressRequest(){

    }
    @Override
    public String toString(){  return JSON.toJSONString(this);    }
}