package com.jcex.open.easysdk.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刘亚杰
 * @date 2019/11/25 18:49
 * @action
 */
@Data
@Accessors(chain = true)
public class OpenLabelRequest implements Serializable {

    private String label_type;

    private List<String> waybill_number;

    public OpenLabelRequest(){
    }
}
