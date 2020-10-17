package com.jcex.open.easysdk.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘亚杰
 * @date 2019/11/25 18:28
 * @action
 */
@Data
@Accessors(chain = true)
public class Product {

    private String productId;

    private String productName;

}
