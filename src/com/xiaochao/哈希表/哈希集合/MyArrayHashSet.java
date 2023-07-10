package com.xiaochao.哈希表.哈希集合;

import com.xiaochao.哈希表.哈希数组.MyArrayHashMap;

public class MyArrayHashSet<K, V> {

    private final static Object PRESENT = new Object();

    private MyArrayHashMap<K, Object> map = new MyArrayHashMap<>();

    public MyArrayHashSet() {
    }

    public boolean add(K key) {
        if (contains(key)) {
            return false;
        }

        map.put(key, PRESENT);
        return true;
    }

    public boolean remove(K key) {
        if (!map.containsKey(key)) {
            return false;
        }
        map.remove(key);
        return true;
    }

    public K pop() {
        return map.pop();
    }

    public boolean contains(K key) {
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
