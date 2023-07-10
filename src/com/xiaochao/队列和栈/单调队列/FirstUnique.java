package com.xiaochao.队列和栈.单调队列;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一系列整数，插入一个队列中，找出队列中第一个唯一整数。
 * 实现 FirstUnique 类：
 * FirstUnique(int[] nums) 用数组里的数字初始化队列。
 * int showFirstUnique() 返回队列中的第一个唯一整数的值。如果没有唯一整数，返回 -1。
 * void add(int value) 将 value 插入队列中。
 * 示例 1：
 * 输入：
 * ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
 * [[[2,3,5]],[],[5],[],[2],[],[3],[]]
 * 输出：
 * [null,2,null,2,null,3,null,-1]
 * 解释：
 * FirstUnique firstUnique = new FirstUnique([2,3,5]);
 * firstUnique.showFirstUnique(); // 返回 2
 * firstUnique.add(5);            // 此时队列为 [2,3,5,5]
 * firstUnique.showFirstUnique(); // 返回 2
 * firstUnique.add(2);            // 此时队列为 [2,3,5,5,2]
 * firstUnique.showFirstUnique(); // 返回 3
 * firstUnique.add(3);            // 此时队列为 [2,3,5,5,2,3]
 * firstUnique.showFirstUnique(); // 返回 -1
 */
public class FirstUnique {
    // 记录每个元素的出现次数
    HashMap<Integer, Integer> count = new HashMap<>();
    // 从队尾添加元素，队头取出元素
    Queue<Integer> q = new LinkedList<>();

    public FirstUnique(int[] nums) {
        // 初始化队列和哈希表
        for (int elem : nums) {
            add(elem);
        }
    }

    public int showFirstUnique() {
        while (!q.isEmpty()) {
            int elem = q.peek();
            if (count.get(elem) > 1) {
                // 队列中的非唯一元素都弹出
                q.poll();
            } else {
                // 直到找到第一个唯一元素
                return elem;
            }
        }
        // 如果队列弹空了还找不到，那就不存在唯一元素
        return -1;
    }

    public void add(int value) {
        // 加入队列
        q.offer(value);
        // 哈希表中记录出现次数
        int valCount = count.getOrDefault(value, 0);
        count.put(value, valCount + 1);
    }
}
