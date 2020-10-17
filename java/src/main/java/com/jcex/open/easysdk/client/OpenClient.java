package com.jcex.open.easysdk.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jcex.open.easysdk.exception.SdkException;
import com.jcex.open.easysdk.request.BaseRequest;
import com.jcex.open.easysdk.response.BaseResponse;
import com.jcex.open.easysdk.response.ErrorResponse;
import com.jcex.open.easysdk.sign.SignException;
import com.jcex.open.easysdk.sign.Signature;
import com.jcex.open.easysdk.sign.StringUtils;
import com.jcex.open.easysdk.common.DataNameBuilder;
import com.jcex.open.easysdk.common.OpenConfig;
import com.jcex.open.easysdk.common.RequestForm;
import com.jcex.open.easysdk.common.SdkConstants;
import com.jcex.open.easysdk.common.SdkErrors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collections;
import java.util.Map;

/**
 * 请求客户端，申明一个即可
 *

 */
public class OpenClient {
    private static final Log log = LogFactory.getLog(OpenClient.class);

    /**
     * 默认配置
     */
    private static final OpenConfig DEFAULT_CONFIG = new OpenConfig();

    /**
     * 接口请求url
     */
    private String url;

    /**
     * 平台提供的appId
     */
    private String appId;

    /**
     * 开放平台提供的私钥
     */
    private String privateKey;

    /**
     * 开放平台提供的公钥
     */
    private String publicKeyPlatform;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 配置项
     */
    private OpenConfig openConfig;

    /**
     * 请求对象
     */
    private OpenRequest openRequest;

    /**
     * 节点处理
     */
    private DataNameBuilder dataNameBuilder;

    /**
     * 构建请求客户端
     *
     * @param url           接口url
     * @param appId         平台分配的appId
     * @param privateKeyIsv 平台分配的私钥
     */
    public OpenClient(String url, String appId, String privateKeyIsv) {
        this(url, appId, privateKeyIsv, DEFAULT_CONFIG);
    }

    /**
     * 构建请求客户端
     *
     * @param url               接口url
     * @param appId             平台分配的appId
     * @param privateKeyIsv     平台分配的私钥
     * @param notifyUrl 平台分配的公钥
     */
    public OpenClient(String url, String appId, String privateKeyIsv, String notifyUrl) {
        this(url, appId, privateKeyIsv);
        this.notifyUrl = notifyUrl;
    }

    /**
     * 构建请求客户端
     *
     * @param url           接口url
     * @param appId         平台分配的appId
     * @param privateKeyIsv 平台分配的私钥
     * @param openConfig    配置项
     */
    public OpenClient(String url, String appId, String privateKeyIsv, OpenConfig openConfig) {
        if (openConfig == null) {
            throw new IllegalArgumentException("openConfig不能为null");
        }
        this.url = url;
        this.appId = appId;
        this.privateKey = privateKeyIsv;
        this.openConfig = openConfig;

        this.openRequest = new OpenRequest(openConfig);
        this.dataNameBuilder = openConfig.getDataNameBuilder();
    }

    /**
     * 构建请求客户端
     *
     * @param url               接口url
     * @param appId             平台分配的appId
     * @param privateKeyIsv     平台分配的私钥
     * @param publicKeyPlatform 平台分配的公钥
     * @param openConfig        配置项
     */
    public OpenClient(String url, String appId, String privateKeyIsv, String publicKeyPlatform, OpenConfig openConfig) {
        this(url, appId, privateKeyIsv, openConfig);
        this.publicKeyPlatform = publicKeyPlatform;
    }

    /**
     * 请求接口
     *
     * @param request 请求对象
     * @param <T>     返回对应的Response
     * @return 返回Response
     */
    public <T extends BaseResponse> T execute(BaseRequest<T> request) {
        return this.execute(request, null);
    }

    /**
     * 请求接口
     *
     * @param request     请求对象
     * @param accessToken jwt
     * @param <T>         返回对应的Response
     * @return 返回Response
     */
    public <T extends BaseResponse> T execute(BaseRequest<T> request, String accessToken) {
        RequestForm requestForm = request.createRequestForm(this.openConfig);
        // 表单数据
        Map<String, String> form = requestForm.getForm();
        if (accessToken != null) {
            form.put(this.openConfig.getAccessTokenName(), accessToken);
        }
        form.put(this.openConfig.getAppKeyName(), this.appId);
        if (!StringUtils.isEmpty(this.notifyUrl)){
            form.put(this.openConfig.getNotifyUrl(), this.notifyUrl);
        }
        String content = Signature.getSignContent(form);
        String sign = null;

        try {
            sign = Signature.rsaSign(content, privateKey, openConfig.getCharset(), openConfig.getSignType());

        } catch (SignException e) {
            throw new SdkException("构建签名错误", e);
        }

        form.put(this.openConfig.getSignName(), sign);
        String resp = doExecute(this.url, requestForm, Collections.emptyMap());

        System.out.println("待签名内容");
        System.out.println(content);

        System.out.println("11111111");
        System.out.println(privateKey);

        System.out.println("sign");

        System.out.println(sign);

        if (log.isDebugEnabled()) {
            log.debug("----------- 请求信息 -----------"
                    + "\n请求参数：" + Signature.getSignContent(form)
                    + "\n待签名内容：" + content
                    + "\n签名(sign)：" + sign
                    + "\n----------- 返回结果 -----------"
                    + "\n" + resp
            );
        }
        return this.parseResponse(resp, request);
    }

    protected String doExecute(String url, RequestForm requestForm, Map<String, String> header) {
        return openRequest.request(url, requestForm, header);
    }

    /**
     * 解析返回结果
     *
     * @param resp    返回结果
     * @param request 请求对象
     * @param <T>     返回结果
     * @return 返回对于的Response对象
     */
    protected <T extends BaseResponse> T parseResponse(String resp, BaseRequest<T> request) {
        String method = request.getMethod();
        String rootNodeName = dataNameBuilder.build(method);
        JSONObject jsonObject = JSON.parseObject(resp);

        String errorResponseName = this.openConfig.getErrorResponseName();

        System.out.println(rootNodeName);

        boolean errorResponse =  jsonObject.containsKey(errorResponseName);
        if (errorResponse) {
            rootNodeName = errorResponseName;
        }

        JSONObject data = jsonObject.getJSONObject(rootNodeName);
        String sign = jsonObject.getString(openConfig.getSignName());
        // 是否要验证返回的sign
        if (StringUtils.areNotEmpty(sign, publicKeyPlatform)) {
            String signContent = buildBizJson(rootNodeName, resp);
            if (!this.checkResponseSign(signContent, sign, publicKeyPlatform)) {
                ErrorResponse error = SdkErrors.CHECK_RESPONSE_SIGN_ERROR.getErrorResponse();
                data = JSON.parseObject(JSON.toJSONString(error));
            }
        }

        //System.out.println(request.getResponseClass().toString());

        SerializerFeature[] features = {
                SerializerFeature.WriteClassName,
        };

        T t =  data.toJavaObject(request.getResponseClass());

        t.setCode(data.getString("code"));
        t.setMsg(data.getString("msg"));
        t.setSubCode(data.getString("sub_code"));
        t.setSubMsg(data.getString("sub_msg"));

        t.setBody(data.toJSONString());
        return t;
    }

    /**
     * 构建业务json内容。
     * 假设返回的结果是：<br>
     * {"alipay_story_get_response":{"msg":"Success","code":"10000","name":"海底小纵队","id":1},"sign":"xxx"}
     * 将解析得到：<br>
     * {"msg":"Success","code":"10000","name":"海底小纵队","id":1}
     *
     * @param rootNodeName 根节点名称
     * @param body         返回内容
     * @return 返回业务json
     */
    protected String buildBizJson(String rootNodeName, String body) {
        int indexOfRootNode = body.indexOf(rootNodeName);
        if (indexOfRootNode < 0) {
            rootNodeName = SdkConstants.ERROR_RESPONSE_KEY;
            indexOfRootNode = body.indexOf(rootNodeName);
        }
        String result = null;
        if (indexOfRootNode > 0) {
            result = buildJsonNodeData(body, rootNodeName, indexOfRootNode);
        }
        return result;
    }

    /**
     * 获取业务结果，如下结果：<br>
     * {"alipay_story_get_response":{"msg":"Success","code":"10000","name":"海底小纵队","id":1},"sign":"xxx"}
     * 将返回：<br>
     * {"msg":"Success","code":"10000","name":"海底小纵队","id":1}
     *
     * @param body            返回内容
     * @param rootNodeName    根节点名称
     * @param indexOfRootNode 根节点名称位置
     * @return 返回业务json内容
     */
    protected String buildJsonNodeData(String body, String rootNodeName, int indexOfRootNode) {
        /*
          得到起始索引位置。{"alipay_story_get_response":{"msg":"Success","code":"10000","name":"海底小纵队","id":1},"sign":"xxx"}
          得到第二个`{`索引位置
         */
        int signDataStartIndex = indexOfRootNode + rootNodeName.length() + 2;
        // 然后这里计算出"sign"字符串所在位置
        int indexOfSign = body.indexOf("\"" + openConfig.getSignName() + "\"");
        if (indexOfSign < 0) {
            return null;
        }
        int length = indexOfSign - 1;
        // 根据起始位置和长度，截取出json：{"msg":"Success","code":"10000","name":"海底小纵队","id":1}
        return body.substring(signDataStartIndex, length);
    }

    /**
     * 校验返回结果中的sign
     *
     * @param signContent       校验内容
     * @param sign              sign
     * @param publicKeyPlatform 平台公钥
     * @return true：正确
     */
    protected boolean checkResponseSign(String signContent, String sign, String publicKeyPlatform) {
        try {
            String charset = this.openConfig.getCharset();
            String signType = this.openConfig.getSignType();
            return Signature.rsaCheck(signContent, sign, publicKeyPlatform, charset, signType);
        } catch (SignException e) {
            log.error("验证服务端sign出错，signContent：" + signContent, e);
            return false;
        }
    }


}
