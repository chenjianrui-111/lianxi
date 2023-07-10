package com.xiaochao.哈希表;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 使用线性探查法解决哈希冲突的 HashMap 的第一种实现，删除时通过 rehash 过程避免出现空洞：
 * @param <K>
 * @param <V>
 */
public class MyHashMap23<K, V> {

    private static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return val;
        }

        @Override
        public V setValue(V val) {
            V oldVal = this.val;
            this.val = val;
            return oldVal;
        }
    }

    // 真正存储键值对的数组
    private Node<K, V>[] table;
    // HashMap 中的键值对个数
    private int size;
    // HashMap 的容量，即 keys 和 vals 的 length
    private int cap;
    // 默认的初始化容量
    private static final int INIT_CAP = 4;

    public MyHashMap23() {
        this(INIT_CAP);
    }

    public MyHashMap23(int initCapacity) {
        size = 0;
        cap = initCapacity;
        table = (Node<K, V>[]) new Node[initCapacity];
    }

    /***** 增/改 *****/

    // 添加 key -> val 键值对
    // 如果键 key 已存在，则将值修改为 val
    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        if (size >= cap / 2) {
            resize(cap * 2);
        }

        int i = getNodeIndex(key);

        // key 已存在，修改对应的 val
        if (i != -1) {
            Node<K, V> entry = table[i];
            V oldVal = entry.val;
            entry.val = val;
            return oldVal;
        }

        // key 不存在，找个空位插入
        i = hash(key);
        while (table[i] != null) {
            i = (i + 1) % cap;
        }
        // 此时 table[i] 为一个空位
        Node<K, V> x = new Node<>(key, val);
        table[i] = x;
        size++;

        return null;
    }

    /***** 删 *****/

    // 删除 key 和对应的 val，并返回 val
    // 若 key 不存在，则返回 null
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        if (size < cap / 8) {
            resize(cap / 2);
        }

        int i = getNodeIndex(key);

        if (i == -1) {
            // key 不存在，不需要 remove
            return null;
        }

        // 开始 remove
        V deletedVal = table[i].val;
        table[i] = null;
        size--;
        // rehash 保持连续性
        i = (i + 1) % cap;
        for (; table[i] != null; i = (i + 1) % cap) {
            Node<K, V> entry = table[i];
            //先删掉
            table[i] = null;
           //再插入
            put(entry.key, entry.val);
            // put 里面又会加一
            size--;
        }

        return deletedVal;
    }

    /***** 查 *****/

    // 返回 key 对应的 val
    // 如果 key 不存在，则返回 null
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        int i = getNodeIndex(key);

        if (i == -1) {
            return null;
        }

        return table[i].val;
    }

    // 判断 key 是否存在 Map 中
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        return getNodeIndex(key) != -1;
    }

    /***** 其他工具函数 *****/

    public List<K> keys() {
        LinkedList<K> keyList = new LinkedList<>();
        for (Node<K, V> entry : table) {
            if (entry != null) {
                keyList.addLast(entry.key);
            }
        }
        return keyList;
    }

    public List<Map.Entry<K, V>> entries() {
        LinkedList<Map.Entry<K, V>> entryList = new LinkedList<>();
        for (Node<K, V> entry : table) {
            if (entry != null) {
                entryList.addLast(entry);
            }
        }
        return entryList;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 哈希函数，将键映射到 table 的索引
    // [0, table.length - 1]
    private int hash(K key) {
        // int: 0000 0000 0000 ... 0000
        //    : 0111 1111 1111 ... 1111
        return (key.hashCode() & 0x7fffffff) % cap;
    }

    // 对 key 进行线性探查，返回一个索引
    // 若返回 -1 说明没有找到
    private int getNodeIndex(K key) {
        int i;
        for (i = hash(key); table[i] != null; i = (i + 1) % cap) {
            if (table[i].key.equals(key))
                return i;
        }
        return -1;
    }

    private void resize(int newCap) {
        MyHashMap23<K, V> newMap = new MyHashMap23<>(newCap);
        for (Node<K, V> entry : table) {
            if (entry != null) {
                newMap.put(entry.key, entry.val);
            }
        }
        this.table = newMap.table;
        this.cap = newMap.cap;
    }
}
