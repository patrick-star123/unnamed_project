package blockQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by PC on 2020/6/19.
 */
public class MyBlockingQueue<T> {

    private final static Object pushLock = new Object();

    private final static Object popLock = new Object();

    private int maxSize = 0;

    private LinkedList<T> queue;

    public MyBlockingQueue(int maxSize){
        this.maxSize = maxSize;
        queue = new LinkedList<T>();
    }

    public synchronized void push(T t){
        if(queue.size() >= maxSize){
            pushLock();
        }
        queue.addLast(t);
        popUnLock();
    }

    public synchronized T pop(){
        if(queue.size() == 0){
            popLock();
        }
        T t = queue.pollFirst();
        pushUnLock();
        return t;
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
            pushLock.notify();
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
            popLock.notify();
        }
    }
}
