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
//    @ApiModelProperty(value="回传地址",example = "http://localhost:8080/api/notify/")
//    private String notify_url;


    private String jcex_key;

    private String plat_name;

    private Packages packages;

    public OpenExpressRequest(){

    }
    @Override
    public String toString(){  return JSON.toJSONString(this);    }
}