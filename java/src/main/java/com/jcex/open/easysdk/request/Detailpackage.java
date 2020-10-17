package com.jcex.open.easysdk.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain =  true)

public class Detailpackage{

	private String child_number;

	private String fba_number;

	private BigDecimal actual_weight;

	private Float length;

	private Float width;

	private Float height;

	private BigDecimal volume;

	private BigDecimal volume_weight;

	private String track_no;

	private List<Invoiceinformation> item;
}
