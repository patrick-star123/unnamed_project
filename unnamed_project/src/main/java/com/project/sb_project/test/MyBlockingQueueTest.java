package com.project.sb_project.test;

import com.project.base.toolsUtils.MyCustomUtil.MyBlockingQueue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PC on 2020/6/19.
 */
@RestController
public class MyBlockingQueueTest {

    private static MyBlockingQueue<String> queue = new MyBlockingQueue<>(5);

    @PostMapping("/push")
    public void push(String element){
        new Thread(new Runnable() {
            @Override
            public void run() {
                queue.push(element);
            }
        }).start();
    }

    @PostMapping("/pop")
    public void pop(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(queue.pop());
            }
        }).start();
    }
}
