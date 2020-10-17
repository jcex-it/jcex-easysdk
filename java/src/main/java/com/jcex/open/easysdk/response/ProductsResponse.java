package com.jcex.open.easysdk.response;

import com.jcex.open.easysdk.domain.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 刘亚杰
 * @date 2019/11/25 18:25
 * @action
 */
@Data
@Accessors(chain = true)
public class ProductsResponse extends BaseResponse {

    private List<Product> products;

    public ProductsResponse(List<Product> products){
        this.products=products;
    }

}
