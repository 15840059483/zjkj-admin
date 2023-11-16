package com.jeebase.system.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.jeebase.system.common.domain.MyAliPayConfig;
import com.jeebase.system.common.service.IAlApiService;
import com.jeebase.system.common.service.IAlTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IAlApiServiceImp implements IAlApiService {

    @Autowired
    MyAliPayConfig myAliPayConfig;

    @Autowired
    IAlTokenService iAlTokenService;

    /**
     * 域名
     */
    @Value("${weixin.domain}")
    private String domain;


    @Override
    public String downloadBill() {
        return null;
    }

    @Override
    public AlipayEcoCityserviceCityserviceEnergySendResponse alipayEcoCityserviceCityserviceEnergy(String scene,String code) {

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",myAliPayConfig.getAppid(),myAliPayConfig.getMyPrivateKey(),"json","GBK",myAliPayConfig.getAlipayPublicKey(),"RSA2");
        AlipayEcoCityserviceCityserviceEnergySendRequest request = new AlipayEcoCityserviceCityserviceEnergySendRequest();

        JSONObject bizContent = new JSONObject();
        bizContent.put("scene", scene);//类型
        bizContent.put("outer_no", "3519760174fc9994f099b38e576ddbbc");//业务编码

        request.setBizContent(bizContent.toString());
        AlipayEcoCityserviceCityserviceEnergySendResponse response = null;

        AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse = iAlTokenService.alipaySystemOauthTokenRequest(code);

        if (!alipaySystemOauthTokenResponse.isSuccess()) {
            return null;
        }

        try {
            response = alipayClient.execute(request,alipaySystemOauthTokenResponse.getAccessToken());

            System.out.println(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

        return response;
    }

    @Override
    public AlipayTradeCreateResponse alipayTradeCreate(String out_trade_no, String total_amount, String buyer_id,String cost_type) {

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",myAliPayConfig.getAppid(),myAliPayConfig.getMyPrivateKey(),"json","GBK",myAliPayConfig.getAlipayPublicKey(),"RSA2");
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();

        String notify_url = "";

        switch (cost_type) {
            case "挂号缴费":
                notify_url = domain + "/notify/al/reg";
                break;
            case "门诊缴费":
                notify_url = domain + "/notify/al/outFee";
                break;
            case "住院缴费":
                notify_url = domain + "/notify/al/hospt";
                break;
            default:
                notify_url = domain;
                break;
        }
        request.setNotifyUrl(notify_url);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", out_trade_no);//单号
        bizContent.put("total_amount", total_amount);//金额
        bizContent.put("subject", cost_type);//名称
        bizContent.put("buyer_id", buyer_id);//用户Id
        bizContent.put("timeout_express", "10m");
        //bizContent.put("product_code", "JSAPI_PAY");//FACE_TO_FACE_PAYMENT

        request.setBizContent(bizContent.toString());
        AlipayTradeCreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        System.out.println(response.getBody());
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return response;
    }

    @Override
    public AlipayUserInfoShareResponse alipayUserInfoShareRequest(String accessToken) {

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",myAliPayConfig.getAppid(),myAliPayConfig.getMyPrivateKey(),"json","GBK",myAliPayConfig.getAlipayPublicKey(),"RSA2");
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;
        try {
            response = alipayClient.execute(request,accessToken);

            System.out.println(JSONObject.toJSON(response.getAvatar()));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return response;
    }

    public static void main(String[] args) throws AlipayApiException {
        String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/ctrIdwRcGnNATDrnGyRJncb3w2jLDO/j1d3YYn158JrizHuTXERA5BcLEr4hWu27/R7EmCt2DknG2oAaaRd7UQQE4NviU6cHn+9hJIUb79QJCBJfuL+jELVDvvyaV/qFtGcVhNLoWLQVIGIMqinDLtBkUqzKTMkT+If2brLfzeuzJPcPP86zdhKW7X8Zrb2gARt/qzpFq0cRtRi6/t+OogetULzN+UjNAWYsW+826c13I1XQfRm+2Z8pfzrUQVbb34oegJ/e5S5y54p4MlLner77rcdTcmslZsk3gs2faQeftvvkpTzHMQwp8y8X0Ih91kvQxb3HqAuo1R4XhbQkwIDAQAB";
        String private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCzxa9yjmLzM5Gmx8kYhGKeH1+tjPOdfRvqF4JqTVRuy6u4oHQa10LUWt28hOFMbZRoKRPtjBgXsp9MO7JUNIigO81bxqpTa1OR/8+K1thpyLnmOU7gMT0qW5eT+e1upBmPs2pl1DwsCPW0Rt0fuuPtzN9pF7E2DcnzXxSE8g2kDLakhW3n8gWUVEd+09QDGmk9L4eBp1D1yVjPxv+x3Kq90WGKdX5qP2IZwQ5wv6WZKCITvl3hhY51LGNSbcA5Bbo0sL2xzJlmEXwZboiNbwy/dPm3x7bTXk3RaZheCqscboZG4ffr7UIzW1QwoJ1UEinsY0Gh2xWYMr+mcpxNnCvTAgMBAAECggEBAJlRbrlfkCpkf8LivrHnLgg3L61e+eIF8kmqdTJd8zVvBGw4g+zkS326ZUPDfDZnelt/NRyyD1Yeo9crToKcLy2g8xpji54LecGZjxKU+wr1Av7DP27a75Rxewvb6lAjKh2xYXupDwxrM8nlae8GwB9uS9dgOmWkSukB3QkpOtL+Cr+wzFn3GnkjJzs0Ep5yCSSpnVh3nq2ejfriOWJKjVH37GzsTkODWlPMH8Q17XhDQ6Z3B1QTwd8q2ODGQYLiiGkh3+dh30StJROs8cTQ2y1cmGuUnVq+A9xII9p4f3OmbxpNtiWBSJfFVb+KC5RtzE6D+VVYdAcIqHAk+GLExOECgYEA21N7BgRzEKBqxr6oGkD4WBZgfQSGGM8uJxanpZlRlQki62oRaQkpAAFLcCeFnPfGhaxvM+mhyXCl3JEQPeYn063HjnjZaEd8t3dKLvatV0UOSzGMrpg5H6SAUR8l6RUD2HO5Wibzd4q+emgwP9YGcQpcJ0jX+KZYykTgZOTt+8MCgYEA0dUNe8bhpzP/7QGUNs3VGoF19gUDw3cNK1TvRvLHrq5xMfSxU9AAuWgl3sYAWQoGoEZpSSB157HPCPZhV2SdYyaQw5BsW4iztoJIO89F8qi/vWCGwa5OviSTR7zPbtam7YXJWd8x/8itylksbnIub8SgVDZU/u23iUtcKKJq3rECgYEAgOJduPuEYpDiLkh7oHHtflYn/U9bPdkB1haKDdY7CsmDq7N457BqV4ZrPkz7R3KTRpo9/tOcLzPzQ9o7ISTO4qWMTzzVBWJ895MyFMcAtffunmFPuonZh66zePhE3uNqjxfyI/gfum/d3zC6qciroGHmJW+4+nVGWBaBhsjA0OECgYBNWPnVbKR1nAd0+CYIZatuT9aJz6NphBMIrBBf2NKeo5L6VTagrIX30lpTWi16pOV4YzwgRqTvuW2V4ixTqLUTfX3TeQNYYnXMiw3RbkBciLPVPSWvc293S8g2o3TbaKiz2aM1S78EiJb3hhOIyvIvJQ3svPJr3njPVUYtfGFkoQKBgQCR40ZIlWP6++SUwg5Xdrwlrd75sDGDwHqUlQuIwFNUwXleCUg13eMtNQfnHfuNzI6DxDjx8uymDr59/iB0RJq0lRcLVjsu60Dnnh+8cX3ktoSzr8MGSooeaZxDfM7qc8dVxpm9YR1QDgwlnMyXBVFeTYe5OytIfwEl3djCMNzJcg==";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2021003146632241",private_key,"json","GBK",alipay_public_key,"RSA2");

//        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
//        request.setGrantType("refresh_token");
//        //request.setCode("b6a79d1f2a4440bf97dd0cb164a9TA42");
//        request.setRefreshToken("authbseBd4cab97ab4124bb2bdcc13d9f98afF42");
//        AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
//        System.out.println(JSONObject.toJSON(response.getBody()));
//        if(response.isSuccess()){
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }

        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        request.setNotifyUrl("");
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "2223233333222222");//单号
        bizContent.put("total_amount", 1);//金额
        bizContent.put("subject", "测试");//名称
        bizContent.put("buyer_id", "2088312982887420");//用户Id
        bizContent.put("timeout_express", "10m");
        //bizContent.put("product_code", "JSAPI_PAY");//FACE_TO_FACE_PAYMENT

        request.setBizContent(bizContent.toString());
        AlipayTradeCreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        System.out.println(response.getBody());
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
