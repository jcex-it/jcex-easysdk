package com.jcex.open.easysdk.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Data
public class Packages {

	private String waybill_number;

	private String reference_number;

	private Senderinformation sender;

	private Recipientinformation recipient;

	private List<Invoiceinformation> invoice;

	private Weightinformation weight;

	private List<Detailpackage> detail_package ;

	private List<Specialservice> special_service;

	private Date out_bound_time;

	private Boolean update;

	private String plat_number;

	private String fba_shipment_id;

	private String fba_po_id;

	private String fba_code;

}