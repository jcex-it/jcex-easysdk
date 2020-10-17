package com.jcex.open.easysdk.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Senderinformation  {

	private String sender_name;

	private String sender_chinese_name;

	private String sender_company;

	private String sender_phone;

	private String sender_country;

	private String sender_city;

	private String sender_town;

	private String sender_postcode;

	private String sender_address;

	private String sender_customs_registration_code;

	private String sender_customs_operating_units;

	private String sender_proxy_code;

	private String sender_email;

	private String sender_state;

}
