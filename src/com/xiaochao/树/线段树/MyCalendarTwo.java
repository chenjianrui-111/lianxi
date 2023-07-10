package com.xiaochao.树.线段树;

/**
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * 示例：
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * 解释：
 * 前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
 * 第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
 * 第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
 * 第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
 * 时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
 * 提示：
 * 每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。
 * 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
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
 因此盲猜\log{n}的常数在4左右，保守一点可以直接估算到6，因此我们可以估算点数为6 m * \log{n}，其中n = 1e9和 m = 1e3$ 分别代表值域大小和查询次数。
 * 当然一个比较实用的估点方式可以「尽可能的多开点数」，利用题目给定的空间上界和我们创建的自定义类（结构体）的大小，尽可能的多开（ Java 的  可以开到  以上）。
 */
public class MyCalendarTwo {
      /*
        这题也可以用线段树进行求解:start与end的范围为[0,1e9]
        不过相比于Q729 我的日程安排表I 这里要维护的val为区间的最大值max
        当区间的最大值>=2就说明已经有两个重叠的预订,第3个预订就不能book了
        查询和更新一次时间复杂度为:O(logN) 空间复杂度为O(MlogN),M为操作次数
         */

    /*
    节点类
     */
    class Node {
        int ls, rs, add, max;   // ls, rs 为左右子节点在tr中索引(触手); add 懒标记; max 维护区间最大值
    }

    int N = (int) 1e9, M = 120010, cnt = 1; // N 区间范围; M 节点个数; cnt 节点在tr中的索引
    Node[] tr = new Node[M];

    public MyCalendarTwo() {
        tr[0] = new Node(); // 创建根节点
    }

    /*
    更新区间[l,r] 值为val
     */
    public void update(int u, int lc, int rc, int l, int r, int val) {
        // [l,r]在u表示的区间内
        if (lc >= l && rc <= r) {
            tr[u].add += val;   // 懒标记要累计(例如覆盖了2次)
            // 最大值是max(curVal,curVal+val)=curVal+val -> max += val;
            tr[u].max += val;
            return; // 结束
        }
        // [l,r]不在u内
        lazyCreate(u);  // 动态开点
        pushDown(u);    // 下传懒标记
        int mid = lc + (rc - lc) / 2;
        if (l <= mid) update(tr[u].ls, lc, mid, l, r, val); // 占据到左子树
        if (r > mid) update(tr[u].rs, mid + 1, rc, l, r, val);  // 占据到右子树
        pushUp(u);  // 回溯
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

    /*
    按需动态开点
     */
    public void lazyCreate(int u) {
        if (tr[u].ls == 0) {    // 左子节点不存在 -> 创建并构建连接
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }
    }

    /*
    下传懒标记
     */
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

    /*
    回溯更新u的最大值
     */
    public void pushUp(int u) {
        tr[u].max = Math.max(tr[tr[u].ls].max, tr[tr[u].rs].max);
    }

    public boolean book(int start, int end) {
        // 最大值>=2说明区间[start,end-1]存在某个点覆盖了2次
        if (query(0, 0, N, start, end - 1) >= 2) return false;
        update(0, 0, N, start, end - 1, 1);
        return true;
    }
}
//时间复杂度：令 n 为值域大小，本题固定为 1e9，线段树的查询和增加复杂度均为 O(logn)
//空间复杂度：令询问数量为 m，复杂度为 O(mlogn)
/**
*线段树（动态开点）- 动态指针
 * 利用「动态指针」实现的「动态开点」可以有效避免数组估点问题，更重要的是可以有效避免 new 大数组的初始化开销，
 * 对于 LC 这种还跟你算所有样例总时长的 OJ 来说，在不考虑 static 优化/全局数组优化 的情况下，动态指针的方式要比估点的方式来得好。
*/
class MyCalendarTwo2 {
    class Node {
        Node ls, rs;
        int max, add;
    }
    int N = (int)1e9;
    Node root = new Node();
    void update(Node node, int lc, int rc, int l, int r, int v) {
        if (l <= lc && rc <= r) {
            node.add += v;
            node.max += v;
            return ;
        }
        pushdown(node);
        int mid = lc + rc >> 1;
        if (l <= mid) update(node.ls, lc, mid, l, r, v);
        if (r > mid) update(node.rs, mid + 1, rc, l, r, v);
        pushup(node);
    }
    int query(Node node, int lc, int rc, int l, int r) {
        if (l <= lc && rc <= r) return node.max;
        pushdown(node);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans = query(node.ls, lc, mid, l, r);
        if (r > mid) ans = Math.max(ans, query(node.rs, mid + 1, rc, l, r));
        return ans;
    }
    void pushdown(Node node) {
        if (node.ls == null) node.ls = new Node();
        if (node.rs == null) node.rs = new Node();
        int add = node.add;
        node.ls.max += add; node.rs.max += add;
        node.ls.add += add; node.rs.add += add;
        node.add = 0;
    }
    void pushup(Node node) {
        node.max = Math.max(node.ls.max, node.rs.max);
    }
    public boolean book(int start, int end) {
        if (query(root, 0, N, start, end - 1) >= 2) return false;
        update(root, 0, N, start, end - 1, 1);
        return true;
    }
}
