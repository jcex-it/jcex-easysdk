package com.jcex.open.easysdk.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class Weightinformation {
	private String weight_method;

	private Short total_packages;

	private String item_type;

	private BigDecimal total_weight;

	private BigDecimal total_volumeweight;

	private BigDecimal total_chargeable_weight;


}