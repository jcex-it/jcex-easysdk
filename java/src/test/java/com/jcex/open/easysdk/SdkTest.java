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

    String appId= "202005087083501681639424110";

    String privateKeyIsv = "MIIEvQIBADANdBgkqhkiG9w0BAQEFAASCBKcwgdgSjAgEAAoIBAQCfLM4aOzE/5NokbxiO2juoKm71iULl9Ue+T/QcsY0Z4JCm3r1Ub6i97AOamCEYnI1YhM6D3WCKnLERQPh5HWQwwLqt9Hb5hG6zPJJA+rJuohPgvtZKJpDKlmbRV0qFznV6bM1DG0jxX4rS2Onesl/nC0Zgy601ffDYURt3TlzL4KMWdKOE/L0COLYPywamTi1z/TTs0BchesWGmgAXyF1wZX59cLqnn+RGec0viJR2p5x6yjVaAwUJzCgggDzqeOk+FsO6q1V5LKHoVhV7W4H7WtItsN1zxqKn+b91naNp+keNup2B28qRKOQO0kcrItKSrQD6sBYyBW/Mxe32IzVVAgMBAAECggEAHnHcben6boUgMC3Lna8iD/UlyfLaB8/VTBJQ15giK9vm7vPVdKvys3jL66rDUcjHxDGKqnASQhDcUTeDDu9PNX/3dMQ4aVsqLUAdnS4WKND5SlhV9lLQCN+hhNLUXylxENx0mixC1F/41oFEoiKt8ywV5veuBngzM7y3qyci2p+sdNWxP/dCF4zdJMunoeuhbjMaWv0+dYdohN1SY/nk7AW1XN0LiRsB02OUJ0MB2iFDVLEZl5vpZ1fZPCw5pHbQlZKYC9FaFPSZbyQqjlW+XzYv0ukyyQiJ9g1ZpXECIOBlnVW5twvK3LYjFZM5fpU9KocRXgmO9HN3aRcToPrrYQKBgQDOSUAJf2sFonXINsGtXUlNZfJ9Q7r3nJFUjGiwABtbVnP60FE8IqbJ1FIhxnKOMec/DTvd7s6Gcaos6yQicbjcEldLk8t5sPOq7R3f357d9gywDRRJGT/bYWLOYm/2j72z4ANH+NCY1EjlyM0MHug9Olr+8dDxvee62konF+wFXQKBgQDFiQ0vEw7tarBE1eVAvuToBfGLkNQROlSZTorHrrJcbuOzX03DoI2eks2DHpnKSLDQU+AwuTW9c1NfTNtnEEc1apXVwvpzef09kWBTdM4MA73dpc8M7Nlt/tu7Xf6HF9I0bppKt0VHVbgnCxgkCQHqScuBY6HVUKIMrBi2I/04WQKBgFzB6U6cAealt6qQnKQ050orRk93TjIbL+pBvK0vTx3SERK961lMB42HsGrJjidMXzwl2j6gLDOeFrReA4CNfF9Qy/oK+hq9K8Q3HRDWv9v00J21M+LuvJ0L/F7rOoc2+UlRuIf7ZOjjw+e3VCRYHXuyqsoquBaLmxCVKXTMt+9tAoGAU6W9l3mSkKblDcEl20z60eXyaw9hPgpdWK4Rxo0Lqmiqs4JgA78xOT0hGa0Tor01YoKiJP/I6fi0DV8WaYZTISOJX4O5Wr0Fp9Sv3adQuXW8KOfVzbteN1383lLnPX2w3dMYMxt4ksSRvrbL+jZ2QSgOrTPri9EhTgxySm7jA8ECgYEAr/8sL4EfDteFuUUQHt+a0nTv009TjypGbBkvZA5Jk9Ng1MebLNpbH3S9q8e651aKypX7LvD/RGdZAiX0kbjIDvxTFpnQ8gi39xFpLYbom9KO9NZqWo52dyqQw+Cc2q0Q2t3e0eAaHxKxUME9gdm6PIrVXOjRnEgOxszzbOZeeVY=";

    // 声明一个就行
    OpenClient client = new OpenClient(url, appId, privateKeyIsv,"http://www.jcex.com");

    String accessToken = "0ae3d20fb49e0acf91b3c7900be10888";

    /**
     * 获取Token
     */
    @Test
    public void testToken() {

        String app_id = "20200928760094870181249024";
        String code = "3f3e7d4a31ac47ecd13854aa6b1ff9e0";


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
        CommonRequest request = new CommonRequest("jcex.product.get");
        // 发送请求
        CommonResponse response = client.execute(request);
        System.out.println("返回："+response.getBody());
    }

    /**
     * 分页获取产品
     */
    @Test
    public void testGetProductByPage() {

        CommonRequest request = new CommonRequest("jcex.product.page");
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
