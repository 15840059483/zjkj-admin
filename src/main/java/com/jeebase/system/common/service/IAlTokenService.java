package com.jeebase.system.common.service;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;

public interface IAlTokenService {

    /**
     * 获取token
     * @param code
     * @return
     */
    AlipaySystemOauthTokenResponse alipaySystemOauthTokenRequest(String code);
}
