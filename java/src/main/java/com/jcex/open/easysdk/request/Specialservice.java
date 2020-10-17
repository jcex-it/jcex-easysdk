package com.jcex.open.easysdk.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class Specialservice  {

	private String service_name;

	private BigDecimal cost_amount;

	private String cost_currency;

	private String description;

}