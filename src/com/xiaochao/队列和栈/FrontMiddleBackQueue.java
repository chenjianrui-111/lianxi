package com.xiaochao.队列和栈;

import java.util.LinkedList;

/**
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 *
 * 请你完成 FrontMiddleBack 类：
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 */
public class FrontMiddleBackQueue {

    // 用两个列表表示队列的左右两部分，一遍从中间操作元素

    LinkedList<Integer> left = new LinkedList<>();
    LinkedList<Integer> right = new LinkedList<>();

    // 如果是奇数个元素，维护左边少右边多，所以：
    // 1、如果有偶数个元素时，pushMiddle 优先向右边添加
    // 2、如果有奇数个元素时，popMiddle 优先从右边删除
    // 3、如果只有 1 个元素，popFront 的时候，要去右边删除
    // 要把以上三个特点写到代码里，才能保证细节不出错

    // 维护左边少右边多的状态，每次增删元素之后都要执行一次
    private void balance() {
        // 右边最多比左边多一个元素
        if (right.size() > left.size() + 1) {
            // 右边多，匀一个给左边
            left.addLast(right.removeFirst());
        }
        if (left.size() > right.size()) {
            // 左边多，匀一个给右边
            right.addFirst(left.removeLast());
        }
    }

    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        if (size() % 2 == 0) {
            // 如果有偶数个元素时，pushMiddle 优先向右边添加
            right.addFirst(val);
        } else {
            left.addLast(val);
        }
        balance();
    }

    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    public int popFront() {
        if (size() == 0){
            return -1;
        }
        if (size() == 1){
            // 如果只有 1 个元素，popFront 的时候，要去右边删除
            return right.removeFirst();
        }
        int e = left.removeFirst();
        balance();
        return e;
    }

    public int popMiddle() {
        if (size() == 0) {
            return -1;
        }
        int e;
        if (size() % 2 == 0){
            e = left.removeLast();
        }else {
            // 如果有奇数个元素时，popMiddle 优先从右边删除
            e = right.removeFirst();
        }
        balance();
        return e;
    }

    public int popBack() {
        if (size() == 0) {
            return -1;
        }
        int e =right.removeLast();
        balance();
        return e;
    }

    public int size() {
        return left.size() + right.size();
    }
}
