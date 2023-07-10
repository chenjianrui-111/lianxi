package com.xiaochao.笔试;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    //阻塞队列
    private MyBlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers;

    //核心线程数
    private int coreSize;

    //任务队列的容量上限
    private int queueCapacity;

    //超时时间，时间内没有任务就结束线程
    private long timeout;

    //时间单位
    private TimeUnit timeUnit;

    public MyThreadPool(int coreSize, int queueCapacity, long timeout, TimeUnit timeUnit) {
        this.coreSize = coreSize;
        this.queueCapacity = queueCapacity;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new MyBlockingQueue<>(queueCapacity);
        workers = new HashSet<>();
    }

    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public MyThreadPool(int coreSize, int queueCapacity, long timeout, TimeUnit timeUnit, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.queueCapacity = queueCapacity;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
        taskQueue = new MyBlockingQueue<>(queueCapacity);
        workers = new HashSet<>();
    }

    //执行任务
//    public void execute(Runnable task) {
//        synchronized (workers) {
//            //如果任务数小于核心数，直接创建一个worker对象执行
//            if (workers.size() < coreSize) {
//                //创建线程对象
//                Worker worker = new Worker(task);
//                System.out.println("新增一个worker线程，立即执行任务，线程编号：" + worker.getName());
//                //将线程对象加入线程集合
//                workers.add(worker);
//                //启动线程
//                worker.start();
//            }
//            //如果任务数大于核心数，就把它放入任务队列
//            else {
//                System.out.println("线程池已满，任务被放入任务队列");
//                taskQueue.put(task);
//            }
//        }
//    }
    //带拒绝策略的执行任务
    //执行任务
    public void execute(Runnable task) {
        synchronized (workers) {
            //如果任务数小于核心数，直接创建一个worker对象执行
            if (workers.size() < coreSize) {
                //创建线程对象
                Worker worker = new Worker(task);
                System.out.println("新增一个worker线程，立即执行任务，线程编号：" + worker.getName());
                //将线程对象加入线程集合
                workers.add(worker);
                //启动线程
                worker.start();
            }
            //如果任务数大于核心数，就调用tryPut
            else {
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    //线程的包装类，包含一些额外信息
    class Worker extends Thread {
        //任务对象
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        //执行任务
        @Override
        public void run() {
            //如果task不为null，说明创建线程对象时就指定了任务，直接执行
            //如果task为null，说明之前的任务已经执行完成。查看任务队列，如果任务队列不为空，就取出一个任务执行
            while (task != null || (task = taskQueue.tackInTime(timeout, timeUnit)) != null) {
                try {
                    System.out.println(this.getName() + " 正在执行任务：" + task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //任务执行完毕
                    System.out.println(task + "执行完毕：");
                    task = null;
                }
            }
            //上面调用的是任务队列的超时等待方法，如果它返回null，说明已经等够时间了，直接销毁线程
            synchronized (workers) {
                System.out.println("没有任务了，worker被移除" + this.getName());
                workers.remove(this);
            }
        }
    }


    public static void main(String[] args) {
        //创建线程池对象
        MyThreadPool pool = new MyThreadPool(2, 5, 1000, TimeUnit.MICROSECONDS);
        //创建一堆任务对象，用线程池执行
        for (int i = 0; i < 15; i++) {
            int num = i;
            pool.execute(() -> {
                try {
                    Thread.sleep(10000);
                    System.out.println(num + "号任务正在执行...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //    测试，定义拒绝策略为“死等”

        MyThreadPool pool1 = new MyThreadPool(2, 5, 1000, TimeUnit.MICROSECONDS,
                (queue, task) -> {
                    queue.put(task);
                });
//    定义拒绝策略为“抛出异常”

        MyThreadPool pool2 = new MyThreadPool(2, 5, 1000, TimeUnit.MICROSECONDS,
                (queue, task) -> {
                    throw new RuntimeException("队列已满");
                });
    }

}
