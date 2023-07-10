package com.xiaochao.笔试;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.使用synchronized实现。由于synchronized是同一把锁，所以使用notify()可能会唤醒非目标线程，
 * notifyAll()唤醒全部线程则会带来大量的 CPU 上下文切换和锁竞争
 */
public class 实现线程安全阻塞队列 {
    class ArrayBlockingQueue{
        private Object[] array;//数组
        private int head;
        private int tail;
        private volatile int size;
        public ArrayBlockingQueue(int capacity){
            this.array = new Object[capacity];
        }
        //写入元素
        public synchronized void put(Object o) throws InterruptedException{
            //当队列满时，阻塞
            while (size == array.length){
                this.wait();
            }
            array[tail++] = o;
            if (tail == array.length){
                tail = 0;
            }
            size++;
            //唤醒线程
            this.notifyAll();
        }
        //取出元素
        public synchronized Object get() throws InterruptedException{
            //当队列为空时，阻塞
            while (size == 0){
                this.wait();
            }
            Object o =array[head++];
            if (head == array.length){
                head = 0;
            }
            size--;
            //唤醒线程
            this.notifyAll();
            return o;
        }
    }

    class ArrayBlockingQueue2 {
        private Object[] array; //数组
        private int head; //头
        private int tail; //尾
        private volatile int size; //元素个数
        private ReentrantLock lock = new ReentrantLock(); //锁
        private Condition notEmpty = lock.newCondition(); //非空
        private Condition notFull = lock.newCondition();  //非满

        public ArrayBlockingQueue2(int capacity) {
            this.array = new Object[capacity];
        }

        //写入元素
        public void put(Object o) throws InterruptedException {
            try{
                lock.lock();
                //当队列满时，阻塞
                while (size == array.length) {
                    notFull.wait();
                }
                array[tail++] = o;
                if (tail == array.length) {
                    tail = 0;
                }
                size ++;
                //唤醒线程
                notEmpty.notifyAll();
            } finally {
                lock.unlock();
            }
        }

        //取出元素
        public Object get() throws InterruptedException {
            lock.lock();
            try {
                //当队列为空，阻塞
                while (size == 0) {
                    notEmpty.wait();
                }
                Object o = array[head++];
                if (head == array.length) {
                    head = 0;
                }
                size --;
                //唤醒线程
                notFull.notifyAll();
                return o;
            } finally {
                lock.unlock();
            }
        }

    }
}
