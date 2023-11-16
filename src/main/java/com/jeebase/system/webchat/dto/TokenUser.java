package com.jeebase.system.webchat.dto;


import com.alipay.api.response.AlipayUserInfoShareResponse;

public class TokenUser {
    
    private boolean isReg;

    private boolean remember;
    
    private String openId;
    
    private String token;

    private Long userType;

    private String alipayUserId;

    private String aliUserId;

    private AlipayUserInfoShareResponse aliUserInfo;

    public AlipayUserInfoShareResponse getAliUserInfo() {
        return aliUserInfo;
    }

    public void setAliUserInfo(AlipayUserInfoShareResponse aliUserInfo) {
        this.aliUserInfo = aliUserInfo;
    }

    public String getAliUserId() {
        return aliUserId;
    }

    public void setAliUserId(String aliUserId) {
        this.aliUserId = aliUserId;
    }

    public String getAlipayUserId() {
        return alipayUserId;
    }

    public void setAlipayUserId(String alipayUserId) {
        this.alipayUserId = alipayUserId;
    }

    public boolean isReg() {
        return isReg;
    }

    
    public void setReg(boolean isReg) {
        this.isReg = isReg;
    }



    public String getOpenId() {
        return openId;
    }

    
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    
    public String getToken() {
        return token;
    }

    
    public void setToken(String token) {
        this.token = token;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }
}
