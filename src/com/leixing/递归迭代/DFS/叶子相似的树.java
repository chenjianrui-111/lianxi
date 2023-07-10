package 树的搜索;

import java.util.ArrayList;
import java.util.List;

/**
 *请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 */
public class 叶子相似的树 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> l1=new ArrayList<>(),l2=new ArrayList<>();
        dfs(root1,l1);
        dfs(root2,l2);
        if (l1.size() == l2.size()){
            for (int i=0;i<l1.size();i++){
                if (!l1.get(i).equals(l2.get(i))) return false;
            }
            return true;
        }
        return false;
    }
    void dfs(TreeNode root,List<Integer> list){
        if (root == null) return;
        if (root.left == null && root.right==null){
            list.add(root.val);
            return;
        }
        dfs(root.left,list);
        dfs(root.right,list);
    }
}
/**
 *时间复杂度：n 和 m 分别代表两棵树的节点数量。复杂度为 O(n + m)
 * 空间复杂度：n 和 m 分别代表两棵树的节点数量，当两棵树都只有一层的情况，
 * 所有的节点值都会被存储在 list 中。复杂度为 O(n + m)
 */