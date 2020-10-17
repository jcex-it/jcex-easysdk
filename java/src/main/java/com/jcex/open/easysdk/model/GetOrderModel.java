package com.jcex.open.easysdk.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GetOrderModel {

    @JSONField(name = "waybillNumber")
    private String waybillNumber;
}
