package com.jcex.open.easysdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jcex.open.easysdk.model.OpenAuthTokenAppModel;
import com.jcex.open.easysdk.client.OpenClient;
import com.jcex.open.easysdk.request.*;
import com.jcex.open.easysdk.response.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

public class SdkTest extends TestCase {

    String url = "https://open.jcex.com";

    //String url = "http://127.0.0.1:8081";

    String appId="20200228683046215729807360";

    //String appId= "20200508708350168163942400";

    /** 开发者私钥 */
    String privateKeyIsv = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCFvSO8FUA2JYRlUYpQSdc3uDgIKwnunhc0uMOnOSMU7OY9iewVUH/OOw+7KB0CXpz6nMwNlD1AAJDl71rkoq7n0nmPQIrYcUBkbkaQNaM1rrtshQMKhqH6vuguyJWgp3GVmZcDzdAujoQs8IAoSm/WQleUIjREXktFT9gGvZiXDuZ/MxxfYHimSiDu1ura8YgSvt8S7wqDOZoZiRvGV3I3utC8cYKIhwMk03QKr29vNMSW0Tqqe12ic01B1glKd1SQFvsz66gcdG3QvX5G0tPMNygoUs+cbXnuRZ0L1OGw96pMKmbR6bkicjhDcls8ESqr1ictqBM+nvrRaHeKoI9zAgMBAAECggEAOFUb+TxmSKhy5Q58mScRPUoeZ+phC56RncZ5uzVI/qZusNsoky3ON7sO/EGAPqBFtRZYqRuY1R2n6wJwIcAYtlMkmt+P3G2Ptv3wQQwmzBTmo2Y/iq59ChZhzFr3E62UHXSNdK+7kfC74gYHpXD9PJ3DMAVcSLQDNpXUI7q2zGGBPwtZnaRQ6xcV86z6uebtom0S98elkn14tC1p4XoPsym7yRgNBZdDTt0iiAkzJOZmUXk/no/VXy/h2xyqoqvX2hLobd5VX29rp18Ewd18pfKvXSNXrijQo6Q6q5m4Rnb+wtXqEQJO4IfErueNbtpoRfDRjRkzZF4MKZ+bEdFGSQKBgQDDagViLmcKkVdgz4HDdS8jea2zjRSEH155m/ytGn+XW2YFjyzBg7nIum/ybUbjctihCrw65MJ3NK3WGo436NBPIe6vzcJ2GNgBFRCyZItrWTbiKEC8ackgvsug8t4rqyzoC6c3VMWSXOLDLVGyHPMmyJTmAXIk4fB5cSQdhStmLQKBgQCvM/YzHGWL8tbwNWn11wzDToclabFpVZe29+xI2E0/s1sn5+5GnAZkYLiucTjGwu6m2FyxbhdOi1L//vaG8p+bT/SK72UjYeOi8vCdGWihfzO/rpEiqWMLg54q8yH7sa++EjZC3x07/cydhdFP1w2NoGGrumNGXTAJBnZ6jwnwHwKBgQCl6/mSOdAn+ux0OPz3VyKlPoWsdNe8r13qZ/YNjPhVLbfcdIsN7KjstNCkyQBt02/4nULhxqcPzFzl9DpZYoatCiS+hTjeSY3urVI2BoUSqvec44zD7hZmhVok2NKnxXoltO9QXdw7wD6SdoJtd4NFpP44pXWjt2wV0WlW32HQKQKBgDGfZAIB7vj+ZMZQOpy4thKzMS/1iZwt8/SMr83KBccfA5h/5kr1chIIGwa9ZrlVCMcUzNwsNEm4yF9dVXHPUklpEfgv6dNlrFXAvUmhorLrFjsaNF0+4KCoPKophtyE7SPGgLvb+am+LrAG8MvOOzZ70G4VMn0Bc9N6kyAPV189AoGBAK6SHYCHfa6skP9AJ15i7Fo/LY9Ms6E4BzEG+RcadDaqNvcF1ZgMAwA6lHqkQJMvDstUXcCbMsb3+aH1AB2H2y2y6fiKgzkImLpujCiNPdLTF/9kGKfP0JS/HNhb8yJAgpGbc4mUM2OeiIDH0OOkk3rE2gAfHb4IwHjQFjdJzufx";//"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSGblnNzOXmNE5Lylm6UZaSsT1e06m++uWfOdLjnNcYfo2UuzCPfcOGjDk3fq4qxaLvF/mxrVaJSs3KSxSOk9/eFonyqdXKynaLwCn3E+qYv1kI3mB2QXg8EFzlVr9crYyzzjhRuTOGzD4DM/TES+GrljwMt4sPXDIuWpk+FNABg7Fgp2GQ+JDPjHh2ngnWjeDRiIn/GZIY429cW3LB4RMZCzwADnD8gEdwc6UoPzY4swylHyvtFRMm3xg/CAzy/gRNyi/ETaf+C28kRw+1XZDQXiBsLkCto8JtSnG7ecGS86W1m2iByQzcuGLEseXsG8MXSGGOo2RwNr8v6uFn3brAgMBAAECggEAeEGkUkkltkqbLK6dLZAl0Hxq0oTEoL4LlLsKxy5h1lDvW3e2WcwXZww34qq0jPyGek57wcEd1S1y/NxvFt/YP9wYmVg9lYp50JWPvJujF2STnbF1emPgat5XgYQWeTfKsNfQwd1QyDWpgQNKrTkgHYO0wUGdjlykURQNdz7Ph4ozy4Zj6WadQrdgon8QfFaDbUQqRxg2FkpiW2U5fAQn3PSvp+U8omrY4kdYPaeCQbnBPbKiykmbLjDnQmKAEIy892NmBVL2gdkWeTCyxzheihMtbs9q8CyPcWnqgwPJsT6gJsPbzMEkhTIyBXjbX+SdIKkYg2HrizgFo0pT5SxjQQKBgQDcmVa+mlfyMEI5jGz0Xyd+dBrnbjmjTPNfN8aJY4X14s4hqekw5aow5DTAc61TpqHo3DB2twZmJxK2p/WqZ1XqeG6Zoea1mHEaRiXViktvtgAZr4DvD+njXhrLEofcTtf/8wFIVJjeiMolBTUWbZtoXh6zitro7x9YhM4+8xu1FQKBgQCpi9VjdZwaZi6jSFTJA7uDUgcWrlwAwkKey8qbqCwT+BYUP/RQF8MvV224zAZNY+5WX/o/68nPDdMVIWvkTRpNgsMHRUpqJsZmjsv0eQhYxeQ0tUWmCQL2eLkaa2/3aAcHrR11GkSeP2ATFdrNe6GzoCETrd/4jVp49iPO1DF7/wKBgQCeA7h/D0+vy6GkV1I0OXSwv7oFzI68yoIJ0ooEH6X1W2EL/AltXRVrsAuZT0L4iIOxqQeOAcwzoAlnjSgLx7ljGqEeqzn3GhrqFxVCwK0Id/XacDyLAXsnwmCu1bc7t75BOG/Sv8ZAoPsrSW+dgSWwvLqBIRVthNuPLJIPxVQzgQKBgC33Y1sbv77IhUxvh80X3fa0fod/riHmiPlUdxHMfmbLk/DZhftQ9fIX2f+IEQ1aUnvIeahq3MPuE5RfA0laPg9VrFqERrCQ4qlbfaBCUCkBPeWsX72sI8l+4XBwQGLrKTzDioD9Ji1hMUL7Yd7j7estNsqHEXnpmOXCleK1VNUxAoGBAK47+77jCqY9yHAejNU1ehvBW4nbDy+5+EOHtkQVSs9WAQV1i9Plm49M/bzwdllcg+tRc3Hhk5G8iAHRXB6XK8uLI/JKADvuoD+JkcMOmbnfQd5obOpFfXRxiCr3ILTdqDH4H9yKWmioHEcw7/wT5ZelaOJEhPIcDi3cQcjoYJNI";

    // 声明一个就行
    OpenClient client = new OpenClient(url, appId, privateKeyIsv,"http://www.jcex.com");

    String accessToken = "4fa2b9f95181dbf6ab8c7";


    /**
     * 获取Token
     */
    @Test
    public void testToken() {

        String code = "77dcc697ae95d624b4146b4f";

        OpenAuthTokenAppRequest request = new OpenAuthTokenAppRequest();
        OpenAuthTokenAppModel model = new OpenAuthTokenAppModel();
        model.setCode(code);
        model.setGrant_type("authorization_code");
        request.setBizModel(model);

        // 根据code获取token
        OpenAuthTokenAppResponse response = client.execute(request);
        if (response.isSuccess()) {
            // 成功拿到token，开发者在这里保存token信息
            // 后续使用token进行接口访问
            System.out.println("返回："+response.getBody());
        }
    }

    /**
     * 获取佳成运单信息
     */
    @Test
    public void testWayBillList() {
        CommonRequest request = new CommonRequest("jcex.tms.waybill.list");
        // 发送请求
        WayBillRequest wayBillRequest = new WayBillRequest();
        wayBillRequest.setPage(1);
        wayBillRequest.setSize(10);


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
        wayBillRequest.setBeginDate(calendar.getTime());

        wayBillRequest.setEndDate(new Date());
        request.setBizModel(wayBillRequest);

        //注意后面需要accessToken,accessToken 对应到登陆账号...
        CommonResponse response = client.execute(request,accessToken);
        System.out.println("返回："+response.getBody());
    }

    @Test
    public void testWayBillInfo() {
        CommonRequest request = new CommonRequest("jcex.tms.waybill.info");
        Map<String, Object> bizModel = new HashMap<>();
        bizModel.put("hawbcode", "JCTES20200921001_D168");
        request.setBizModel(bizModel);
        CommonResponse response = client.execute(request,accessToken);
        System.out.println("返回："+response.getBody());
    }

    /**
     * 获取账号信息
     */
    @Test
    public void testAccountInfo() {
        CommonRequest request = new CommonRequest("jcex.tms.account.info");
        //request.setBizModel(wayBillRequest);
        //注意后面需要accessToken,accessToken 对应到登陆账号...
        CommonResponse response = client.execute(request,accessToken);
        System.out.println("返回："+response.getBody());
    }

    //OpenClient client = new OpenClient(url, appId, privateKeyIsv);
    /**
     * 获取佳成所有产品
     */
    @Test
    public void testProduct() {
        CommonRequest request = new CommonRequest("jcex.tms.product.get");
        // 发送请求
        CommonResponse response = client.execute(request);
        System.out.println("返回："+response.getBody());
    }

    /**
     * 分页获取产品
     */
    @Test
    public void testGetProductByPage() {

        CommonRequest request = new CommonRequest("jcex.tms.product.page");
        //请求参数
        ProductRequestPageDTO productRequestPageDTO = new ProductRequestPageDTO();
        productRequestPageDTO.setPage(1);
        productRequestPageDTO.setSize(10);
        request.setBizModel(productRequestPageDTO);

        // 发送请求
        CommonResponse response = client.execute(request);
        String body = response.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        System.out.println(jsonObject);
    }


    @Test
    public void testSign() {
        CommonRequest request = new CommonRequest("jcex.tools.sign.create");

        Map<String, Object> bizModel = new HashMap<>();
        bizModel.put("content", "app_id=20200408697382963347718144&biz_content={}&charset=UTF-8&format=json&method=jcex.product.get&sign_type=RSA2&timestamp=2020-05-08 10:28:06&version=1.0");
        bizModel.put("private_key", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCpjDxUyptwd/b46iZ5v+8tN24dnvRe6LzAbrNcxuNZRTuuSC1QgytbxQFSI/BLvDsAuHKPZQhG72Yi+QCOf9o7CMwWOPpYDOqdZe/fFc0+YsphnauTjjmjU1xioQLu4GbeTvPTmv2NRIumOjIsTrRBvt3fYhgAUOrALRieMYzUf8y4eBv1LqssbPfw7KeWG5fFFabkSdYv7SeChIMlq0norh3zd3j23IknmL5IhU9p136mJz6cuy9/Pm3h2aHZ8QvdAc0AegG/OoRYdqyzDsB57kFVi3+IBDL5aTAdebA0Gn3NiZ2GYaMX4OLAZAQPdhgcyZ+yt+Go2x0X/oV3X0GHAgMBAAECggEAekT1pThPjHYXFz7U7Gwr8fHNaXMeS+uPYz7x/nA3uEMjXPYUJ8HQXOffofrk6dkeu9BIXXzA0VcH0yS68AjnujcH5ubEMwdtkoDW0YyJbPoVdPwdpdWlX1FYiJAQ62/M/3j0gZ9+RoguJXxGfolrmRYqL1Qwdtnj0R80Z7DYHM22X2eJRqi5l/tkQs+RzT2hNhJWlRrKE3c2AC0dEOZlwh/mHRDbTvc6elO5hd1ASIswC8+1qWa3LwjHuzXZvppkvX+HbHjDnQWMnnrUc9G49h6BRSztSO5GGDVOhIuPjNdWYWbh9ZmXBMkRk0PI1WG9FTCSzdhPL6SP12DagXmasQKBgQDsGX7U8eM4ohOX94WuyRD5TZav0ZSJxTkc7LwV7WqRu4Eb/kDKEfIeEF1625Qa5N+N80n9sfbFv/W283JM9m75WsD8tBsU1yFogbnF1iwpUy4ojHhlJGZ+ukeop0NIRW+8+dN8ajGZSm4wSlKQTE6d6Lc945/ufwW1M6N0gZTDuwKBgQC31rNKgs1Ct/64nFAvsEK7kQK549jYxU4wsx/EqlyytrSbGhs4CzgVsd/t43xsEsR6rRSlkAPnSLQnhWN7CnawrKy1Jnbdy6IFYaI5sK9CJNX2Kgfrst0NHqrCsttD+YGaHam9VV4sdLv4msOHO/EMd/crRbCQEne71AP09xyupQKBgFwoE36xMB0R/o3p2u9/ojV9mJzmNJGWPxXQPhZdBqT9yIaRtt5pJNWhU6u5+9SHk89HsJDrH0TPCe0wl2P9KRIB36SD9LIsyuiNAmN7lt9GjZTcR75K+ArtUT0+lJ+oItZjua4tkVChYeNNj26tql4u0R/CMtIYGLVDA8FdNyEzAoGBALAohK8efa+z/dpKtq+rt5ZiEvmrcivtjMovqn5aPO9nEdMaBgEDByAKzSf3907cAPgIiCNJx+PFEBRaxgOt+CO9otUG4ZoQudVsQ/YB7z5za68xgVuCjj03KKvelSsil+TlDbSGa/IvfbtwGXKppoarIeaOJe04NRF/zLbcX9idAoGBAIwBG6yAQqwMz18vso3sLZaiEw5uqiCVCoR5Piejv4xtJExg8n/2KPxpVqEhf5F3ARxM6Oc6KKt7M+ArQ8Gm+czEznFga0Ymbo3NRt0pm/+C+XlJi7r6Mu7hsh/KxvwC0Bwt+SpXhh2zSPL0UP2lLA9Sjq9C1zQSFXoYo28+7dDg");
        request.setBizModel(bizModel);

        // 发送请求
        System.out.println("result:");
        CommonResponse response = client.execute(request);
        System.out.println(response.getBody());
    }


    /**
     * 获取转单号
     */
    @Test
    public void testOrderTransferNumber() {

        CommonRequest request = new CommonRequest("jcex.order.transfernumber.get");
        //请求参数
        OpenFindTransferNumberRequest bizModel = new OpenFindTransferNumberRequest();
        bizModel.setWaybill_number("JCS0708117022INxx");
        request.setBizModel(bizModel);

        // 发送请求
        CommonResponse response = client.execute(request);
        String body = response.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        System.out.println(jsonObject);
    }


    /**
     * 查询标签
     */
    @Test
    public void testOrderLabel() {

        CommonRequest request = new CommonRequest("jcex.order.lable");

        //请求参数
        OrderLabelRequest orderLabelRequest = new OrderLabelRequest();
        orderLabelRequest.setWaybill_number("JCT0509064270INtttt");

        request.setBizModel(orderLabelRequest);

        // 发送请求
        CommonResponse response = client.execute(request);

        if (response.isSuccess()) {

            // 返回结果
            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println(jsonObject);

        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }

    /**
     * 获取标签
     */
    @Test
    public void testGetOrderLabel() {

        CommonRequest request = new CommonRequest("jcex.order.lable.get");
        //请求参数

        OpenLabelRequest openLabelRequest = new OpenLabelRequest();
        openLabelRequest.setLabel_type("10_10");
        openLabelRequest.setWaybill_number(Collections.singletonList("23456543"));

        request.setBizModel(openLabelRequest);

        // 发送请求
        CommonResponse response = client.execute(request);

        if (response.isSuccess()) {

            System.out.println("调用成功...");

            // 返回结果

            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println(jsonObject);

        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }
    /**
     * 创建订单
     */
    @Test
    public void testOpenCreateOrder() {

        CommonRequest request = new CommonRequest("jcex.order.create");
        //请求参数
        String createOrderRequest="{\"packages\":{\"detail_package\":[{\"actual_weight\":2}],\"invoice\":[{\"chinese_name\":\"连衣裙\",\"declaration_amount\":10,\"declaration_currency\":\"USD\",\"english_name\":\"skirt\",\"inpieces\":1,\"unit_price_amount\":10.0}],\"plat_number\":\"ZY_DHL\",\"recipient\":{\"recipient_address\":\"Platinastraat 71\",\"recipient_city\":\"Zoetermeer\",\"recipient_country\":\"NL\",\"recipient_email\":\"\",\"recipient_name\":\"NL-AE\",\"recipient_phone\":\"3165499531\",\"recipient_postcode\":\"2718 ST\"},\"reference_number\":\"D20200627005540\",\"sender\":{\"sender_address\":\"Platinastraat 71\",\"sender_city\":\"Zoetermeer\",\"sender_country\":\"NL\",\"sender_name\":\"NL-AE\",\"sender_phone\":\"3165499531\",\"sender_postcode\":\"2718 ST\"},\"special_service\":[{}],\"waybill_number\":\"D20200627005540\",\"weight\":{\"total_packages\":1,\"total_weight\":2}},\"product_id\":\"PK2169\"}";
        //String createOrderRequest="{\"JcexKey\":null,\"PlatName\":null,\"ProductId\":\"JCEX-001\",\"Packages\":{\"FbaCode\":null,\"FbaPoId\":null,\"FbaShipmentId\":null,\"OutBoundTime\":null,\"PlatNumber\":null,\"ReferenceNumber\":\"10819439-58561779-PV\",\"Update\":null,\"WaybillNumber\":null,\"DetailPackage\":[{\"ActualWeight\":10,\"ChildNumber\":\"10819439-58561779-PV\",\"FbaNumber\":null,\"Height\":null,\"Item\":[{\"ChineseName\":null,\"DeclarationAmount\":10000.00,\"DeclarationCurrency\":\"USD\",\"EnglishName\":\"佳成\",\"goods_id\":null,\"Hscode\":null,\"Inpieces\":2,\"InvoiceMaterialCn\":null,\"InvoiceMaterialEn\":null,\"InvoiceUrl\":null,\"InvoiceUseCn\":null,\"InvoiceUseEn\":null,\"InvoiceWeight\":null,\"MaterialQuality\":null,\"MeasurementUnit\":null,\"PmUrl\":null,\"ProductName\":null,\"ProductSku\":null,\"Purpose\":null,\"Sku\":null,\"SpecificationModel\":null,\"UnitPriceAmount\":5000.00,\"WarehouseCode\":null}],\"Length\":null,\"TrackNo\":null,\"Volume\":null,\"VolumeWeight\":null,\"Width\":null}],\"Invoice\":[{\"ChineseName\":null,\"DeclarationAmount\":10000.00,\"DeclarationCurrency\":\"USD\",\"EnglishName\":\"佳成\",\"GoodsId\":null,\"Hscode\":null,\"Inpieces\":2,\"InvoiceMaterialCn\":null,\"InvoiceMaterialEn\":null,\"InvoiceUrl\":null,\"InvoiceUseCn\":null,\"InvoiceUseEn\":null,\"InvoiceWeight\":null,\"MaterialQuality\":null,\"MeasurementUnit\":null,\"PmUrl\":null,\"ProductName\":null,\"ProductSku\":null,\"Purpose\":null,\"Sku\":null,\"SpecificationModel\":null,\"UnitPriceAmount\":5000.00,\"WarehouseCode\":null}],\"Recipient\":{\"RecipientAddress\":\"Blk 717 Bedok Reservoir Road  #10-4546\",\"RecipientCertificateCode\":null,\"RecipientCity\":\"Singapore \",\"RecipientCompany\":null,\"RecipientCountry\":\"SG\",\"RecipientDutyParagraph\":null,\"RecipientEmail\":null,\"RecipientHouseNumber\":\"Blk 717 Bedok Reservoir Road  #10-4546\",\"RecipientMobile\":null,\"RecipientName\":\"Leong Mabel\",\"RecipientPhone\":\"+65 9438 6100\",\"RecipientPostCode\":\"470717\",\"RecipientState\":null,\"RecipientTown\":null},\"Sender\":{\"SenderAddress\":\"No. 500, Kenhui 6th Road, Xiaoshan District, Hangzhou, Zhejiang _-_Hangzhou City_ZJ_CN\",\"SenderChineseName\":null,\"SenderCity\":\"Hangzhou City\",\"SenderCompany\":null,\"SenderCountry\":\"CN\",\"SenderCustomsOperatingUnits\":null,\"SenderCustomsRegistrationCode\":null,\"SenderEmail\":null,\"SenderName\":\"onearts warehouse\",\"SenderPhone\":\"+8615657132076\",\"SenderPostCode\":\"310000\",\"SenderProxyCode\":null,\"SenderState\":null,\"SenderTown\":null},\"SpecialService\":[],\"Weight\":{\"ItemType\":null,\"TotalChargeableWeight\":null,\"TotalPackages\":2,\"TotalVolumeWeight\":null,\"TotalWeight\":10,\"WeightMethod\":null}}}";

        //System.out.println(createOrderRequest);
        Map<String, Object> bizModel = JSONObject.parseObject(createOrderRequest, new TypeReference<Map<String, Object>>(){});

        request.setBizModel(bizModel);


        // 发送请求
        CommonResponse response = client.execute(request);

        if (response.isSuccess()) {

            System.out.println("调用成功...");

            // 返回结果
            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println("pda.scan.order.search");
            System.out.println(jsonObject);

        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }




    /**
     * 获取订单信息
     */
    @Test
    public void testGetOrderInfo() {
        CommonRequest request = new CommonRequest("jcex.order.info");
        //请求参数

        OpenOrderInfoRequest openOrderInfoRequest = new OpenOrderInfoRequest();

        List<String> waybillNumberList = new ArrayList();
        waybillNumberList.add("23456543");
        waybillNumberList.add("JCS0708117022IN");
        openOrderInfoRequest.setWaybill_number(waybillNumberList);
        request.setBizModel(openOrderInfoRequest);
        // 发送请求
        CommonResponse response = client.execute(request);
        if (response.isSuccess()) {

            System.out.println("调用成功...");
            // 返回结果
            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println(jsonObject);

        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }



    /**
     * 查询轨迹
     */
    @Test
    public void testOrderTrack() {
        CommonRequest request = new CommonRequest("jcex.order.track");
        //请求参数
        OpenTrackingRequest openTrackingRequest = new OpenTrackingRequest();
        openTrackingRequest.setWaybill_number("JCS0708116824IN");
        request.setBizModel(openTrackingRequest);
        // 发送请求
        CommonResponse response = client.execute(request);
        if (response.isSuccess()) {

            System.out.println("调用成功...");

            // 返回结果

            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println(jsonObject);

        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }

    /**
     * 修改订单
     */
    @Test
    public void testOpenUpdateOrder() {
        CommonRequest request = new CommonRequest("jcex.order.update");
        //请求参数
        String createOrderRequest="{\"product_id\":\"JC-EUEXPHU\",\"packages\":{\"waybill_number\":\"JCT0107040320IN\",\"plat_number\":\"\",\"reference_number\":\"TEST201912041012\",\"service_type\":null,\"sender\":{\"sender_name\":\"wangweizeng\",\"sender_chinese_name\":\"\",\"sender_company\":\"qihuang\",\"sender_phone\":\"18923661778\",\"sender_country\":\"CN\",\"sender_city\":\"Quirinópolis\",\"sender_town\":\"shantou\",\"sender_postcode\":\"515000\",\"sender_address\":\"Chang Hua Ya Ting Building ,No,8 of Nan He Lane 6, Tai He Street\",\"sender_email\":\"\"},\"recipient\":{\"recipient_name\":\"mireille fravega\",\"recipient_phone\":\"686335197\",\"recipient_country\":\"ES\",\"recipient_postcode\":\"51002\",\"recipient_city\":\"Ceuta\",\"recipient_state\":\"Ceuta\",\"recipient_town\":\"\",\"recipient_address\":\"calle almeria nº2 3B ceuta, ceuta 51002 Spain\",\"recipient_company\":\"\"},\"invoice\":[{\"chinese_name\":\"玩具\",\"english_name\":\"toy\",\"hscode\":\"9503006000\",\"inpieces\":\"1\",\"unit_price_amount\":\"0\",\"declaration_amount\":\"18\",\"declaration_currency\":\"USD\"}],\"weight\":{\"weight_method\":null,\"total_packages\":\"1\",\"item_type\":\"\",\"total_weight\":\"0.3\"},\"detail_package\":{\"actual_weight\":\"0.3\",\"length\":\"0\",\"width\":\"0\",\"height\":\"0\",\"volume\":\"0\",\"volumeweight\":\"0\",\"item\":[]},\"special_service\":[{\"service_name\":null,\"cost_amount\":0,\"costcurrency\":null,\"description\":null}]}}";
        Map<String, Object> bizModel = JSONObject.parseObject(createOrderRequest, new TypeReference<Map<String, Object>>(){});
        request.setBizModel(bizModel);
        // 发送请求
        CommonResponse response = client.execute(request);

        if (response.isSuccess()) {

            System.out.println("调用成功...");

            // 返回结果

            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println("pda.scan.order.search");
            System.out.println(jsonObject);

        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }
    /**
     * 查询费率
     */
    @Test
    public void testGreight() {
        CommonRequest request = new CommonRequest("jcex.greight.get");
        //请求参数
        List<String> waybillNumber = new ArrayList();
        waybillNumber.add("994150776");
        waybillNumber.add("994150802");
        Map<String,Object> map=new HashMap();
        map.put("waybill_number",waybillNumber);
        request.setBizModel(map);
        // 发送请求
        CommonResponse response = client.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功...");
            // 返回结果
            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println(jsonObject);
        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }

    /**
     * 查询订单信息
     */
    @Test
    public void testOrderInfo() {
        CommonRequest request = new CommonRequest("jcex.order.get");
        //请求参数
        List<String> waybillNumber = new ArrayList();
        waybillNumber.add("JCT0703004344IN");
        Map<String,Object> map=new HashMap();
        map.put("waybill_number",waybillNumber);
        request.setBizModel(map);
        // 发送请求
        CommonResponse response = client.execute(request);
        if (response.isSuccess()) {

            System.out.println("调用成功...");
            // 返回结果
            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println(jsonObject);

        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }
}
