package com.jeebase.system.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jeebase.system.common.entity.ImgIDCardInfo;
import com.jeebase.system.common.service.ISmsService;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author fyy
 */
@Service
public class SmsServiceImpl implements ISmsService {
	
	@Autowired
    private RestTemplate restTemplate;

    /**
     * 系统日志
     */
    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";

    /**
     *  产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    
    /**
     * 百度api地址
     */
    private static final String BAIDUAPI = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token={baiduToken}";
    
    @Value("${baidu.ai.token}")
    private String baiduToken;

    /**
     *  短信发送的appkey
     */
    @Value("${aliyun.AccessKey-ID}")
    private String accessKeyId;

    /**
     *  短信发送的秘钥
     */
    @Value("${aliyun.Access-Key-Secret}")
    private String accessKeySecret;

    @Value("${aliyun.sign-name}")
    private String signName;

    @Value("${aliyun.reg-code}")
    private String regCode;

    @Override
    public void sendSms(SendSmsRequest request) {
        try {
            // 可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            // 初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            // hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            logger.error("短信发送失败：" + String.valueOf(e));
        }
    }

    @Override
    public QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request) {
        QuerySendDetailsResponse querySendDetailsResponse = null;
        try {
            // 可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            // 初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            // hint 此处可能会抛出异常，注意catch
            querySendDetailsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            logger.error("短信发送失败：" + String.valueOf(e));
        }
        return querySendDetailsResponse;
    }

    @Override
    public void sendWarnSms(String jsonParam) {
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers("");
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_137670765");
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(jsonParam);
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        sendSms(request);
    }

    @Override
    public void sendVcodeSms(String phoneNumber, String smsCode) {
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(regCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        String jsonParam = "{\"code\":\"" + smsCode + "\"}";
        request.setTemplateParam(jsonParam);
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        sendSms(request);
    }

	@Override
	public ImgIDCardInfo idCardImgDiscern(MultipartFile file,String direction) {
		// TODO Auto-generated method stub
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		/* 创建base64 eclipse=> buid path=> librarise=> jar librarise=> access rules=> edit=> add=> accessible => ** */
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(bytes);
		
		/* postForObject  表单提交固定 Map格式*/
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		paramMap.add("image", encode);
		paramMap.add("id_card_side", direction);
		
		/* 创建表头 */
		HttpHeaders headers = new HttpHeaders();
		/* 声明表头类型 */
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		/* 声明请求对象 */
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(paramMap, headers);
		
		String forObject = restTemplate.postForObject(BAIDUAPI, httpEntity, String.class,baiduToken);
		
		/* 结果转换json */
		JSONObject parseObject = JSONObject.parseObject(forObject);
		
		System.out.println(parseObject);
		System.out.println(parseObject.containsKey("image_status"));
		/* 判断图片是否合法 */
		if(!(parseObject.containsKey("image_status")&&!parseObject.get("image_status").toString().trim().equals("normal"))) {
			System.out.println("不合法");
			return null;
		}
		
		/* 结果集 */
		String faceJson = parseObject.get("words_result").toString();
		
		ImgIDCardInfo imgIDCardInfo = null;
		
		/* 正面 */
		if(direction.equals("front")) {
			imgIDCardInfo = new ImgIDCardInfo(
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("公民身份号码").toString()).get("words").toString(), 
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("性别").toString()).get("words").toString(), 
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("姓名").toString()).get("words").toString(),
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("民族").toString()).get("words").toString(), 
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("住址").toString()).get("words").toString(), 
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("出生").toString()).get("words").toString()
					);
		}else 
			/* 反面 */
			if(direction.equals("back")){
			imgIDCardInfo = new ImgIDCardInfo(
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("失效日期").toString()).get("words").toString(), 
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("签发机关").toString()).get("words").toString(), 
					JSONObject.parseObject(JSONObject.parseObject(faceJson).get("签发日期").toString()).get("words").toString());
		}
		return imgIDCardInfo;
	}
}
