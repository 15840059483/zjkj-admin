package com.jeebase.system.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.Gson;
import com.jeebase.common.base.BusinessException;
import com.jeebase.system.common.domain.*;
import com.jeebase.system.common.entity.WeChatPlaceOrderResult;
import com.jeebase.system.common.service.IWechatApiService;
import com.jeebase.system.common.service.IWechatTokenService;
import com.jeebase.system.common.util.WeChatUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fyy
 */
@Service
public class WechatApiServiceImpl implements IWechatApiService {

    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(WechatApiServiceImpl.class);

    private static final String WEIXIN_TOKEN_KEY = "weiXin";

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private IWechatTokenService wechatTokenService;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private MyWxPayConfig config;

    /**
     * 域名
     */
    @Value("${weixin.domain}")
    private String domain;
    /**
     * 获取access_token的URL,有效期目前为2个小时
     */
    private String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appId}&secret={appSecret}";

    /**
     * 获取access_token的URL,有效期目前为2个小时
     */
    private String accessWebTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={appSecret}&code={code}&grant_type=authorization_code";

    /**
     * 微信授权获取用户信息
     */
    private String querySnsUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token={accessToken}&openid={openId}&lang=zh_CN";
    /**
     * 获取帐号下所有模板信息
     */
    private String queryTemplateListUrl = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token={accessToken}";

    /**
     * 发送微信模板信息
     */
    private String sendTemplateMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={accessToken}";

    /**
     * 获取用户增减数据(最大时间跨度7天)
     */
    private String queryUserSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token={accessToken}";

    /**
     * 获取用户总关注数(最大时间跨度7天)
     */
    private String queryUserCumulateUrl = "https://api.weixin.qq.com/datacube/getusercumulate?access_token={accessToken}";

    /**
     * 获取jsapi_ticket
     */
    private String queryJsApiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={accessToken}&type=jsapi";

    /**
     * 获取用户关注列表
     */
    private String queryAttentionUserList = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={accessToken}&next_openid={nextOpenId}";

    /**
     * 批量获取用户信息
     */
    private String queryUserInfoList = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token={accessToken}";
    
    
    
    /**
     * 挂号异步通知地址
     */
    public String getRegNotifyUrl() {
		return domain + "ww/";
	}
    
    /**
     * 门诊异步通知地址
     */
    public String getOutNotifyUrl() {
		return domain + "/notify/wechat/outFee";
	}
    /**
     * 住院异步通知地址
     */
	public String getHosNotifyUrl() {
		return domain + "/notify/wechat/hospt";
	}

	/**
     * 普通异步通知地址
     */
	public String getNotifyUrl() {
		return domain + "ww/";
	}




	/**
     * 从微信获取消息模板列表
     */
    @Override
    public MsgTemplateList queryTemplateList(String appId, String appSecret) {
        MsgTemplateList msgTemplateList = null;
        try {
            String accessToken = wechatTokenService.getAccessToken(appId, appSecret);
            msgTemplateList = restTemplate.getForObject(queryTemplateListUrl, MsgTemplateList.class, accessToken);
        } catch (Exception e) {
            logger.error("调用从微信获取消息模板列表接口异常：" + e);
            throw new BusinessException("调用从微信获取消息模板列表接口异常。");
        }
        return msgTemplateList;
    }

    /**
     * 发送模板消息
     */
    @Override
    public MsgSendResponse sendWeiXinMsg(String appId, String appSecret, MsgSendRequest msgSend) {
        MsgSendResponse msgSendResponse = null;
        try {
            String accessToken = wechatTokenService.getAccessToken(appId, appSecret);
            Gson gson = new Gson();
            String json = gson.toJson(msgSend);
            msgSendResponse = restTemplate.postForObject(sendTemplateMsgUrl, json, MsgSendResponse.class,
                    accessToken);
            logger.info("调用发送微信模板消息接口返回值：" + JSONObject.toJSON(msgSendResponse));
        } catch (Exception e) {
            logger.error("调用发送微信模板消息接口异常：" + e);
            throw new BusinessException("调用发送微信模板消息接口异常。");
        }
        return msgSendResponse;
    }

    /**
     * 获取关注的用户增减数据
     */
    @Override
    public SummaryUserList summaryUser(String appId, String appSecret, SummaryUserRequest suRequest) {
        SummaryUserList summaryUserList = null;
        try {
            String accessToken = wechatTokenService.getAccessToken(appId, appSecret);
            summaryUserList = restTemplate.postForObject(queryUserSummaryUrl, suRequest, SummaryUserList.class,
                    accessToken);
        } catch (Exception e) {
            logger.error("调用获取关注的用户增减数据接口异常：" + e);
            throw new BusinessException("调用获取关注的用户增减数据接口异常。");
        }
        return summaryUserList;
    }

    /**
     * 获取关注的用户总数
     */
    @Override
    public CumulateUserList cumulateUser(String appId, String appSecret, SummaryUserRequest suRequest) {
        CumulateUserList cumulateUserList = null;
        try {
            String accessToken = wechatTokenService.getAccessToken(appId, appSecret);
            cumulateUserList = restTemplate.postForObject(queryUserCumulateUrl, suRequest, CumulateUserList.class,
                    accessToken);
        } catch (Exception e) {
            logger.error("调用获取关注的用户总数接口异常：" + e);
            throw new BusinessException("调用获取关注的用户总数接口异常。");
        }
        return cumulateUserList;
    }

    /**
     * 从微信获取关注的用户列表
     */
    @Override
    public AttentionUserList queryAttentionUserList(String appId, String appSecret, String nextOpenId) {
        AttentionUserList attentionUserList = null;
        try {
            String accessToken = wechatTokenService.getAccessToken(appId, appSecret);
            attentionUserList = restTemplate.getForObject(queryAttentionUserList, AttentionUserList.class, accessToken,
                    nextOpenId);
        } catch (Exception e) {
            logger.error("调用从微信获关注用户列表接口异常：" + e);
            throw new BusinessException("调用从微信获取关注用户列表接口异常。");
        }
        return attentionUserList;
    }

    /**
     * 批量获取用户信息
     */
    @Override
    public UserListInfoResponse queryUserInfoList(String appId, String appSecret, UserListInfoRequest userListReq) {
        UserListInfoResponse userListInfoResponse = null;
        try {
            String accessToken = wechatTokenService.getAccessToken(appId, appSecret);
            userListInfoResponse = restTemplate.postForObject(queryUserInfoList, userListReq,
                    UserListInfoResponse.class, accessToken);
        } catch (Exception e) {
            logger.error("调用批量获取用户信息接口异常：" + e);
            throw new BusinessException("调用批量获取用户信息接口异常。");
        }
        return userListInfoResponse;
    }

    @Override
    @Cacheable(value = WEIXIN_TOKEN_KEY, key = "'jsapi_ticket_'.concat(#appId)")
    public String getJsapiTicket(String appId, String appSecret) {
        String ticketString = null;
        if (StringUtils.isEmpty(ticketString)) {
            JsapiTicket jsapiTicket = queryJsapiTicket(appId, appSecret);
            ticketString = jsapiTicket.getTicket();
        }
        return ticketString;
    }

    @Override
    public JsapiTicket queryJsapiTicket(String appId, String appSecret) {
        JsapiTicket jsapiTicket = null;
        try {
            String accessToken = wechatTokenService.getAccessToken(appId, appSecret);
            jsapiTicket = restTemplate.getForObject(queryJsApiTicketUrl, JsapiTicket.class, accessToken);
        } catch (Exception e) {
            logger.error("调用获取微信jsapi_ticket接口异常：" + e);
            throw new BusinessException("调用获取微信jsapi_ticket接口异常。");
        }
        return jsapiTicket;
    }

    @Override
    public QueryOpenIdResponse queryAppId(String appId, String appSecret, String code) {
        QueryOpenIdResponse queryOpenIdResponse = null;
        try {
            logger.info("获取openID时,code=" + code + "appId=" + appId + "secret=" + appSecret);
            queryOpenIdResponse = restTemplate.getForObject(accessWebTokenUrl,QueryOpenIdResponse.class, appId,
                    appSecret, code);
            logger.info("获取openID时接口返回值：" + JSONObject.toJSON(queryOpenIdResponse));
        } catch (Exception e) {
            logger.error("调用获取微信queryAppId接口异常：" + e);
        }
        logger.info("获取openID时openid=" + queryOpenIdResponse.getOpenid());
        return queryOpenIdResponse;
    }
    
    /**
     * 批量获取用户信息
     */
    @Override
    public SnsUserInfo querySnsUserInfo(String accessToken, String openId) {
        SnsUserInfo snsUserInfo = null;
        try {
            snsUserInfo = restTemplate.getForObject(querySnsUserInfoUrl, SnsUserInfo.class,
                     accessToken, openId);
            Object forObject = restTemplate.getForObject(querySnsUserInfoUrl, Object.class,
                    accessToken, openId);
            logger.info("获取值：" + forObject);
        } catch (Exception e) {
            logger.error("通过openid获取用户信息接口异常：" + e);
            throw new BusinessException("通过openid获取用户信息接口异常。");
        }
        return snsUserInfo;
    }

	@Override
	public WeChatPlaceOrderResult WeChatPlaceOrder(String fee ,String tradeType,String openId,String costtype) throws Exception {
		WeChatPlaceOrderResult result = null;
		
			
            WXPay wxPay = new WXPay(config);
            Map<String,String> reqData = new HashMap<>();

            String out_trade_no = WeChatUtil.getOutTradeNo();
            
            switch (costtype) {
			case "4010":
				reqData.put("body", "挂号缴费");
				reqData.put("notify_url", getRegNotifyUrl());
				break;
			case "4011":
				reqData.put("body", "门诊缴费");
				reqData.put("notify_url", getOutNotifyUrl());
				break;
			case "4012":
				reqData.put("body", "住院缴费");
				reqData.put("notify_url", getHosNotifyUrl());
				break;
			default:
				reqData.put("body", "普通缴费");
				reqData.put("notify_url", getNotifyUrl());
				break;
			}
            
    		reqData.put("out_trade_no", out_trade_no);
    		reqData.put("total_fee", fee);
    		reqData.put("spbill_create_ip", request.getRemoteAddr());
    		reqData.put("trade_type", tradeType);
    		reqData.put("openid", openId);

    		Map<String, String> fillRequestData = wxPay.fillRequestData(reqData);
    		
            Map<String, String> unifiedOrder = wxPay.unifiedOrder(fillRequestData);
            
            unifiedOrder.put("out_trade_no", out_trade_no);
            
            JSONObject json = (JSONObject)JSONObject.toJSON(unifiedOrder);
            result = JSONObject.toJavaObject(json, WeChatPlaceOrderResult.class);
        
		return result;
	}

	@Override
	public Map<String, String> webChatReturnFee(MyWxPayConfig config, String out_trade_no, String fee, String out_refund_no,
			String refund_fee) {
		// TODO Auto-generated method stub
		WXPay wxPay = null;
		Map<String, String> refund = null;
		 
		try {
			wxPay = new WXPay(config);
			Map<String,String> reqData = new HashMap<>();
			
			BigDecimal bigDecimal = new BigDecimal(fee);
			BigDecimal bigDecimal2 = new BigDecimal(refund_fee);
			BigDecimal bigDecimal3 = new BigDecimal(100);
			
			double parseDouble = bigDecimal.multiply(bigDecimal3).doubleValue();
			double refund_fees = bigDecimal2.multiply(bigDecimal3).doubleValue();
			
			reqData.put("notify_url", "http://fscrsapp.natapp1.cc/notify/wechatQR");
			reqData.put("out_trade_no", out_trade_no);
			reqData.put("out_refund_no", out_refund_no);
			reqData.put("total_fee", String.valueOf((int)parseDouble));
			reqData.put("refund_fee", String.valueOf((int)refund_fees));
			Map<String, String> fillRequestData = wxPay.fillRequestData(reqData);
	        refund = wxPay.refund(fillRequestData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return refund;
	}

    @Override
    public Map<String, String> webChatOrderFile(MyWxPayConfig config) {
        return null;
    }
}
