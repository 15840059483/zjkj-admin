package com.jeebase.system.config;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
 
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
* Type: WebSocketServer
* Description: WebSocketServer,实现服务器客户端平等交流，
* 		达到服务器可以主动向客户端发生消息
* @author fyy
* @date Dec 02, 2020
 */
@Component
@ServerEndpoint(value = "/websocket/{userName}")
public class WebSocketServer {
	
	//日志记录器
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
	
    //使用道格李的ConcurrentHashSet, 放的是WebSocketServer而不是session为了复用自己方法
    private static transient volatile Set<WebSocketServer> webSocketSet = ConcurrentHashMap.newKeySet();
 
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    private String userName;
 
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("userName") String username,Session session) {
        this.session = session;
        this.userName = username;
        webSocketSet.add(this);     //加入set中
        System.out.println("username"+username+"session"+session);
        boolean sendMessage = sendMessage("连接成功");
        System.out.println("链接状态"+sendMessage);
    }
 
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
    }
 
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	LOGGER.info("来自客户端(" + session.getId() + ")的消息:" + message);
    	sendMessage("Hello, nice to hear you! There are " + webSocketSet.size() + " users like you in total here!");
    }
 
	/**
	 * Title: onError
	 * Description: 发生错误时候回调函数
	 * @param session
	 * @param error
	 */
    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("webSocket发生错误:" + error.getClass() + error.getMessage());
    }
 
    /**
     * Title: sendMessage
     * Description: 向客户端发送消息
     * @param message
     * @throws IOException
     */
    public boolean sendMessage(String message,String userName) {
        try {
        	for (WebSocketServer item : webSocketSet) {
                if (item.userName.equals(userName) ) {
                    item.session.getAsyncRemote().sendText(message);
                    break;
                }
            }
        	return true;
		} catch (Exception error) {
			LOGGER.error("webSocket-sendMessage发生错误:" + error.getClass() + error.getMessage());
			return false;
		}
    	
    }
    
    /**
     * Title: sendMessage
     * Description: 向客户端发送消息
     * @param message
     * @throws IOException
     */
    public boolean sendMessage(String message) {
        try {
			this.session.getBasicRemote().sendText(message);
			return true;
		} catch (IOException error) {
			LOGGER.error("webSocket-sendMessage发生错误:" + error.getClass() + error.getMessage());
			return false;
		}
    }
 
 
    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) {
    	LOGGER.info("webSocket-sendInfo群发消息：" + message);
        for (WebSocketServer item : webSocketSet) {
            item.sendMessage(message);
        }
    }
 
    /**
     * Title: getOnlineCount
     * Description: 获取连接数
     * @return
     */
    public static int getOnlineCount() {
        return webSocketSet.size();
    }
}

