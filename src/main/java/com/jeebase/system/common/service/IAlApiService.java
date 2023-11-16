package com.jeebase.system.common.service;

import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayEcoCityserviceCityserviceEnergySendResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;

public interface IAlApiService {

    public String downloadBill();

    /**
     * 支付宝城服绿色能量
     * @param scene 预约挂号（horegister）、报告查询（hoinquire）
     * @return
     */
    AlipayEcoCityserviceCityserviceEnergySendResponse alipayEcoCityserviceCityserviceEnergy(String scene,String code);

    /**
     *  统一下单
     * @param out_trade_no
     * @param total_amount
     * @param buyer_id
     * @return
     */
    AlipayTradeCreateResponse alipayTradeCreate(String out_trade_no,String total_amount,String buyer_id,String cost_type);

    /**
     * 获取支付宝用户信息
     * @param accessToken 支付宝token
     * @return
     */
    AlipayUserInfoShareResponse alipayUserInfoShareRequest(String accessToken);



}
