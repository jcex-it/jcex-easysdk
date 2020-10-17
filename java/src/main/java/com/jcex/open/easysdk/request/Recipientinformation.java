package com.jcex.open.easysdk.request;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Recipientinformation  {

	private String recipient_name;

	private String recipient_phone;

	private String recipient_mobile;

	private String recipient_country;

	private String recipient_postcode;

	private String recipient_city;

	private String recipient_state;

	private String recipient_town;

	private String recipient_house_number;

	private String recipient_address;

	private String recipient_company;

	private String recipient_duty_paragraph;

	private String recipient_email;

	private String recipient_certificate_code;
}