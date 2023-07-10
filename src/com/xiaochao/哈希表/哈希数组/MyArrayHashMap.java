package com.xiaochao.哈希表.哈希数组;

import com.xiaochao.数组链表.MyArrayList;

import java.util.HashMap;
import java.util.Random;

//O(1)的时间内，等概率返回一个元素
public class MyArrayHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    //HashMap中存放：{key，索引}
    private final HashMap<K, Integer> map = new HashMap<>();
    //MyArrayList中存放节点:{索引,值}
    private final MyArrayList<Node<K, V>> arr = new MyArrayList<>();

    private final Random r = new Random();

    public MyArrayHashMap() {
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        // 获取 key 在 map 中的索引
        int i = map.get(key);
        return arr.get(i).val;
    }

    public V put(K key, V val) {
        if (containsKey(key)) {
            // 修改
            int i = map.get(key);
            Node<K, V> x = arr.get(i);
            V oldVal = x.val;
            x.val = val;
            return oldVal;
        }

        // 新增
        Node<K, V> x = new Node<>(key, val);
        arr.addLast(x);
        map.put(key, arr.size() - 1);

        return null;
    }

    //将数组中的元素换到数组最后面，完成删除的时间复杂度O(1)
    public V remove(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        //取到索引位置
        int i = map.get(key);
        //在arrList中得到节点
        Node<K, V> x = arr.get(i);
        // 1. 最后一个元素 e 和第 i 个元素 x 换位置
        Node<K, V> e = arr.get(arr.size() - 1);
        arr.set(i, e);
        arr.set(arr.size() - 1, x);

        // 2. 修改 map 中 e.key 对应的索引
        map.put(e.key, i);

        // 3. 在数组中 removeLast
        arr.removeLast();

        // 4. 在 map 中 remove x.key
        map.remove(x.key);

        return x.val;
    }

    // 随机弹出一个键
    public K pop() {
        int n = arr.size();
        //[0..n-1]
        int randomIndex = r.nextInt(n);
        return arr.get(randomIndex).key;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public static void main(String[] args) {
        MyArrayHashMap<Integer, Integer> map = new MyArrayHashMap<>();
        map.put(2, 1);
        map.put(2, 2);

        System.out.println(map.get(2));//2
    }
}
