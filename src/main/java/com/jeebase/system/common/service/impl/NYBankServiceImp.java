//package com.jeebase.system.common.service.impl;
//
//import java.util.LinkedHashMap;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.abc.pay.client.ebus.UnifiedPaymentRequest;
//import com.alibaba.fastjson.JSONObject;
//import com.jeebase.system.common.entity.WeChatPlaceOrderResult;
//import com.jeebase.system.common.service.INYBankApiService;
//import com.jeebase.system.common.service.IWechatApiService;
//import com.jeebase.system.common.util.TimeUtil;
//import com.jeebase.system.common.util.WeChatUtil;
//
//
//public class NYBankServiceImp implements INYBankApiService{
//	 /**
//     * 域名
//     */
//    @Value("${weixin.domain}")
//    private String domain;
//    
//    /**
//     * 挂号异步通知地址
//     */
//    public String getRegNotifyUrl() {
//		return domain + "ww/";
//	}
//    
//    /**
//     * 门诊异步通知地址
//     */
//    public String getOutNotifyUrl() {
//		return domain + "ww/";
//	}
//    /**
//     * 住院异步通知地址
//     */
//	public String getHosNotifyUrl() {
//		return domain + "ww/";
//	}
//
//	/**
//     * 普通异步通知地址
//     */
//	public String getNotifyUrl() {
//		return domain + "ww/";
//	}
//
//	@Override
//	public JSONObject NYWeChatPlaceOrder(String Fee, String tradeType, String openId, String costtype) {
//		// TODO Auto-generated method stub
//		
//		UnifiedPaymentRequest unifiedPaymentRequest = new UnifiedPaymentRequest();
//		
//		LinkedHashMap dicRequest = unifiedPaymentRequest.dicRequest;
//		LinkedHashMap dicOrder = unifiedPaymentRequest.dicOrder;
//
//		switch (costtype) {
//		case "4010":
//			dicOrder.put("OrderDesc", "挂号缴费");
//			dicOrder.put("ResultNotifyURL", getRegNotifyUrl());
//			break;
//		case "4011":
//			dicOrder.put("OrderDesc", "门诊缴费");
//			dicOrder.put("ResultNotifyURL", getOutNotifyUrl());
//			break;
//		case "4012":
//			dicOrder.put("OrderDesc", "住院缴费");
//			dicOrder.put("ResultNotifyURL", getHosNotifyUrl());
//			break;
//		default:
//			dicOrder.put("OrderDesc", "普通缴费");
//			dicOrder.put("ResultNotifyURL", getNotifyUrl());
//			break;
//		}
//		
//		dicRequest.put("TrxType", "UnifiedOrderReq");
//		dicRequest.put("PaymentType", "8");
//		dicRequest.put("PaymentLinkType", "2");
//		dicRequest.put("NotifyType", "1");
//		dicRequest.put("MerchantRemarks", "");
//		dicRequest.put("IsBreakAccount", "0");
//		dicRequest.put("CommodityType", "0201");
//		dicRequest.put("MerModelFlag", "0");
//		dicRequest.put("SubMerchantID", "");
//		dicRequest.put("SubMerNo", "");
//		
//		dicOrder.put("PayTypeID", "JSAPI");
//		dicOrder.put("OrderNo", WeChatUtil.getOutTradeNo());
//		dicOrder.put("ExpiredDate", "30");
//		dicOrder.put("OrderAmount", Fee);
//		dicOrder.put("AccountNo", "wxb3acb24d196ae55c");
//		dicOrder.put("CurrencyCode", "156");
//		dicOrder.put("ReceiverAddress", "");
//		dicOrder.put("BuyIP", "");
//		
//		dicOrder.put("OrderDate", TimeUtil.Today("yyyy/MM/dd"));
//		dicOrder.put("OrderTime", TimeUtil.Today("HH:mm:ss"));
//		dicOrder.put("orderTimeoutDate", TimeUtil.getInAFewSecond("yyyyMMddHHmmss",500));
//		dicOrder.put("OpenID", openId);
//		dicOrder.put("LimitPay", "no_credit");
//		dicOrder.put("SplitAccInfoItems", "[{\n" + 
//				"                    \"SplitMerchantID\": \"\",\n" + 
//				"                    \"SplitAmount\": \"\"\n" + 
//				"                }]");
//		
//		com.abc.pay.client.JSON postRequest = unifiedPaymentRequest.postRequest();
//		
//		
//		
//		return null;
//	}
//	
//	public static void main(String[] args) {
//		UnifiedPaymentRequest unifiedPaymentRequest = new UnifiedPaymentRequest();
//		
//		String outTradeNo = WeChatUtil.getOutTradeNo();
//		LinkedHashMap dicRequest = unifiedPaymentRequest.dicRequest;
//		LinkedHashMap dicOrder = unifiedPaymentRequest.dicOrder;
//
//		dicRequest.put("TrxType", "UnifiedOrderReq");
//		dicRequest.put("PaymentType", "8");
//		dicRequest.put("PaymentLinkType", "2");
//		dicRequest.put("NotifyType", "1");
//		dicRequest.put("ResultNotifyURL", "");
//		dicRequest.put("MerchantRemarks", "");
//		dicRequest.put("IsBreakAccount", "0");
//		dicRequest.put("CommodityType", "0201");
//		dicRequest.put("MerModelFlag", "0");
//		dicRequest.put("SubMerchantID", "");
//		dicRequest.put("SubMerNo", "");
//		
//		dicOrder.put("PayTypeID", "JSAPI");
//		dicOrder.put("OrderNo", outTradeNo);
//		dicOrder.put("ExpiredDate", "30");
//		dicOrder.put("OrderAmount", "1");
//		dicOrder.put("AccountNo", "wxb3acb24d196ae55c");
//		dicOrder.put("CurrencyCode", "156");
//		dicOrder.put("ReceiverAddress", "");
//		dicOrder.put("BuyIP", "");
//		dicOrder.put("OrderDesc", "测试");
//		dicOrder.put("OrderDate", TimeUtil.Today("yyyy/MM/dd"));
//		dicOrder.put("OrderTime", TimeUtil.Today("HH:mm:ss"));
//		dicOrder.put("orderTimeoutDate", TimeUtil.getInAFewSecond("yyyyMMddHHmmss",500));
//		dicOrder.put("OpenID", "oSKFt6fJRK8-PJRN-erHmDaj84VM");
//		dicOrder.put("LimitPay", "no_credit");
//		dicOrder.put("SplitAccInfoItems", "[{\n" + 
//				"                    \"SplitMerchantID\": \"\",\n" + 
//				"                    \"SplitAmount\": \"\"\n" + 
//				"                }]");
//		
//		com.abc.pay.client.JSON postRequest = unifiedPaymentRequest.postRequest();
//		
//		if(postRequest.getIJsonString()!=null) {
//			JSONObject jsonObject = JSONObject.parseObject(postRequest.getIJsonString()).getJSONObject("MSG").getJSONObject("Message");
//			if(jsonObject.getString("ReturnCode").equals("0000")) {
//				JSONObject jsonObject2 = jsonObject.getJSONObject("JSAPI");
//				System.out.println(jsonObject2);
//				WeChatPlaceOrderResult result = new WeChatPlaceOrderResult();
//				
//				result.setAppid(jsonObject2.getString("sub_appId"));
//				result.setCode_url("");
//				result.setOut_trade_no(outTradeNo);
//				result.setReturn_code("SUCCESS");
//				result.setReturn_msg("ok");
//				result.setMch_id("");
//				result.setNonce_str(jsonObject2.getString("sub_appId"));
//				//result.set
//			}
//			
//			
//		}
//		
//		System.out.println(JSONObject.toJSON(postRequest));
//		
//	}
//
//	@Override
//	public JSONObject refundFee(String out_refund_no, String out_trade_no, String fee) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
