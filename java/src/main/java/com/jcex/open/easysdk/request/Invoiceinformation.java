package com.jcex.open.easysdk.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class Invoiceinformation {


	private String chinese_name;


	private String english_name;


	private String hscode;


	private Integer inpieces;


	private Double unit_price_amount;


	private BigDecimal declaration_amount;


	private String declaration_currency;

	private String material_quality;

	private String purpose;

	private String measurement_unit;

	private String specification_model;

	private String invoice_material_cn;

	private String invoice_material_en;

	private String invoice_use_cn;

	private String invoice_use_en;

	private Integer goods_id;

	private String product_sku;

	private String product_name;

	private String invoice_url;

	private BigDecimal invoice_weight;

	private String warehouse_code;

	private String sku;

	private String pm_url;
}