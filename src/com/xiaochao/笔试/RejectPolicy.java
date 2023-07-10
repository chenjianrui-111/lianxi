package com.xiaochao.笔试;

@FunctionalInterface
public interface RejectPolicy<T> {
    //把队列和任务传给调用者
    void reject(MyBlockingQueue<T> queue, T task);
}
