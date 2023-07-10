package com.leixing.递归迭代.BFS;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 层序遍历
 * 根据节点值的数据范围 -1000 <= Node.val <= 1000（我是在 297. 二叉树的序列化与反序列化 看的，你也可以不使用数字，使用某个特殊字符进行表示，只要能在反序列时有所区分即可），我们可以建立占位节点 emptyNode 用来代指空节点（emptyNode.val = INF）。
 * 序列化：先特判掉空树的情况，之后就是常规的层序遍历逻辑：
 * 起始时，将 root 节点入队；
 * 从队列中取出节点，检查节点是否有左/右节点：
 * 如果有的话，将值追加序列化字符中（注意使用分隔符），并将节点入队；
 * 如果没有，检查当前节点是否为 emptyNode 节点，如果不是 emptyNode 说明是常规的叶子节点，需要将其对应的空节点进行存储，即将 emptyNode 入队；
 * 循环流程 2，直到整个队列为空。
 * 反序列：同理，怎么「序列化」就怎么进行「反序列」即可：
 * 起始时，构造出 root 并入队；
 * 每次从队列取出元素时，同时从序列化字符中截取两个值（对应左右节点），检查是否为 INF，若不为 INF 则构建对应节点；
 * 循环流程 2，直到整个序列化字符串被处理完（注意跳过最后一位分隔符）。
 */
public class 二叉树的序列化与反序列化 {
    int INF=-2000;
    TreeNode emptyNode=new TreeNode(INF);
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb=new StringBuilder();
        Deque<TreeNode> d=new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()){
            TreeNode poll=d.pollFirst();
            sb.append(poll.val+"_");
            if (!poll.equals(emptyNode)){
                d.addLast(poll.left !=null ? poll.left : emptyNode);
                d.addLast(poll.right !=null ? poll.right :emptyNode);
            }
        }
        return sb.toString();
    }


    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        String [] ss=data.split("_");
        int n=ss.length;
        TreeNode root=new TreeNode(Integer.parseInt(ss[0]));
        Deque<TreeNode> d=new ArrayDeque<>();
        d.addLast(root);
        for (int i=1;i< n - 1;i+=2){
            TreeNode poll=d.pollFirst();
            int a=Integer.parseInt(ss[i]),b=Integer.parseInt(ss[i + 1]);
            if (a != INF){
                poll.left=new TreeNode(a);
                d.addLast(poll.left);
            }
            if (b != INF){
                poll.right=new TreeNode(b);
                d.addLast(poll.right);
            }
        }
        return root;
    }
}
//时间复杂度：O(n)
//空间复杂度：O(n)
class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
 }
