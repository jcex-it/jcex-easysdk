package com.jcex.open.easysdk.request;


import com.jcex.open.easysdk.response.OpenAuthTokenAppResponse;

/**

 */
public class OpenAuthTokenAppRequest extends BaseRequest<OpenAuthTokenAppResponse> {
    @Override
    protected String method() {
        return "open.auth.token.app";
    }
}
