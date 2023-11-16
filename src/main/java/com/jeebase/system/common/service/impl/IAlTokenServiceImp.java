package com.jeebase.system.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.jeebase.system.common.domain.MyAliPayConfig;
import com.jeebase.system.common.service.IAlTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class IAlTokenServiceImp implements IAlTokenService {

    @Autowired
    MyAliPayConfig config;


    @Override
    public AlipaySystemOauthTokenResponse alipaySystemOauthTokenRequest(String code) {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",config.getAppid(),config.getMyPrivateKey(),"json","GBK",config.getAlipayPublicKey(),"RSA2");

        System.out.println("code"+code);
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        //request.setRefreshToken("authbseBfe9c6bf70b93452188dddd79e8711X37");
        request.setCode(code);
        //request.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSON(response.getBody()));
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return response;
    }
}
