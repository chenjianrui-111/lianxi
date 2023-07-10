package com.xiaochao.树.线段树;

/**
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着
 * 在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 * 示例 1：
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 */

/**
 * 区间和 解决方案：
 * 针对不同的题目，我们有不同的方案可以选择（假设我们有一个数组）：
 * 数组不变，求区间和：「前缀和」、「树状数组」、「线段树」
 * 多次修改某个数（单点），求区间和：「树状数组」、「线段树」
 * 多次修改某个区间，输出最终结果：「差分」
 * 多次修改某个区间，求区间和：「线段树」、「树状数组」（看修改区间范围大小）
 * 多次将某个区间变成同一个数，求区间和：「线段树」、「树状数组」（看修改区间范围大小）
 * 这样看来，「线段树」能解决的问题是最多的，那我们是不是无论什么情况都写「线段树」呢？
 * 答案并不是，而且恰好相反，只有在我们遇到第 4 类问题，不得不写「线段树」的时候，我们才考虑线段树。
 * 因为「线段树」代码很长，而且常数很大，实际表现不算很好。我们只有在不得不用的时候才考虑「线段树」。
 * 总结一下，我们应该按这样的优先级进行考虑：
 * 1.简单求区间和，用「前缀和」
 * 2.多次将某个区间变成同一个数，用「线段树」
 * 3.其他情况，用「树状数组」
 */
//• 数组不变，区间查询：前缀和、树状数组、线段树；
// • 数组单点修改，区间查询：树状数组、线段树；
// • 数组区间修改，单点查询：差分、线段树；
// • 数组区间修改，区间查询：线段树。

/**
 * 由于涉及「区间修改」操作，因此我们需要对线段树进行持久化标记（懒标记），从
 * 而确保操作仍为 log 级别的复杂度。
 */
public class 航班预定统计 {
    class Node{
        int l,r,v,add;
        Node(int _l,int _r){
            l = _l;r = _r;
        }
    }
    int N =20009;
    Node[] tr= new Node[N*4];
    void pushup(int u){
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }
    void pushdown(int u){
        int add = tr[u].add;
        tr[u << 1].v += add;
        tr[u << 1].add += add;
        tr[u << 1 | 1].v += add;
        tr[u << 1 | 1].add += add;
        tr[u].add = 0;
    }
    void build(int u,int l,int r){
        tr[u] = new Node(l,r);
        if (l != r){
            int mid = l + r >> 1;
            build(u << 1,l,mid);
            build(u << 1,mid+1,r);
        }
    }
    void update(int u,int l,int r,int v){
        if (l <= tr[u].l && tr[u].r <= r){
            tr[u].v += v;
            tr[u].add += v;
        }else {
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if (l <= mid) update(u << 1,l,r,v);
            if (r > mid) update(u << 1 | 1,l,r,v);
            pushup(u);
        }
    }
    int query(int u,int l,int r){
        if (l <= tr[u].l && tr[u].r <= r) {
            return tr[u].v;
        } else {
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            int ans = 0;
            if (l <= mid) ans += query(u << 1, l, r);
            if (r > mid) ans += query(u << 1 | 1, l, r);
            return ans;
        }
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        build(1,1,n);
        for (int[] bo : bookings) {
            update(1,bo[0],bo[1],bo[2]);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n ; i++) {
            ans[i] = query(1,i+1,i+1);
        }
        return ans;
    }
}
//• 时间复杂度：线段树建树复杂度为 O(n)，其余操作复杂度为 O(log n)。对于本
//题，令 bs 长度为 m，整体复杂度为 O(m log n + n log n) • 空间复杂度：O(n)
