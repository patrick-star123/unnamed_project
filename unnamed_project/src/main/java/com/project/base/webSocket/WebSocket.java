package com.project.base.webSocket;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by PC on 2020/6/11.
 */
@RestController
@ServerEndpoint("/webSocket")
public class WebSocket {
    /*webSocket 客户端会话 通过Session 向客户端发送数据*/
    private Session session;
    /*线程安全set存放每个客户端处理消息的对象*/
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    /*用于绑定session和sessionId*/
    private static ConcurrentMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    /*webSocket 连接建立成功后进行调用*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        sessionMap.put(session.getId(), session);
        webSocketSet.add(this);
        System.out.println("webSocket有新的链接"+webSocketSet.size());
    }

    /*WebSocket 连接关闭调用的方法*/
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    /*收到客户端消息后调用的方法*/
    @OnMessage
    public void onMessage(String message){
        System.out.println(message);
    }

    /* WebSocket 发生错误时进行调用*/
    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
    }

    /**
     * 发送消息
     */
    private static void sendMessage(Session session, String msg) throws IOException {
        session.getBasicRemote().sendText(msg);
    }

    /**
     * 群发消息
     */
    public static void commonSend(String msg) throws IOException {
        for (WebSocket socket : webSocketSet) {
            if(socket.session.isOpen()){
                sendMessage(socket.session, socket.session.getId() + "接收到群发消息："+msg);
            }
        }
    }

    /**
     * 向指定客户端发送消息
     */
    public static void sendToAppoint(String sessionId, String msg) throws IOException {
        if(sessionId != null){
            Session session = sessionMap.get(sessionId);
            if(session!=null && session.isOpen()){
                sendMessage(session, "向指定客户端"+sessionId+"发送消息:"+msg);
            }
        }
    }
}
