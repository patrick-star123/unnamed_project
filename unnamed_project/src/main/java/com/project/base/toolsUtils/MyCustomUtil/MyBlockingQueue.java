package com.project.base.toolsUtils.MyCustomUtil;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.LinkedList;

/**
 * Created by PC on 2020/6/19.
 */
public class MyBlockingQueue<T> {

    private final static Object pushLock = new Object();

    private final static Object popLock = new Object();

    private final static Object pushMethodLock = new Object();

    private final static Object popMethodLock = new Object();

    private int maxSize = 0;

    private LinkedList<T> queue;

    public MyBlockingQueue(int maxSize){
        this.maxSize = maxSize;
        queue = new LinkedList<T>();
    }

    public void push(T t){
        synchronized (pushMethodLock){
            if(queue.size() >= maxSize){
                pushLock();
            }
            queue.addLast(t);
            popUnLock();
        }
    }

    public T pop(){
        synchronized (popMethodLock){
            if(queue.size() == 0){
                popLock();
            }
            T t = queue.pollFirst();
            pushUnLock();
            return t;
        }
    }

    private void pushLock(){
        synchronized (pushLock){
            try {
                pushLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pushUnLock(){
        synchronized (pushLock){
            pushLock.notifyAll();
        }
    }

    private void popLock() {
        synchronized (popLock){
            try {
                popLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void popUnLock(){
        synchronized (popLock){
            popLock.notifyAll();
        }
    }
}
