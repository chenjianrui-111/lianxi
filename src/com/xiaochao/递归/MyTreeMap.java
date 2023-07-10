package com.xiaochao.递归;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyTreeMap<K extends Comparable<K>, V> {

    private class TreeNode {
        K key;
        V val;
        TreeNode left, right;
        // 记录，以该节点为根的 BST 有多少个节点
        int size;

        TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
            this.size = 1;
            left = right = null;
        }
    }

    private TreeNode root = null;

    public MyTreeMap() {
    }

    /***** 增/改 *****/

    // 添加 key -> val 键值对，如果键 key 已存在，则将值修改为 val
    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        V oldVal = get(key);

        root = put(root, key, val);

        return oldVal;
    }

    private TreeNode put(TreeNode node, K key, V val) {
        if (node == null) {
            // 找不到 key（不存在）
            return new TreeNode(key, val);
        }
        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            // node.key > key，去左子树搜索 key
            node.left = put(node.left, key, val);
        } else if (cmp < 0) {
            // node.key < key
            node.right = put(node.right, key, val);
        } else {
            // node.key == key，找到了
            node.val = val;
        }

        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    /***** 删 *****/

    // 删除 key 并返回对应的 val
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (!containsKey(key)) {
            return null;
        }

        V deletedVal = get(key);
        root = remove(root, key);

        return deletedVal;
    }

    private TreeNode remove(TreeNode node, K key) {
        int cmp = node.key.compareTo(key);
        if (cmp > 0) {
            // node.key > key，去左子树找
            node.left = remove(node.left, key);
        } else if (cmp < 0) {
            // node.key < key，去右子树找
            node.right = remove(node.right, key);
        } else {
            // node.key == key，找到了 key，进行删除

            // 1. node 是叶子节点，左右子树都是 null
            // 2. node 左右子树有一个非空
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // 3. node 左右子树都是非空的
            // 3.1 得到左子树最大的那个节点的指针 leftMax
            TreeNode leftMax = maxNode(node.left);
            // 3.2 删除左子树最大的那个节点
            node.left = removeMax(node.left);
            // 3.3 用 leftMax 替换 node
            leftMax.left = node.left;
            leftMax.right = node.right;
            node = leftMax;
        }

        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    // 删除并返回 BST 中最小的那个 key
    public void removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMin(root);
    }

    // 删除并返回以 node 为根的 BST 中最小的那个节点
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            // 已经走到最左边的 TreeNode
            return node.right;
        }
        node.left = removeMin(node.left);

        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    // 删除并返回 BST 中最大的那个 key
    public void removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMax(root);
    }

    // 删除并返回以 node 为根的 BST 中最大的那个节点
    private TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            // 已经走到最右边的 TreeNode
            return node.left;
        }
        node.right = removeMax(node.right);

        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    /***** 查 *****/

    // 返回 key 对应的 val，如果 key 不存在，则返回 null
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        TreeNode x = get(root, key);
        if (x == null) {
            // key 不存在
            return null;
        }

        return x.val;
    }

    // 在以 node 为根的 BST 中查找 key
    private TreeNode get(TreeNode node, K key) {
        if (node == null) {
            // key 不存在
            return null;
        }

        int cmp = node.key.compareTo(key);

        // node.key > key
        if (cmp > 0) {
            return get(node.left, key);
        }

        // node.key < key
        if (cmp < 0) {
            return get(node.right, key);
        }

        // node.key == key
        return node;
    }

    // 返回小于等于 key 的最大的键
    public K floorKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        TreeNode x = floorKey(root, key);
        return x.key;
    }

    private TreeNode floorKey(TreeNode node, K key) {
        if (node == null) {
            // key 不存在
            return null;
        }

        int cmp = node.key.compareTo(key);

        // node.key > key，去左子树找
        if (cmp > 0) {
            return floorKey(node.left, key);
        }

        // node.key < key，去右子树找
        if (cmp < 0) {
            TreeNode x = floorKey(node.right, key);
            if (x == null) {
                return node;
            }
            return x;
        }

        // node.key == key
        return node;
    }

    // 返回大于等于 key 的最小的键
    public K ceilingKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        TreeNode x = ceilingKey(root, key);
        return x.key;
    }

    private TreeNode ceilingKey(TreeNode node, K key) {
        if (node == null) {
            // key 不存在
            return null;
        }

        int cmp = node.key.compareTo(key);

        // node.key > key，去左子树找
        if (cmp > 0) {
            TreeNode x = ceilingKey(node.left, key);
            if (x == null) {
                return node;
            }
            return x;
        }

        // node.key < key，去右子树找
        if (cmp < 0) {
            return ceilingKey(node.right, key);
        }

        // node.key == key
        return node;
    }

    // 返回小于 key 的键的个数
    public int rank(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        return rank(root, key);
    }

    // 返回以 node 为根的 BST 中小于 key 的键的个数
    private int rank(TreeNode node, K key) {
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            // key < node.key
            // 和 node 以及 node.right 没啥关系了
            // 因为它们太大了
            return rank(node.left, key);
        } else if (cmp > 0) {
            // key > node.key
            // node 和 node.left 左子树都是比 key 小的
            return size(node.left) + 1 + rank(node.right, key);
        } else {
            // key == node.key
            return size(node.left);
        }
    }

    // 返回索引为 i 的键，i 从 0 开始计算
    public K select(int i) {
        if (i < 0 || i >= size()) {
            throw new IllegalArgumentException();
        }

        TreeNode x = select(root, i);
        return x.key;
    }

    // 返回以 node 为根的 BST 中索引为 i 的那个节点
    private TreeNode select(TreeNode node, int i) {
        int n = size(node.left);

        if (n > i) {
            // n == 10, i == 3
            return select(node.left, i);
        } else if (n < i) {
            // n == 3, i == 10
            return select(node.right, i - n - 1);
        } else {
            // i == n
            // node 就是索引为 i 的那个节点
            return node;
        }
    }

    // 返回 BST 中最大的键
    public K maxKey() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        TreeNode p = maxNode(root);
        return p.key;
    }

    private TreeNode maxNode(TreeNode p) {
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    // 返回 BST 中最小的键
    public K minKey() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        TreeNode p = minNode(root);
        return p.key;
    }

    private TreeNode minNode(TreeNode p) {
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    // 判断 key 是否存在 Map 中
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        return get(root, key) != null;
    }

    /***** 工具函数 *****/

    // 从小到大返回所有键
    public Iterable<K> keys() {
        if (isEmpty()) {
            return new LinkedList<>();
        }
        LinkedList<K> list = new LinkedList<>();
        traverse(root, list);
        return list;
    }

    // 中序遍历 BST
    private void traverse(TreeNode node, LinkedList<K> list) {
        if (node == null) {
            return;
        }
        traverse(node.left, list);
        // 中序遍历
        list.addLast(node.key);
        traverse(node.right, list);
    }

    // 从小到大返回闭区间 [min, max] 中的键
    public Iterable<K> keys(K min, K max) {
        if (min == null) throw new IllegalArgumentException("min is null");
        if (max == null) throw new IllegalArgumentException("max is null");

        LinkedList<K> list = new LinkedList<>();
        traverse(root, list, min, max);
        return list;
    }

    // 中序遍历 BST
    private void traverse(TreeNode node, LinkedList<K> list, K min, K max) {
        if (node == null) {
            return;
        }

        int cmpMin = min.compareTo(node.key);
        int cmpMax = max.compareTo(node.key);

        if (cmpMin < 0) {
            // min < node.key
            traverse(node.left, list);
        }

        // 中序遍历 min <= node.key <= max
        if (cmpMin <= 0 && cmpMax >= 0) {
            list.addLast(node.key);
        }

        if (cmpMax > 0) {
            // max > node.key
            traverse(node.right, list);
        }
    }

    public int size() {
        return size(root);
    }

    // 返回以 node 节点为根的 BST 有多少节点
    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}
