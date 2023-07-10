package com.xiaochao.笔试;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 一个电商项目，QPS一千多，如果每次都创建和关闭数据库连接，会造成性能瓶颈。
 * 应该做一个连接池，预先放入一批连接对象
 * 一个请求到达，从池内取一个连接对象，使用完再归还，实现连接的复用
 */
public class 实现数据库连接池 {
    public class Pool {
        //连接池大小
        private final int poolSize;

        //连接对象数组
        private Connection[] connections;

        //连接状态数组 0空闲 1繁忙
        private AtomicIntegerArray states;

        //构造方法
        public Pool(int poolSize){
            this.poolSize = poolSize;
            this.connections = new Connection[poolSize];
            states = new AtomicIntegerArray(poolSize);
            //创建这么多连接对象
            for (int i = 0; i < poolSize; i++) {
//                connections[i] = new MyConnection();
            }
        }

        //取一个连接
        public Connection getConnection(){
            while (true) {
                for (int i = 0; i < poolSize; i++) {
                    //找一个空闲连接
                    if (states.get(i) == 0){
                        //试图获取它
                        if (states.compareAndSet(i, 0, 1)){
                            System.out.println("获取到一个连接，号码是："+i);
                            return connections[i];
                        }
                    }
                }
                //如果没有空闲连接，线程等待
                synchronized (this) {
                    try {
                        System.out.println("wait...");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //归还一个连接
        public void giveBack(Connection connection){
            //找到对应的连接
            for (int i = 0; i < poolSize; i++) {
                if (connections[i] == connection){
                    //设置连接状态
                    states.set(i, 0);
                    //唤醒等待的线程
                    synchronized (this) {
                        System.out.println("有一个连接空闲了，号码是："+i);
                        this.notifyAll();
                    }
                }
            }
        }
    }
}
/**
 *注意：
 * states数组使用了原子数组，保证修改的线程安全
 * 数据库连接对象可能短期内不会被释放，所以不能一直循环做CAS操作，应该上锁等待。
 * 缺陷：
 * 没有考虑连接池容量的动态收缩
 * 没有连接保活机制
 * 没有超时等待处理，只能一直wait
 * 不支持分布式数据库
 */
