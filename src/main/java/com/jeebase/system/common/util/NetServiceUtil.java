package com.jeebase.system.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.google.gson.Gson;

public class NetServiceUtil {
	public static <T> T getServiceData(Map map, String url, String serviceName, Class<T> clazz) {

		T ret = null;
		String URL = null;

		Service service = new Service();
		Gson gson = new Gson();
		String invoke = null;
		int count = 0;
		Object fromJson = null;

		try {
		    Call e = (Call)service.createCall();
		    e.setTargetEndpointAddress(new java.net.URL(url));
		    e.setUseSOAPAction(true);
		    e.setEncodingStyle((String)null);
		    e.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
			e.setOperationName(new QName("http://tempuri.org/", serviceName));
			e.setSOAPActionURI("http://tempuri.org/" + serviceName);
			e.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
			Object[] obj = new Object[map.size()];

			for(Iterator var13 = map.keySet().iterator(); var13.hasNext(); ++count) {
			   String key = (String)var13.next();
			   e.addParameter(new QName("http://tempuri.org/", key), XMLType.SOAP_STRING, ParameterMode.IN);
			   obj[count] = map.get(key);
			}

			invoke = (String)e.invoke(obj);

			System.out.println("result "+invoke);

			if(clazz.getCanonicalName().equals(String.class.getCanonicalName()) ) {
				ret = (T) invoke;
			   return ret;
			}

			ret = gson.fromJson(invoke, clazz);

	         } catch (RemoteException var17) {
	            var17.printStackTrace();
	         } catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	         return ret;
	      }
	public static Object test(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			System.out.println(objects[i]);
		}
		return objects;
	}
}
