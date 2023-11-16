package com.jeebase.system.common.util;

import com.alibaba.fastjson.JSONObject;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WeChatUtil {
	/**
     *生成18位随机订单号
     * 时间+5位随机数
     *
     */
    public static String getOutTradeNo(){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time=format.format(date);
        String radom = String.valueOf(new Random().nextInt(10000));
        String outTradeNo = time + radom ;
        return outTradeNo;
    }

    /* commons-lang-2.6.jar jdom-2.0.2.jar 版本不能搞错不然会报 org/apache/commons/lang/exception/NestableRuntimeException */
    public static JSONObject XmlToJson(String xml) throws JDOMException, IOException {
        JSONObject json = new JSONObject();
        byte[] bytes = xml.getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        SAXBuilder sb = new SAXBuilder();
        org.jdom2.Document doc = sb.build(is);
        Element root = doc.getRootElement();
        json.put(root.getName(), iterateElement(root));
        return json;
    }

    private static JSONObject iterateElement(Element element) {
        List node = element.getChildren();
        Element et = null;
        JSONObject obj = new JSONObject();
        List list = null;
        for (int i = 0; i < node.size(); i++) {
            list = new LinkedList();
            et = (Element) node.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
    }
}
