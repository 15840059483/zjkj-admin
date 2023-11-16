package com.jeebase.system.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.jeebase.system.common.entity.ImgIDCardInfo;

/**
 *
 * @author fyy
 */
public interface ISmsService {


    /**
     * 短信发送
     * @param request
     */
    void sendSms(SendSmsRequest request);

    /**
     * 短信查询
     * @param request
     * @return
     */
    QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request);

    /**
     * 发送告警短信
     * @param jsonParam
     */
    void sendWarnSms(String jsonParam);

    /**
     * 发送短信验证码
     * @param phoneNumbers
     * @param smsCode
     */
    void sendVcodeSms(String phoneNumbers, String smsCode);
    
    /**
     * 身份证识别信息
     * @param file
     * @return
     */
    ImgIDCardInfo idCardImgDiscern(MultipartFile file,String direction);
}
