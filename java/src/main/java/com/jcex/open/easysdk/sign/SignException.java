/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.jcex.open.easysdk.sign;


/**
 * 
 * @author runzhi
 */
public class SignException extends Exception {

    private static final long serialVersionUID = -238091758285157331L;

    private String            errCode;
    private String            errMsg;

    public SignException() {
        super();
    }

    public SignException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignException(String message) {
        super(message);
    }

    public SignException(Throwable cause) {
        super(cause);
    }

    public SignException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}