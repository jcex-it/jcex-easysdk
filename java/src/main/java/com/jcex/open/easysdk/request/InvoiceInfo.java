package com.jcex.open.easysdk.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Xie
 * @Date: 2020/12/11 11:33
 */
@Data
public class InvoiceInfo {


    private String chineseName;

    private String englishName;

    private String hscode;

    private Integer inpieces;

    private BigDecimal unitPriceAmount;

    private BigDecimal declarationAmount;

    private String declarationCurrency;

    private String materialQuality;

    private String purpose;

    private String measurementUnit;

    private String specificationModel;

    private String invoiceMaterialCn;

    private String invoiceMaterialEn;

    private String invoiceUseCn;

    private String invoiceUseEn;

    private Integer goodsId;

    private String productSku;

    private String productName;

    private String invoiceUrl;

    private BigDecimal invoiceWeight;

    private String warehouseCode;

    private String sku;

    private String pmUrl;

}
