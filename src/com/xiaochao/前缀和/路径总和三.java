package com.xiaochao.前缀和;

/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * 提示:
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109 
 * -1000 <= targetSum <= 1000 
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 树的遍历 + DFS
 * 一个朴素的做法是搜索以每个节点为根的（往下的）所有路径，并对路径总和为 targetSum
 * 的路径进行累加统计。
 * 使用 dfs1 来搜索所有节点，复杂度为 O(n)；在 dfs1 中对于每个当前节点，使用 dfs2 搜
 * 索以其为根的所有（往下的）路径，同时累加路径总和为 targetSum 的所有路径，复杂度为 O(n)。
 * 整体复杂度为 O(n2)，数据范围为 103，可以过。
 */
public class 路径总和三 {
    int ans ,t;
    public int pathSum(TreeNode root, int targetSum) {
        t=targetSum;
        dfs1(root);
        return ans;
    }
    void dfs1(TreeNode root){
        if (root == null) return;
        dfs2(root,root.val);
        dfs1(root.left);
        dfs1(root.right);
    }
    void dfs2(TreeNode root,int val){
        if (val == t) ans++;
        if (root.left != null) dfs2(root.left,val + root.left.val);
        if (root.right != null) dfs2(root.right,val + root.right.val);
    }
}
//• 时间复杂度：O(n2) • 空间复杂度：忽略递归带来的额外空间开销，复杂度为 O(1)

/**
 * 树的遍历 + 前缀和
 * 在「解法一」中，我们统计的是以每个节点为根的（往下的）所有路径，也就是说统计的是以每
 * 个节点为「路径开头」的所有合法路径。
 * 本题的一个优化切入点为「路径只能往下」，因此如果我们转换一下，统计以每个节点为「路径
 * 结尾」的合法数量的话，配合原本就是「从上往下」进行的数的遍历（最完整的路径必然是从原
 * 始根节点到当前节点的唯一路径），相当于只需要在完整路径中找到有多少个节点到当前节点的
 * 路径总和为 targetSum。
 * 于是这个树上问题彻底转换一维问题：求解从原始起点（根节点）到当前节点 b 的路径中，有
 * 多少节点 a 满足 sum[a...b] = targetSum，由于从原始起点（根节点）到当前节点的路径
 * 唯一，因此这其实是一个「一维前缀和」问题。
 * 具体的，我们可以在进行树的遍历时，记录下从原始根节点 root 到当前节点 cur 路径中，从
 * root 到任意中间节点 x 的路径总和，配合哈希表，快速找到满足以 cur 为「路径结尾」的、
 * 使得路径总和为 targetSum 的目标「路径起点」有多少个。
 * 一些细节：由于我们只能统计往下的路径，但是树的遍历会同时搜索两个方向的子树。因
 * 此我们应当在搜索完以某个节点为根的左右子树之后，应当回溯地将路径总和从哈希表中
 * 删除，防止统计到跨越两个方向的路径。
 */
class Solution8{

    Map<Integer,Integer> map = new HashMap<>();
    int ans ;int  t;
    public int pathSum(TreeNode root, int _t) {
        if (root == null) return 0;
        t = _t;
        //这是因为任何节点本身也可以形成一个路径（长度为1的路径）。如果某个节点的值就为target，
        // 那么它本身就是一个解。前缀和0正好可以与它形成这个解。对任何节点而言，本身就是解最多只能有一个，所以一开始put(0, 1)。相当于给所有节点一个可单独形成合法路径的机会。
        map.put(0,1);
        dfs(root,root.val);
        return ans;
    }
    void dfs(TreeNode root,int val){
        if (root == null) return;
        if (map.containsKey(val - t)) ans += map.get(val - t);// #1 累计满足要求的前缀和数量
        map.put(val,map.getOrDefault(val,0) + 1);// #2 先累计再put（先#1，再#2）
        if (root.left != null) dfs(root.left,val + root.left.val);
        if (root.right != null) dfs(root.right,val + root.right.val);
        map.put(val,map.getOrDefault(val,0) - 1);// 路径退缩，去掉不再在路径上的当前结点的前缀和。必存在，无需使用getOrDefault。
    }
}
//• 时间复杂度：O(n) • 空间复杂度：O(n)
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
 }
}
class Solution9 {
    // 记录前缀和
    // 定义：从二叉树的根节点开始，路径和为 pathSum 的路径有 preSumCount.get(pathSum) 个
    HashMap<Integer, Integer> preSumCount = new HashMap<>();

    int pathSum, targetSum;
    int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        this.pathSum = 0;

        this.targetSum = targetSum;
        this.preSumCount.put(0, 1);
        traverse(root);
        return res;
    }

    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序遍历位置
        pathSum += root.val;
        // 从二叉树的根节点开始，路径和为 pathSum - targetSum 的路径条数
        // 就是路径和为 targetSum 的路径条数
        res += preSumCount.getOrDefault(pathSum - targetSum, 0);
        // 记录从二叉树的根节点开始，路径和为 pathSum 的路径条数
        preSumCount.put(pathSum, preSumCount.getOrDefault(pathSum, 0) + 1);

        traverse(root.left);
        traverse(root.right);

        // 后序遍历位置
        preSumCount.put(pathSum, preSumCount.get(pathSum) - 1);
        pathSum -= root.val;
    }
}
