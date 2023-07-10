package com.daimasuixianglu.shejishujujiegou;

/**
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * 实现 MyHashSet 类：
 * void add(key) 向哈希集合中插入值 key
 * bool contains(key) 返回哈希集合中是否存在这个值 key
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做   示例：
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 */
//简单数组解法
public class MyHashSet {
    boolean [] nodes=new boolean[1000009];
    public void add(int key){
        nodes[key]=true;
    }
    public void remove(int key){
        nodes[key] =false;
    }
    public boolean contains(int key){
        return nodes[key];
    }
}
//时间复杂度：O(1)
//空间复杂度：O(1)
/**
 *分桶数组解法
 * 事实上我们还可以实现一个类似「bitmap」数据结构。
 * 使用 int 中的每一位代表一个位置。
 * 由于数据范围为 0 <= key <= 10^6，我们最多需要的 int 数量不会超过 40000。
 * 因此我们可以建立一个 buckets 数组，数组装载的 int 类型数值。
 * 先对 key 进行 key / 32，确定当前 key 所在桶的位置（大概位置）
 * 再对 key 进行 key % 32，确定当前 key 所在桶中的哪一位（精确位置）
 * 根据位运算对「精确位置」进行修改。
 */
class MyHashSet01 {
    int[] bs = new int[40000];
    public void add(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        setVal(bucketIdx, bitIdx, true);
    }
    public void remove(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        setVal(bucketIdx, bitIdx, false);
    }
    public boolean contains(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        return getVal(bucketIdx, bitIdx);
    }
    void setVal(int bucket, int loc, boolean val) {
        if (val) {
            int u = bs[bucket] | (1 << loc);
            bs[bucket] = u;
        } else {
            int u = bs[bucket] & ~(1 << loc);
            bs[bucket] = u;
        }
    }
    boolean getVal(int bucket, int loc) {
        int u = (bs[bucket] >> loc) & 1;
        return u == 1;
    }
}
//时间复杂度：O(1)
//空间复杂度：O(1)
