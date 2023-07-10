package com.xiaochao.笔试;

import java.util.LinkedList;

public class 生产者消费者模式 {
    //消息类
//    @Data
    final class Message {
        private int id;
        private Object message;
    }
    //消息队列类
    //操作消息队列需要持有锁，同一时刻只能有一个线程操作消息队列，向外提供了两个安全的方法
    class MessageQueue {
        private LinkedList<Message> queue;
        private int capacity;

        public MessageQueue(int capacity) {
            this.capacity = capacity;
            queue = new LinkedList<>();
        }

        public Message take() {
            synchronized (queue) {
                while (queue.isEmpty()) {
//                    log.debug("没货了, wait");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message message = queue.removeFirst();
                //取走一个，队列不满了，所以可以唤醒其他线程
                queue.notifyAll();
                return message;
            }
        }

        public void put(Message message) {
            synchronized (queue) {
                while (queue.size() == capacity) {
//                    log.debug("库存已达上限, wait");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(message);
                queue.notifyAll();
            }
        }
    }
//    MessageQueue messageQueue = new MessageQueue(2);
//// 4 个生产者线程, 下载任务
//for (int i = 0; i < 4; i++) {
//        int id = i;
//        new Thread(() -> {
//            try {
//                log.debug("download...");
//                List<String> response = Downloader.download();
//                log.debug("try put message({})", id);
//                messageQueue.put(new Message(id, response));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }, "生产者" + i).start();
//    }
//// 1 个消费者线程, 处理结果
//new Thread(() -> {
//        while (true) {
//            Message message = messageQueue.take();
//            List<String> response = (List<String>) message.getMessage();
//            log.debug("take message({}): [{}] lines", message.getId(), response.size());
//        }
//    }, "消费者").start();
}
