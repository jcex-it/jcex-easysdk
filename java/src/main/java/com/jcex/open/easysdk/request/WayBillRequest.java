package com.jcex.open.easysdk.request;


import lombok.Data;


import java.util.Date;

@Data
public class WayBillRequest {

    long page;

    long size;

    private Date beginDate;

    private Date endDate;

}
