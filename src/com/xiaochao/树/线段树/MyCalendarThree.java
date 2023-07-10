package com.xiaochao.树.线段树;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
 * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 * MyCalendarThree() 初始化对象。
 * int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 * 示例：
 * 输入：
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * 输出：
 * [null, 1, 1, 2, 3, 3, 3]
 * 解释：
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(50, 60); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(10, 40); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是 2 次预订。
 * myCalendarThree.book(5, 15); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
 * myCalendarThree.book(5, 10); // 返回 3
 * myCalendarThree.book(25, 55); // 返回 3
 * 提示：
 * 0 <= start < end <= 109
 * 每个测试用例，调用 book 函数最多不超过 400次
 */
public class MyCalendarThree {
    class Node {
        int ls, rs, add, max;   // ls, rs 为左右子节点在tr中索引(触手); add 懒标记; max 维护区间最大值
    }

    int N = (int) 1e9, M = 120010, cnt = 1; // N 区间范围; M 节点个数; cnt 节点在tr中的索引
    Node[] tr = new Node[M];

    public MyCalendarThree() {
        tr[0] = new Node(); // 创建根节点
    }
    void update(int u,int lc,int rc,int l,int r,int val){
        if (lc >= l && rc <= r){
            tr[u].add += val;
            tr[u].max += val;
            return;
        }
        lazyCreate(u);
        pushDown(u);
        int mid = lc +(rc - lc) / 2;
        if (l <= mid) update(tr[u].ls,lc,mid,l,r,val);
        if (r > mid) update(tr[u].rs,mid+1,rc,l,r,val);
        pushup(u);
    }

    /*
   查询区间[l,r]的最大值
     */
    public int query(int u, int lc, int rc, int l, int r) {
        if (lc >= l && rc <= r) return tr[u].max;
        lazyCreate(u);  // 动态开点
        pushDown(u);    // 下传懒标记
        int mid = lc + (rc - lc) / 2, res = 0;
        if (l <= mid) res = query(tr[u].ls, lc, mid, l, r);
        if (r > mid) res = Math.max(res, query(tr[u].rs, mid + 1, rc, l, r));   // 记得取左右子节点的最大值
        return res; // 返回最大值
    }
    public void lazyCreate(int u) {
        if (tr[u] == null) tr[u] = new Node();
        if (tr[u].ls == 0) {    // 左子节点不存在 -> 创建并构建连接
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }
    }
    void pushup(int u) {
        tr[u].max = Math.max(tr[tr[u].ls].max, tr[tr[u].rs].max);
    }
    public void pushDown(int u) {
        int v = tr[u].add;  // 节点u下传下来的懒标记
        if (v != 0) {   // 当且仅当懒标记不为0才进行下传
            // 下传懒标记至子节点(累计)
            tr[tr[u].ls].add += v;
            tr[tr[u].rs].add += v;
            // 更新左右子节点的值(累计)
            tr[tr[u].ls].max += v;
            tr[tr[u].rs].max += v;
            tr[u].add = 0;  // 下传懒标记完成撤销u的懒标记
        }
    }
    public int book(int start, int end) {
        update(1,1,N+1,start+1,end,1);
        return query(1,1,N+1,1,N+1);
    }
}

/**
 * 带懒标记的分块（TLE）
 * 实际上，还存在另外一种十分值得学习的算法：分块。
 * 但该做法被奇怪的测评机制卡掉，是给每个样例都定了执行用时吗 🤣 ？
 * 旧题解没有这种做法，今天补充的，我们可以大概讲讲「分块」算法是如何解决涉及「区间修改」，也就是带懒标记的问题。
 * 对于本题，我们设定两个块数组 max 和 add，其中 max[idx] 含义为块编号为 idx 的连续区间的最大重复值，而 add[idx] 代表块编号的懒标记，同时使用「哈希表」记录下某些具体位置的覆盖次数。
 * 然后我们考虑如何指定块大小，设定一个合理的块大小是减少运算量的关键。
 * 通常情况下，我们会设定块大小为 ，从而确保块内最多不超过  个元素，同时块的总个数也不超过 ，即无论我们是块内暴力，还是操作多个块，复杂度均为 。因此对于本题而言，设定块大小为 （经试验，调成  能够通过较多样例），较为合适。
 * 对于涉及「区间修改」的分块算法，我们需要在每次修改和查询前，先将懒标记下传到区间的每个元素中。
 * 然后考虑如何处理「块内」和「块间」操作：
 * 块内操作：
 * 块内更新：直接操作 map 进行累加，同时使用更新后的 map 来维护相应的 max[idx]；
 * 块内查询：直接查询 map 即可；
 * 块间操作：
 * 块间更新：直接更新懒标记 add[idx] 即可，同时由于我们是对整个区间进行累加操作，因此最大值必然也会同步被累加，因此同步更新 max[idx]；
 * 块间查询：直接查询 max[idx] 即可。
 */
class MyCalendarThree2 {
    static int N = (int)1e9 + 10, M = 1200010, SZ = N / M + 10;
    static int[] max = new int[M], add = new int[M];
    static Map<Integer, Integer> map = new HashMap<>();
    int minv = -1, maxv = -1;
    int getIdx(int x) {
        return x / SZ;
    }
    void add(int l, int r) {
        pushdown(l); pushdown(r);
        if (getIdx(l) == getIdx(r)) {
            for (int k = l; k <= r; k++) {
                map.put(k, map.getOrDefault(k, 0) + 1);
                max[getIdx(k)] = Math.max(max[getIdx(k)], map.get(k));
            }
        } else {
            int i = l, j = r;
            while (getIdx(i) == getIdx(l)) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                max[getIdx(i)] = Math.max(max[getIdx(i)], map.get(i));
                i++;
            }
            while (getIdx(j) == getIdx(r)) {
                map.put(j, map.getOrDefault(j, 0) + 1);
                max[getIdx(j)] = Math.max(max[getIdx(j)], map.get(j));
                j--;
            }
            for (int k = getIdx(i); k <= getIdx(j); k++) {
                max[k]++; add[k]++;
            }
        }
    }
    int query(int l, int r) {
        pushdown(l); pushdown(r);
        int ans = 0;
        if (getIdx(l) == getIdx(r)) {
            for (int k = l; k <= r; k++) ans = Math.max(ans, map.getOrDefault(k, 0));
        } else {
            int i = l, j = r;
            while (getIdx(i) == getIdx(l)) ans = Math.max(ans, map.getOrDefault(i++, 0));
            while (getIdx(j) == getIdx(r)) ans = Math.max(ans, map.getOrDefault(j--, 0));
            for (int k = getIdx(i); k <= getIdx(j); k++) ans = Math.max(ans, max[k]);
        }
        return ans;
    }
    void pushdown(int x) {
        int idx = getIdx(x);
        if (add[idx] == 0) return ;
        int i = x, j = x + 1;
        while (getIdx(i) == idx) map.put(i, map.getOrDefault(i--, 0) + add[idx]);
        while (getIdx(j) == idx) map.put(j, map.getOrDefault(j++, 0) + add[idx]);
        add[idx] = 0;
    }
    public MyCalendarThree2() {
        Arrays.fill(max, 0);
        Arrays.fill(add, 0);
        map.clear();
    }
    public int book(int start, int end) {
        add(start + 1, end);
        minv = minv == -1 ? start : Math.min(minv, start);
        maxv = maxv == -1 ? end + 1 : Math.max(maxv, end + 1);
        return query(minv, maxv);
    }
}
//时间复杂度：令 M 为块大小，复杂度为O(根号M)
//空间复杂度：O(C * 根号M)，其中 C = 1e3 为最大调用次数
