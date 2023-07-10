package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

import java.util.*;

public class 后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    public void postOrder(TreeNode root, List<Integer> list){
        if (root == null) return;
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }
    /**
     * 迭代则有区别。因为后序遍历要求按照左、右、中的顺序，访问节点的内存空间，从右到中这里比较麻烦。
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            //将所有左节点入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //取出根节点最左侧的左节点
            root = stack.pop();
            //遍历最左节点的右子树，如果右子树不存在，或者上一个访问的节点是右子节点，就访问这个节点
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                //记录这个节点，证明这个节点左、右都遍历完成了。下次遍历就能知道，如果它是右子节点，不用访问右子树
                prev = root;
                //避免重复访问左子树
                root = null;
            } else {
                //如果右子树没有被访问，访问右子树
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}
/**
 *统一迭代法
 * 前面的前中后序迭代法，差异较大，其实可以写成较为统一的形式，这样在更换遍历顺序时，只需要调整三行代码的位置，像递归一样。
 */
class Solution5 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root != null) stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                stack.pop();
                if (node.right!=null) stack.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) stack.push(node.left);    // 添加左节点（空节点不入栈）
                stack.push(node);                          // 添加中节点
                stack.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。

            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                stack.pop();           // 将空节点弹出
                node = stack.peek();    // 重新取出栈中元素
                stack.pop();
                result.add(node.val); // 加入到结果集
            }
        }
        return result;
    }
}
