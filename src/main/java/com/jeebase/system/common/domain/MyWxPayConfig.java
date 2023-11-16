package com.jeebase.system.common.domain;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;


@Component
public class MyWxPayConfig extends WXPayConfig{
	
	@Value("${weixin.api.appId}")
	private String AppID;

	@Value("${weixin.api.secret}")
	private String Secret;
	
	@Value("${weixin.api.mchid}")
	private String MchID;
	
	@Value("${weixin.api.key}")
	private String Key;

	public String getSecret() {
		return Secret;
	}

	public void setSecret(String secret) {
		Secret = secret;
	}

	@Override
	public String getAppID() {
		// TODO Auto-generated method stub
		return AppID;
	}

	@Override
	public String getMchID() {
		// TODO Auto-generated method stub
		return MchID;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return Key;
	}

	@Override
	public InputStream getCertStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWXPayDomain getWXPayDomain() {
		// TODO Auto-generated method stub
		IWXPayDomain iwxPayDomain = new IWXPayDomain() {
		    
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
        
            }
    
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
	}

	@Override
	public String toString() {
		return "MyWxPayConfig [AppID=" + AppID + ", MchID=" + MchID + ", Key=" + Key + "]";
	}

}
