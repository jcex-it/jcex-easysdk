/**
 * Copyright 2019 bejson.com
 */
package com.jcex.open.easysdk.request;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class OpenOrderInfoRequest implements Serializable {

    private List<String> waybill_number;

    public OpenOrderInfoRequest(){

    }
    @Override
    public String toString(){  return JSON.toJSONString(this);    }
}