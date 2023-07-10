package com.xiaochao.队列和栈;

/**
 * 设计双端队列，实现 MyCircularDeque 类:
 *
 * MyCircularDeque(int k)：构造函数，双端队列最大为 k。
 * boolean insertFront()：将一个元素添加到双端队列头部。如果操作成功返回 true，否则返回 false。
 * boolean insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true，否则返回 false。
 * boolean deleteFront()：从双端队列头部删除一个元素。如果操作成功返回 true，否则返回 false。
 * boolean deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true，否则返回 false。
 * int getFront() ：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * int getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * boolean isEmpty()：若双端队列为空，则返回 true，否则返回 false。
 * boolean isFull()：若双端队列满了，则返回 true，否则返回false。
 */
public class MyCircularDeque {

    private int cap;
    private MyArrayDeque<Integer> dq = new MyArrayDeque<>();

    public MyCircularDeque(int k) {
        this.cap = k;
    }

    public boolean insertFront(int value) {
        if (dq.size() == cap){
            return false;
        }
        dq.addFirst(value);
        return true;
    }

    public boolean insertLast(int value) {
        if (dq.size() == cap){
            return false;
        }
        dq.addLast(value);
        return true;
    }

    public boolean deleteFront() {
        if (dq.isEmpty()){
            return false;
        }
        dq.removeFirst();
        return true;
    }

    public boolean deleteLast() {
        if (dq.isEmpty()){
            return false;
        }
        dq.removeLast();
        return true;
    }

    public int getFront() {
        if (dq.isEmpty()){
            return -1;
        }
        return dq.getFirst();
    }

    public int getRear() {
        if (dq.isEmpty()){
            return -1;
        }
        return dq.getLast();
    }

    public boolean isEmpty() {
        return dq.isEmpty();
    }

    public boolean isFull() {
        return dq.size() == cap;
    }
}
