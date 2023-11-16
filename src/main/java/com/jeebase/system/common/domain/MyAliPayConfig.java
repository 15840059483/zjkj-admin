package com.jeebase.system.common.domain;

import com.alipay.api.domain.AlipayAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyAliPayConfig extends AlipayAccount {

    private static final long serialVersionUID = 1L;

    // 商户appid
    @Value("${alipay.appid}")
    private String appid;

    // JSON
    @Value("${alipay.format}")
    private String format;

    // 字符集 utf-8
    @Value("${alipay.charset}")
    private String charset;

    // 签名格式 RSA2
    @Value("${alipay.signType}")
    private String signType;

    // 支付宝网关
    @Value("${alipay.serverUrl}")
    private String serverUrl;

    // 私钥
    @Value("${alipay.myPrivateKey}")
    private String myPrivateKey;

    // 支付宝公钥
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;

    // 服务器异步通知页面路径
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;

    // 页面跳转同步通知页面路径
    @Value("${alipay.returnUrl}")
    private String returnUrl;

    // 支付日志路径
    @Value("${alipay.logPath}")
    private String logPath;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getMyPrivateKey() {
        return myPrivateKey;
    }

    public void setMyPrivateKey(String myPrivateKey) {
        this.myPrivateKey = myPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    @Override
    public String toString() {
        return "MyAliPayConfig [appid=" + appid + ", format=" + format + ", charset=" + charset + ", signType="
                + signType + ", serverUrl=" + serverUrl + ", myPrivateKey=" + myPrivateKey + ", alipayPublicKey="
                + alipayPublicKey + ", notifyUrl=" + notifyUrl + ", returnUrl=" + returnUrl + ", logPath=" + logPath
                + "]";
    }

}
