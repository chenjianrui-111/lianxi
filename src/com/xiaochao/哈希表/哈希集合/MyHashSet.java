package com.xiaochao.哈希表.哈希集合;

import java.util.HashMap;

public class MyHashSet<K> {
    // val 占位符
    private static final Object PRESENT = new Object();
    // 底层 HashMap
    private final HashMap<K, Object> map = new HashMap<>();

    public boolean add(K k) {
        if (map.containsKey(k)){
            return false;
        }
        map.put(k,PRESENT);
        return true;
    }

    public boolean remove(K k) {
       if (map.containsKey(k)){
           map.remove(k);
           return true;
       }
       return false;
    }

    public boolean contains(K k) {
        return map.containsKey(k);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
