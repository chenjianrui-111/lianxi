package com.xiaochao.哈希表;

/**
 * 使用线性探查法解决哈希冲突的 HashMap 的第二种实现，删除时直接用占位节点标记：
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyHashMap24<K, V> {

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

    // TODO: 被删除的 Node 的占位符
    private final Node<K, V> DUMMY = new Node<>(null, null);

    // 真正存储键值对的数组
    private Node<K, V>[] tables;
    // HashMap 中的键值对个数
    private int size;
    // HashMap 的容量，即 keys 和 vals 的 length
    private int cap;
    // 默认的初始化容量
    private static final int INIT_CAP = 4;

    public MyHashMap24() {
        this(INIT_CAP);
    }

    public MyHashMap24(int initCapacity) {
        size = 0;
        cap = initCapacity;
        tables = (Node<K, V>[]) new Node[initCapacity];
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
        if (i != -1) {
            // key 已存在，修改对应的 val
            Node<K, V> entry = tables[i];
            if (entry != null) {
                V oldVal = entry.val;
                entry.val = val;
                return oldVal;
            }
        }

        // key 不存在，插入
        Node<K, V> x = new Node<>(key, val);
        // 在 table 中找一个空位或者占位符
        i = hash(key);
        // 不仅找空的，还可以找占位符
        while (tables[i] != null && tables[i] != DUMMY) {
            i = (i + 1) % cap;
        }
        tables[i] = x;
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
        V deletedVal = tables[i].val;
        // 直接用占位符表示删除
        tables[i] = DUMMY;
        size--;

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

        return tables[i].val;
    }

    // 判断 key 是否存在 Map 中
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        int i = getNodeIndex(key);

        return i != -1;
    }

    // 对 key 进行线性探查，返回一个索引
    // 根据 keys[i] 是否为 null 判断是否找到对应的 key
    private int getNodeIndex(K key) {
        int i;
        int step = 0;
        for (i = hash(key); tables[i] != null; i = (i + 1) % cap) {
            Node<K, V> p = tables[i];
            // TODO:遇到占位符直接跳过
            if (p == DUMMY) {
                continue;
            }
            if (p.key.equals(key)) {
                return i;
            }
            step++;
            // TODO: 防止死循环
            if (step == tables.length) {
                resize(cap);
                return -1;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public List<K> keys() {
        LinkedList<K> keyList = new LinkedList<>();
        for (Node<K, V> entry : tables) {
            if (entry != null) {
                keyList.addLast(entry.key);
            }
        }
        return keyList;
    }

    public List<Map.Entry<K, V>> entries() {
        LinkedList<Map.Entry<K, V>> entryList = new LinkedList<>();
        for (Node<K, V> entry : tables) {
            if (entry != null) {
                entryList.addLast(entry);
            }
        }
        return entryList;
    }

    // 哈希函数，将键映射到 table 的索引
    // [0, table.length - 1]
    private int hash(K key) {
        // int: 0000 0000 0000 ... 0000
        //    : 0111 1111 1111 ... 1111
        return (key.hashCode() & 0x7fffffff) % cap;
    }

    private void resize(int newCap) {
        MyHashMap24<K, V> newMap = new MyHashMap24<>(newCap);
        for (Node<K, V> entry : tables) {
            if (entry != null && entry != DUMMY) {
                newMap.put(entry.key, entry.val);
            }
        }
        this.tables = newMap.tables;
        this.cap = newMap.cap;
    }
}
