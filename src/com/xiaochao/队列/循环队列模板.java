package com.xiaochao.队列;
import java.util.*;
public class 循环队列模板 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        CircleQueue cQueue = new CircleQueue(n1 + 1);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] s = str.split(" ");

            if (s[0].equals("push")) {
                cQueue.push(Integer.parseInt(s[1]));
            } else if (s[0].equals("pop")) {
                cQueue.pop();
            } else if (s[0].equals("front")) {
                cQueue.getFront();
            }
        }
    }
}

    class CircleQueue {
        private int maxSize;
        private int front;
        private int rear;
        private int[] circle;

        public CircleQueue(int maxCircleSize) {
            maxSize = maxCircleSize;
            circle = new int[maxSize];
            front = 0;
            rear = 0;
        }

        public boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public void push(int n) {
            if (isFull()) {
                System.out.println("full");
            } else {
                circle[rear] = n;
                rear = (rear + 1) % maxSize;
            }
        }

        public void pop() {
            if (isEmpty()) {
                System.out.println("empty");
            } else {
                System.out.println(circle[front]);
                front = (front + 1) % maxSize;
            }
        }

        public void getFront() {
            if (isEmpty()) {
                System.out.println("empty");
            } else {
                System.out.println(circle[front]);
            }

        }

    }

