package com.xiaochao.哈希表.哈希集合;

import com.xiaochao.哈希表.哈希链表.MyLinkedHashMap;
import java.util.Iterator;

public class MyLinkedHashSet<K> {
    private MyLinkedHashMap<K, Object> map = new MyLinkedHashMap<>();
    // val 占位符
    private static final Object PRESENT = new Object();

    public boolean add(K k) {
        return map.put(k, PRESENT) == null;
    }

    public boolean remove(K k) {
        return map.remove(k) == PRESENT;
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

    public Iterator<K> iterator() {
        return map.keys().iterator();
    }

}
