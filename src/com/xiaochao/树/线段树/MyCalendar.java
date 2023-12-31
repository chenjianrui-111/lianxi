package com.xiaochao.树.线段树;

/**
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。
 * 实现 MyCalendar 类：
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 * 示例：
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。
 * 提示：
 * 0 <= start < end <= 10^9
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 */

/**
 * 线段树（动态开点）
 * 线段树维护的节点信息包括：
 * ls/rs: 分别代表当前节点的左右子节点在线段树数组 tr 中的下标；
 * add: 懒标记；
 * val: 为当前区间的所包含的点的数量。
 * 对于常规的线段树实现来说，都是一开始就调用 build 操作创建空树，而线段树一般以「满二叉树」的形式用数组存储，因此需要 4*n 的空间，并且这些空间在起始 build 空树的时候已经锁死。
 * 如果一道题仅仅是「值域很大」的离线题（提前知晓所有的询问），我们还能通过「离散化」来进行处理，将值域映射到一个小空间去，从而解决 MLE 问题。
 * 但对于本题而言，由于「强制在线」的原因，我们无法进行「离散化」，同时值域大小达到 1e9 级别，因此如果我们想要使用「线段树」进行求解，只能采取「动态开点」的方式进行。
 * 动态开点的优势在于，不需要事前构造空树，而是在插入操作 add 和查询操作 query 时根据访问需要进行「开点」操作。由于我们不保证查询和插入都是连续的，因此对于父节点 u 而言，
 * 我们不能通过 u << 1 和 u << 1 | 1 的固定方式进行访问，而要将节点 tr[u] 的左右节点所在 tr 数组的下标进行存储，分别记为 ls 和 rs 属性。对于 tr[u].ls = 0 和 tr[u].rs=0  则是代表子节点尚未被创建，当需要访问到它们，而又尚未创建的时候，则将其进行创建。
 * 由于存在「懒标记」，线段树的插入和查询都是 logn  的，因此我们在单次操作的时候，最多会创建数量级为 logn 的点，因此空间复杂度为 O(mlogn) ，而不是 O(4*n)，而开点数的预估需不能仅仅根据 logn 来进行，还要对常熟进行分析，才能得到准确的点数上界。
 * 动态开点相比于原始的线段树实现，本质仍是使用「满二叉树」的形式进行存储，只不过是按需创建区间，如果我们是按照连续段进行查询或插入，最坏情况下仍然会占到 $4 n的间
 因此盲猜\log{n}的常数在4左右，保守一点可以直接估算到6，因此我们可以估算点数为6 m * \log{n}，其中n = 1e9和
 m = 1e3$ 分别代表值域大小和查询次数。
 * 当然一个比较实用的估点方式可以「尽可能的多开点数」，利用题目给定的空间上界和我们创建的自定义类（结构体）的大小，尽可能的多开（ Java 的  可以开到  以上）。
 */
public class MyCalendar {
    class Node {
        // ls 和 rs 分别代表当前节点的左右子节点在 tr 的下标
        // val 代表当前节点有多少数
        // add 为懒标记
        int ls, rs, add, val;
    }
    int N = (int)1e9, M = 120010, cnt = 1;
    Node[] tr = new Node[M];
    void update(int u, int lc, int rc, int l, int r, int v) {
        if (l <= lc && rc <= r) {
            tr[u].val += (rc - lc + 1) * v;
            tr[u].add += v;
            return;
        }
        lazyCreate(u);
        pushdown(u, rc - lc + 1);
        int mid = lc + rc >> 1;
        if (l <= mid) update(tr[u].ls, lc, mid, l, r, v);
        if (r > mid) update(tr[u].rs, mid + 1, rc, l, r, v);
        pushup(u);
    }
    int query(int u, int lc, int rc, int l, int r) {
        if (l <= lc && rc <= r) return tr[u].val;
        lazyCreate(u);
        pushdown(u, rc - lc + 1);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans = query(tr[u].ls, lc, mid, l, r);
        if (r > mid) ans += query(tr[u].rs, mid + 1, rc, l, r);
        return ans;
    }
    void lazyCreate(int u) {
        if (tr[u] == null) tr[u] = new Node();
        if (tr[u].ls == 0) {
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }
    }
    void pushdown(int u, int len) {
        tr[tr[u].ls].add += tr[u].add; tr[tr[u].rs].add += tr[u].add;
        tr[tr[u].ls].val += (len - len / 2) * tr[u].add; tr[tr[u].rs].val += len / 2 * tr[u].add;
        tr[u].add = 0;
    }
    void pushup(int u) {
        tr[u].val = tr[tr[u].ls].val + tr[tr[u].rs].val;
    }

    public boolean book(int start, int end) {
        if (query(1, 1, N + 1, start + 1, end) > 0) return false;
        update(1, 1, N + 1, start + 1, end, 1);
        return true;
    }
}
//时间复杂度：令 n 为值域大小，本题固定为1e9 ，线段树的查询和增加复杂度均为 O(logn)
//空间复杂度：令询问数量为 ，复杂度为 O(mlogn)
