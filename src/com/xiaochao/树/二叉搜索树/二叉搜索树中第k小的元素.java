package com.xiaochao.树.二叉搜索树;

import com.cjr.shu.TreeNode;

import java.util.*;

public class 二叉搜索树中第k小的元素 {
    public int kthSmallest(TreeNode root, int k) {
        traverse(root,k);
        return res;
    }
    //记录结果
    int res = 0;
    //记录当前元素排名
    int rank = 0;
    void traverse(TreeNode root,int k){
        if (root == null){
            return;
        }
        traverse(root.left,k);
        //中序遍历代码位置
        rank++;
        if (k == rank){
            //找到第k小的元素
            res = root.val;
            return;
        }
        traverse(root.right,k);
    }
}

/**
 * 树的遍历 + 排序
 * 朴素的做法是先对二叉树进行一次完整遍历，将所有节点存入列表中，最后对列表排序后返回目标值。
 * 树的遍历可以使用 DFS 或 BFS
 */
class Solution5{
    List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        dfs(root);
        Collections.sort(list);
        return list.get(k - 1);
    }
    void dfs(TreeNode root){
        if (root == null) return;
        list.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
//时间复杂度：树的遍历时间复杂度为 O(n)；排序的复杂度为 O(nlogn)。整体复杂度为 O(nlogn)
//空间复杂度：O(n)

/**
 *树的遍历 + 优先队列（堆）
 * 相比于先直接拿到所有节点再排序的解法一，另外一种做法是使用「优先队列（堆）」来做。
 * 由于我们返回的是第 k 小的数，因此我们可以构建一个容量为 k 的大根堆。
 * 根据大根堆的元素个数和当前节点与堆顶元素的关系来分情况讨论：
 * 大根堆元素不足 k 个：直接将当前节点值放入大根堆；
 * 大根堆元素为 k 个，根据堆顶元素和当前节点值的大小关系进一步分情况讨论：
 * 如果当前节点值元素大于堆顶元素，说明当前节点值不可能在第 k 小的范围内，直接丢弃；
 * 如果当前节点值元素小于堆顶元素，说明当前节点值可能在第 k 小的范围内，先 poll 一个再 add 进去。
 * 树的遍历可以使用 DFS 或 BFS。
 */
class Solution6{
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            TreeNode node = deque.pollFirst();
            if (pq.size() < k){
                pq.add(node.val);
            }else if (pq.peek() > node.val){
                pq.poll();
                pq.add(node.val);
            }
            if (node.left != null) deque.addLast(node.left);
            if (node.right != null) deque.addLast(node.right);
        }
        return pq.peek();
    }
}
//时间复杂度：树的遍历时间复杂度为 O(n)；使用优先队列（堆）复杂度为O(nlogk)。整体复杂度为 O(nlogk)
//空间复杂度：空间多少取决于 d 和 q 使用的容量，q 最多不超过k 个元素，复杂度为O(k)，d 最多不超过二叉树的一层，复杂度为O(n)。整体复杂度为 O(n+k)

/**
 *中序遍历
 * 上述两种节点，都没有利用该树为二叉搜索树的特性。
 * 而我们知道，二叉搜索树的中序遍历是有序的，因此我们只需要对二叉搜索树执行中序遍历，并返回第 k 小的值即可。
 */
class Solution7{
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> d = new ArrayDeque<>();
        while (root != null || !d.isEmpty()) {
            while (root != null) {
                d.addLast(root);
                root = root.left;
            }
            root = d.pollLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
        return -1; // never
    }
}
