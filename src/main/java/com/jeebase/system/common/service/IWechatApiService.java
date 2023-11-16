package com.jeebase.system.common.service;

import com.jeebase.system.common.domain.*;
import com.jeebase.system.common.entity.WeChatPlaceOrderResult;

import java.util.Map;

/**
 *
 * @author fyy
 */
public interface IWechatApiService {

    /**
     * 
     * getJsapiTicket(从缓存获取ticket)
     *
     * @Title: getJsapiTicket
     * @Description: 因为微信的JsapiTicket有效期为2个小时，所以放到缓存，2小时取一次
     * @param appId
     * @param appSecret
     * @return
     */
    String getJsapiTicket(String appId, String appSecret);

    /**
     * 
     * queryJsapiTicket(获取微信的JsapiTicket)
     *
     * @Title: queryJsapiTicket
     * @Description: 因为微信的JsapiTicket有效期为2个小时，所以放到缓存，2小时取一次
     * @param appId
     * @param appSecret
     * @return
     */
    JsapiTicket queryJsapiTicket(String appId, String appSecret);

    /**
     * 
     * queryTemplateList(查询配置的模版列表)
     *
     * @Title: queryTemplateList
     * @param appId
     * @param appSecret
     * @return
     */
    MsgTemplateList queryTemplateList(String appId, String appSecret);

    /**
     * 
     * queryAttentionUserList(从微信获取关注的用户列表)
     *
     * @Title: queryAttentionUserList
     * @Description:
     * @param appId
     * @param appSecret
     * @param nextOpenId
     * @return
     */
    AttentionUserList queryAttentionUserList(String appId, String appSecret, String nextOpenId);

    /**
     * 
     * sendWeiXinMsg(给指定用户发送模版消息)
     *
     * @Title: sendWeiXinMsg
     * @param appId
     * @param appSecret
     * @param msgSend
     * @return
     */
    MsgSendResponse sendWeiXinMsg(String appId, String appSecret, MsgSendRequest msgSend);

    /**
     * 
     * summaryUser(获取用户增减数据)
     *
     * @Title: summaryUser
     * @param appId
     * @param appSecret
     * @param suRequest
     * @return
     */
    SummaryUserList summaryUser(String appId, String appSecret, SummaryUserRequest suRequest);

    /**
     * 
     * cumulateUser(获取累计用户数据)
     *
     * @Title: cumulateUser
     * @param appId
     * @param appSecret
     * @param suRequest
     * @return
     */
    CumulateUserList cumulateUser(String appId, String appSecret, SummaryUserRequest suRequest);

    /**
     * 
     * queryUserInfoList(批量获取用户信息)
     *
     * @Title: queryUserInfoList
     * @Description:
     * @param appId
     * @param appSecret
     * @param userListReq
     * @return
     */
    UserListInfoResponse queryUserInfoList(String appId, String appSecret, UserListInfoRequest userListReq);

    /**
     * 
     * queryAppId(通过code获取微信openId)
     *
     * @Title: queryAppId
     * @Description:
     * @param appId
     * @param appSecret
     * @param code
     * @return
     */
    QueryOpenIdResponse queryAppId(String appId, String appSecret, String code);

    /**
     * 查询用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    SnsUserInfo querySnsUserInfo(String accessToken, String openId);
    
    /**
     * 支付下单
     * @param fee 钱数
     * @param tradeType 支付类型
     * @param openId 微信用户id
     * @param costtype 缴费类型 4010 挂号 4011 门诊 4012 住院 其他普通
     * @return
     */
    WeChatPlaceOrderResult WeChatPlaceOrder(String fee ,String tradeType,String openId,String costtype) throws Exception;
    
    
    /**
     * 微信统一退款
     * @param config 微信基本属性
     * @param out_trade_no 订单
     * @param fee 原订单金额
     * @param out_refund_no 退款订单
     * @param refund_fee 退款金额
     * @return
     */
    Map<String, String> webChatReturnFee(MyWxPayConfig config, String out_trade_no, String fee, String out_refund_no,String refund_fee);

    Map<String, String> webChatOrderFile(MyWxPayConfig config);
}
