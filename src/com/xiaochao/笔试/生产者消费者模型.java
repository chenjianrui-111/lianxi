package com.xiaochao.笔试;

import java.util.LinkedList;
import java.util.Queue;

public class 生产者消费者模型 {
    static class Buffer {
        private Queue<Integer> queue = new LinkedList<>();
        private int maxSize;

        public Buffer(int maxSize) {
            this.maxSize = maxSize;
        }

        public synchronized void produce(int data) throws InterruptedException {
            while (queue.size() == maxSize) {
                wait();
            }

            queue.add(data);
            System.out.println("Produced: " + data);
            notifyAll();
        }

        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }

            int data = queue.poll();
            System.out.println("Consumed: " + data);
            notifyAll();
            return data;
        }
    }

    static class Producer implements Runnable {
        private Buffer buffer;

        public Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        public void run() {
            int data = 0;
            while (true) {
                try {
                    buffer.produce(data++);
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private Buffer buffer;

        public Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        public void run() {
            while (true) {
                try {
                    buffer.consume();
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ProducerConsumerExample {
        public static void main(String[] args) {
            Buffer buffer = new Buffer(5);
            Producer producer = new Producer(buffer);
            Consumer consumer = new Consumer(buffer);

            new Thread(producer).start();
            new Thread(consumer).start();
        }

    }
}
