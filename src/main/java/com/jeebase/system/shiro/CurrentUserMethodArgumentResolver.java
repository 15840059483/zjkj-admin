package com.jeebase.system.shiro;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.jeebase.common.annotation.auth.CurrentUser;
import com.jeebase.common.exception.UnauthorizedException;
import com.jeebase.system.security.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @ClassName: CurrentUserMethodArgumentResolver
 * @Description: 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * @author fyy
 * @date 
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = (User) webRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
        if (user == null) {
            throw new UnauthorizedException("获取用户信息失败");
        }
        return user;
    }

    public static void main(String[] args) throws AlipayApiException {
        String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/ctrIdwRcGnNATDrnGyRJncb3w2jLDO/j1d3YYn158JrizHuTXERA5BcLEr4hWu27/R7EmCt2DknG2oAaaRd7UQQE4NviU6cHn+9hJIUb79QJCBJfuL+jELVDvvyaV/qFtGcVhNLoWLQVIGIMqinDLtBkUqzKTMkT+If2brLfzeuzJPcPP86zdhKW7X8Zrb2gARt/qzpFq0cRtRi6/t+OogetULzN+UjNAWYsW+826c13I1XQfRm+2Z8pfzrUQVbb34oegJ/e5S5y54p4MlLner77rcdTcmslZsk3gs2faQeftvvkpTzHMQwp8y8X0Ih91kvQxb3HqAuo1R4XhbQkwIDAQAB";
        String private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCzxa9yjmLzM5Gmx8kYhGKeH1+tjPOdfRvqF4JqTVRuy6u4oHQa10LUWt28hOFMbZRoKRPtjBgXsp9MO7JUNIigO81bxqpTa1OR/8+K1thpyLnmOU7gMT0qW5eT+e1upBmPs2pl1DwsCPW0Rt0fuuPtzN9pF7E2DcnzXxSE8g2kDLakhW3n8gWUVEd+09QDGmk9L4eBp1D1yVjPxv+x3Kq90WGKdX5qP2IZwQ5wv6WZKCITvl3hhY51LGNSbcA5Bbo0sL2xzJlmEXwZboiNbwy/dPm3x7bTXk3RaZheCqscboZG4ffr7UIzW1QwoJ1UEinsY0Gh2xWYMr+mcpxNnCvTAgMBAAECggEBAJlRbrlfkCpkf8LivrHnLgg3L61e+eIF8kmqdTJd8zVvBGw4g+zkS326ZUPDfDZnelt/NRyyD1Yeo9crToKcLy2g8xpji54LecGZjxKU+wr1Av7DP27a75Rxewvb6lAjKh2xYXupDwxrM8nlae8GwB9uS9dgOmWkSukB3QkpOtL+Cr+wzFn3GnkjJzs0Ep5yCSSpnVh3nq2ejfriOWJKjVH37GzsTkODWlPMH8Q17XhDQ6Z3B1QTwd8q2ODGQYLiiGkh3+dh30StJROs8cTQ2y1cmGuUnVq+A9xII9p4f3OmbxpNtiWBSJfFVb+KC5RtzE6D+VVYdAcIqHAk+GLExOECgYEA21N7BgRzEKBqxr6oGkD4WBZgfQSGGM8uJxanpZlRlQki62oRaQkpAAFLcCeFnPfGhaxvM+mhyXCl3JEQPeYn063HjnjZaEd8t3dKLvatV0UOSzGMrpg5H6SAUR8l6RUD2HO5Wibzd4q+emgwP9YGcQpcJ0jX+KZYykTgZOTt+8MCgYEA0dUNe8bhpzP/7QGUNs3VGoF19gUDw3cNK1TvRvLHrq5xMfSxU9AAuWgl3sYAWQoGoEZpSSB157HPCPZhV2SdYyaQw5BsW4iztoJIO89F8qi/vWCGwa5OviSTR7zPbtam7YXJWd8x/8itylksbnIub8SgVDZU/u23iUtcKKJq3rECgYEAgOJduPuEYpDiLkh7oHHtflYn/U9bPdkB1haKDdY7CsmDq7N457BqV4ZrPkz7R3KTRpo9/tOcLzPzQ9o7ISTO4qWMTzzVBWJ895MyFMcAtffunmFPuonZh66zePhE3uNqjxfyI/gfum/d3zC6qciroGHmJW+4+nVGWBaBhsjA0OECgYBNWPnVbKR1nAd0+CYIZatuT9aJz6NphBMIrBBf2NKeo5L6VTagrIX30lpTWi16pOV4YzwgRqTvuW2V4ixTqLUTfX3TeQNYYnXMiw3RbkBciLPVPSWvc293S8g2o3TbaKiz2aM1S78EiJb3hhOIyvIvJQ3svPJr3njPVUYtfGFkoQKBgQCR40ZIlWP6++SUwg5Xdrwlrd75sDGDwHqUlQuIwFNUwXleCUg13eMtNQfnHfuNzI6DxDjx8uymDr59/iB0RJq0lRcLVjsu60Dnnh+8cX3ktoSzr8MGSooeaZxDfM7qc8dVxpm9YR1QDgwlnMyXBVFeTYe5OytIfwEl3djCMNzJcg==";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2021003146632241",private_key,"json","GBK",alipay_public_key,"RSA2");
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        request.setNotifyUrl("");
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "2021081701010100323333");
        bizContent.put("total_amount", 0.01);
        bizContent.put("subject", "测试商品");
        bizContent.put("buyer_id", "2088312982887420");
        bizContent.put("timeout_express", "10m");
        //bizContent.put("product_code", "JSAPI_PAY");//FACE_TO_FACE_PAYMENT

//// 商品明细信息，按需传入
//JSONArray goodsDetail = new JSONArray();
//JSONObject goods1 = new JSONObject();
//goods1.put("goods_id", "goodsNo1");
//goods1.put("goods_name", "子商品1");
//goods1.put("quantity", 1);
//goods1.put("price", 0.01);
//goodsDetail.add(goods1);
//bizContent.put("goods_detail", goodsDetail);

//// 扩展信息，按需传入
//JSONObject extendParams = new JSONObject();
//extendParams.put("sys_service_provider_id", "2088511833207846");
//bizContent.put("extend_params", extendParams);

//// 结算信息，按需传入
//JSONObject settleInfo = new JSONObject();
//JSONArray settleDetailInfos = new JSONArray();
//JSONObject settleDetail = new JSONObject();
//settleDetail.put("trans_in_type", "defaultSettle");
//settleDetail.put("amount", 0.01);
//settleDetailInfos.add(settleDetail);
//settleInfo.put("settle_detail_infos", settleDetailInfos);
//bizContent.put("settle_info", settleInfo);

//// 二级商户信息，按需传入
//JSONObject subMerchant = new JSONObject();
//subMerchant.put("merchant_id", "2088000603999128");
//bizContent.put("sub_merchant", subMerchant);

//// 业务参数信息，按需传入
//JSONObject businessParams = new JSONObject();
//businessParams.put("busi_params_key", "busiParamsValue");
//bizContent.put("business_params", businessParams);

//// 营销信息，按需传入
//JSONObject promoParams = new JSONObject();
//promoParams.put("promo_params_key", "promoParamsValue");
//bizContent.put("promo_params", promoParams);

        request.setBizContent(bizContent.toString());
        AlipayTradeCreateResponse response = alipayClient.execute(request);

        System.out.println(response.getBody());
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
