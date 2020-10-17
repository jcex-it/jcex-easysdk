package com.jcex.open.easysdk.request;

import com.jcex.open.easysdk.response.CommonResponse;

/**

 */
public class CommonRequest extends BaseRequest<CommonResponse> {

    public CommonRequest(String method) {
        super(method, null);
    }

    public CommonRequest(String method, String version) {
        super(method, version);
    }

    @Override
    protected String method() {
        return "";
    }
}
