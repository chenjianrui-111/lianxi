package com.xiaochao.笔试;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

    //任务队列
    private Deque<T> queue = new ArrayDeque<>();

    //锁
    private ReentrantLock lock = new ReentrantLock();

    //生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //容量
    private int capacity;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    //阻塞获取
    public T take(){
        lock.lock();
        try {
            //当队列中没有任务就阻塞
            while (queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //获取队列头部元素
            T t = queue.removeFirst();
            //唤醒等待的生产者线程
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //带超时的阻塞获取
    public T tackInTime(long timeout, TimeUnit unit){
        lock.lock();
        try {
            //将timeout统一转换为纳秒
            long nanos = unit.toNanos(timeout);

            //当队列中没有任务就阻塞
            while (queue.isEmpty()){
                try {
                    if (nanos <= 0){
                        return null;
                    }
                    //等待nanos纳秒
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //获取队列头部元素
            T t = queue.removeFirst();
            //唤醒等待的生产者线程
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T element){
        lock.lock();
        try {
            //如果队列已满，就阻塞
            while (queue.size() == capacity){
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //把任务添加到队列尾部
            queue.addLast(element);
            //唤醒等待的消费者线程
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }

    //获取队列中任务的数量
    public int size(){
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }
    //完成拒绝策略
    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            //如果队列没有满，就加入队列
            if (queue.size() == capacity){
                queue.addLast(task);
                System.out.println(task + "被成功放入任务队列！");
                emptyWaitSet.signal();
            }
            //如果队列满了，就执行拒绝策略
            else {
                rejectPolicy.reject(this, task);
            }
        }finally {
            lock.unlock();
        }
    }

}
