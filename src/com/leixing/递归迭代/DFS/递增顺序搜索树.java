

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * 基本思路
 * 由于给定的树是一棵「二叉搜索树」，因此只要对其进行「中序遍历」即可得到有序列表，
 * 再根据有序列表构建答案即可。
 * 而二叉搜索树的「中序遍历」有「迭代」和「递归」两种形式
 */
public class 递增顺序搜索树 {
    List<TreeNode> list=new ArrayList<>();
    public TreeNode increasingBST(TreeNode root) {
        Deque<TreeNode> d=new ArrayDeque<>();
        while (root !=null || !d.isEmpty()){
            while (root !=null){
                d.add(root);
                root=root.left;
            }
            root=d.pollLast();
            list.add(root);
            root=root.right;
        }
        TreeNode dummy=new TreeNode(-1);
        TreeNode cur=dummy;
        for (TreeNode node: list){
            cur.right=node;
            node.left=null;
            cur=node;
        }
        return dummy.right;
    }
}
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
/**
 *时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
