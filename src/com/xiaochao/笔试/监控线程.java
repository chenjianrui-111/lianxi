package com.xiaochao.笔试;

/**
 * 1、终止模式-两阶段终止模式
 * 1、使用情形
 * 在一个线程t1中，合理地终止线程t2，要给t2“料理后事”的机会。这里的料理后事，通常指释放持有的锁。
 * 3、合理做法
 * 两阶段终止：
 * 第一阶段：其他线程调用interrupted()，打断某个线程
 * 第二阶段：该线程检查打断标记，自行释放资源，结束运行
 * 比如要做一个监控线程，可以定期监控一些数据。它一般使用while(true)实现，但是需要一个停止机制。
 */
public class 监控线程 {
    public class myThread {
        private Thread thread;

        public void start(){
            thread = new Thread(() -> {
                while (true){
                    Thread current = Thread.currentThread();
                    //检查打断标记
                    if (current.isInterrupted()){
                        System.out.println("即将结束，保存数据中...");
                        break;
                    }
                    //如果睡眠期间被打断，就自行设置打断标记
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        current.interrupt();
                    }
                    //正常工作
                    System.out.println("检测系统数据...");
                }
            }, "监控线程");

            thread.start();
        }

        public void stop(){
            thread.interrupt();
        }

    }

    /**
     * 利用voliate实现
     */
    class myThread02 {
        private Thread thread;

        private volatile boolean flag = false;

        public void start(){
            thread = new Thread(() -> {
                while (true){
                    //检查是否被打断
                    if (flag){
                        System.out.println("即将退出，保存数据中...");
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //正常工作
                    System.out.println("检测系统数据...");
                }
            }, "监控线程");
            thread.start();
        }

        public void stop(){
            System.out.println("申请停止监控线程");
            this.flag = true;
        }
    }
/**
 * 同步模式-保护性暂停模式
 * 1、使用情形
 * 保护性暂停，Guarded Suspension。用在一个线程等待另一个线程的执行结果时。要求等待时进入Waiting状态，有结果后被唤醒。
 * 要点：
 * 有一个结果需要从一个线程传递给另一个线程，让它们关联同一个 Guarded Object
 * 如果有结果不断地从一个线程到另一个线程，可以使用消息队列（改用生产者消费者模式）
 * JDK中join、Future就是这样实现的
 * 因为要等待结果，所以属于同步模式
 */
class GuardedObject {
    //线程间传递的结果
    private Object response;
    //锁对象
    private final Object lock = new Object();

    public Object get() {
        synchronized (lock) {
            // 条件不满足则等待
            while (response == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，通知等待线程
            this.response = response;
            lock.notifyAll();
        }
    }
}
/**
 * 超时功能
 * 一般来说，也不能无限等待结果，应该设置一个超时时间，在规定时间内没有提供所需资源的话，就做一些事情
 * 在GuardedObject内部添加功能：对时间的记录与判断逻辑
 */
class GuardedObjectV2 {
    private Object response;
    private final Object lock = new Object();
    public Object get(long millis) {
        synchronized (lock) {
            // 1) 记录最初时间
            long begin = System.currentTimeMillis();
            // 2) 已经经历的时间
            long timePassed = 0;
            while (response == null) {
                // 4) waitTime 剩余等待时间
                long waitTime = millis - timePassed;
//                log.debug("waitTime: {}", waitTime);
                if (waitTime <= 0) {
//                    log.debug("break...");
                    break;
                }
                try {
                    //再等待 “剩余时间” 个时间，防止虚假唤醒
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 3) 如果提前被唤醒，这时已经经历的时间
                timePassed = System.currentTimeMillis() - begin;
//                log.debug("timePassed: {}, object is null {}",
//                        timePassed, response == null);
            }

            return response;
        }
    }

    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，通知等待线程
            this.response = response;
//            log.debug("notify...");
            lock.notifyAll();
        }
    }
}
}
