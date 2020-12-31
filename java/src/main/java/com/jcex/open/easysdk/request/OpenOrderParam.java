package com.jcex.open.easysdk.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: Xie
 * @Date: 2020/12/11 11:00
 */
@Data
public class OpenOrderParam {

    private static final long serialVersionUID = 1L;


    private String shipperHawbcode;



    private String referenceNumber;


    private OpenSenderParam sender;


    private OpenRecipientParam recipient;


    private WeightInfo weight;


    private List<ParcelInfo> parcelList;


    private List<SpecialServiceInfo> specialServiceList;


    private String ogDepotName;


    private String platName;


    private String platNumber;


    private String orderCreateCode;


    private String productCode;


    private String changeServerCodeFlag;


    private String labelTemplate;


    private String notifyUrl;


    private String fbaShipmentId;


    private String fbaPoId;


    private String fbaCode;


    private String orderStatus;


    private String remarkInfo;


    private String domesticTrackingNumber;

}
