package com.jeebase.system.common.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="WeChatPlaceOrderResult对象", description="菜单典表") 
public class WeChatPlaceOrderResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "返回状态码 SUCCESS/FAIL 非交易标识 result_code来判断")
	private String return_code;
	
	@ApiModelProperty(value = "返回信息 当return_code为FAIL时返回信息为错误原因 ，例如签名失败参数格式校验错误")
	private String return_msg;
	
	@ApiModelProperty(value = "公众账号ID")
	private String appid;
	
	@ApiModelProperty(value = "商户号")
	private String mch_id;
	
	@ApiModelProperty(value = "device_info")
	private String device_info;
	
	@ApiModelProperty(value = "随机字符串")
	private String nonce_str;
	
	@ApiModelProperty(value = "签名")
	private String sign;
	
	@ApiModelProperty(value = "业务结果")
	private String result_code;
	
	@ApiModelProperty(value = "错误代码 INVALID_REQUEST	参数错误	参数格式有误或者未按规则上传	订单重入时，要求参数值与原请求一致，请确认参数问题\n" + 
			"NOAUTH	商户无此接口权限	商户未开通此接口权限	请商户前往申请此接口权限\n" + 
			"NOTENOUGH	余额不足	用户帐号余额不足	用户帐号余额不足，请用户充值或更换支付卡后再支付\n" + 
			"ORDERPAID	商户订单已支付	商户订单已支付，无需重复操作	商户订单已支付，无需更多操作\n" + 
			"ORDERCLOSED	订单已关闭	当前订单已关闭，无法支付	当前订单已关闭，请重新下单\n" + 
			"SYSTEMERROR	系统错误	系统超时	系统异常，请用相同参数重新调用\n" + 
			"APPID_NOT_EXIST	APPID不存在	参数中缺少APPID	请检查APPID是否正确\n" + 
			"MCHID_NOT_EXIST	MCHID不存在	参数中缺少MCHID	请检查MCHID是否正确\n" + 
			"APPID_MCHID_NOT_MATCH	appid和mch_id不匹配	appid和mch_id不匹配	请确认appid和mch_id是否匹配\n" + 
			"LACK_PARAMS	缺少参数	缺少必要的请求参数	请检查参数是否齐全\n" + 
			"OUT_TRADE_NO_USED	商户订单号重复	同一笔交易不能多次提交	请核实商户订单号是否重复提交\n" + 
			"SIGNERROR	签名错误	参数签名结果不正确	请检查签名参数和方法是否都符合签名算法要求\n" + 
			"XML_FORMAT_ERROR	XML格式错误	XML格式错误	请检查XML参数格式是否正确\n" + 
			"REQUIRE_POST_METHOD	请使用post方法	未使用post传递参数 	请检查请求参数是否通过post方法提交\n" + 
			"POST_DATA_EMPTY	post数据为空	post数据不能为空	请检查post数据是否为空\n" + 
			"NOT_UTF8	编码格式错误	未使用指定编码格式	请使用UTF-8编码格式\n" + 
			"")
	private String err_code;
	
	@ApiModelProperty(value = "错误代码描述")
	private String err_code_des;
	
	@ApiModelProperty(value = "trade_type JSAPI -公众号支付 NATIVE -二维码支付 APP -APP支付 MWEB -h5")
	private String trade_type;
	
	@ApiModelProperty(value = "预支付交易会话标识")
	private String prepay_id;
	
	@ApiModelProperty(value = "支付跳转链接")
	private String code_url;
	
	@ApiModelProperty(value = "单号")
	private String out_trade_no;

	@ApiModelProperty(value = "支付宝单号 支付必须")
	private String tradeNO;

	public String getTradeNO() {
		return tradeNO;
	}

	public void setTradeNO(String tradeNO) {
		this.tradeNO = tradeNO;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	@Override
	public String toString() {
		return "WeChatPlaceOrderResult [return_code=" + return_code + ", return_msg=" + return_msg + ", appid=" + appid
				+ ", mch_id=" + mch_id + ", device_info=" + device_info + ", nonce_str=" + nonce_str + ", sign=" + sign
				+ ", result_code=" + result_code + ", err_code=" + err_code + ", err_code_des=" + err_code_des
				+ ", trade_type=" + trade_type + ", prepay_id=" + prepay_id + ", code_url=" + code_url
				+ ", out_trade_no=" + out_trade_no + "]";
	}
	
	
}
