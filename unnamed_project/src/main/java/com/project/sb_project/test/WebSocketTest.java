package com.project.sb_project.test;

import com.project.base.webSocket.WebSocket;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by PC on 2020/6/11.
 */
@RestController
public class WebSocketTest {

    @PostMapping("/webSocketTest")
    public void webSocketTest(){
        try {
            WebSocket.commonSend("这是一条群发消息");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/webSocketTest/oneSocket")
    public void oneSocket(String sessionId){
        try {
            WebSocket.sendToAppoint(sessionId, "abc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
