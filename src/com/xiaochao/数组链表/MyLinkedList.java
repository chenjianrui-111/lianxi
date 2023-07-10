package com.xiaochao.数组链表;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E>{

    //双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    private final Node<E> head, tail;
    private int size;

    //构造函数初始化头尾节点
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;

        this.size = 0;
    }

    /***** 增 *****/
    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = head.next;
        //temp head
        x.next = temp;
        x.prev = head;

        head.next = x;
        temp.prev = x;
        size++;
    }


    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = tail.prev;
        //temp tail
        x.next = tail;
        x.prev = temp;

        tail.prev = x;
        temp.next = x;
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        Node<E> x =new Node<>(element);
        Node<E>  p = getNode(index);
        Node<E> temp =p.prev;

        x.next = p;
        x.prev = temp;

        p.prev = x;
        temp.next = x;

        size++;
    }

    /***** 删 *****/
    public E removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> x = head.next;
        Node<E> temp = x.next;
        //head -> x -> temp
        head.next = temp;
        temp.prev = head;

        x.next = x.prev = null;
        size--;
        return x.val;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> x = tail.prev;
        Node<E> temp = x.prev;
        //temp <-> x <-> tail
        temp.next = tail;
        tail.prev = temp;
        size--;
        return x.val;
    }

    public E remove(int index) {
        checkElementIndex(index);

        Node<E> p =getNode(index);
        Node<E> prev=p.prev;
        Node<E> next=p.next;

        prev.next = next;
        next.prev = prev;

        p.next = p.prev = null;

        size--;
        return p.val;
    }

    /***** 查 *****/
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }

    public E get(int index) {

        checkElementIndex(index);
        Node<E> p =getNode(index);
        return p.val;
    }

    /***** 改 *****/

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> p =getNode(index);

        E oldVal = p.val;
        p.val = element;
        return oldVal;
    }

    /***** 其他工具函数 *****/
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查index位置是否可以存在元素
     */

    private void checkElementIndex(int index) {
    }

    /**
     * 检查index位置是否可以添加元素
     */

    private void checkPositionIndex(int index) {
    }

    //返回index对应的Node
    private Node<E> getNode(int index){
        Node<E> p =head.next;
        for (int i = 0; i <index ; i++) {
            p = p.next;
        }
        return p;
    }

    private void display() {
        System.out.println("size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.print(p.val + " -> ");
        }
        System.out.println("null");
        System.out.println();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }
}
