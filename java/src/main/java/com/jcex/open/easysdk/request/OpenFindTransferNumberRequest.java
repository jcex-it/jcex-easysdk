package com.jcex.open.easysdk.request;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 刘亚杰
 * @date 2019/11/25 11:34
 * @action
 */
@Data
@Accessors(chain = true)
public class OpenFindTransferNumberRequest implements Serializable {

    private String waybill_number;

    public OpenFindTransferNumberRequest(){

    }

}
