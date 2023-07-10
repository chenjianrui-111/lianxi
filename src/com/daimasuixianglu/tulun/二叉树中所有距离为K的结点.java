package com.daimasuixianglu.tulun;

import com.cjr.shu.TreeNode;

import java.util.*;

/**
 *输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 * 思路
 * 显然，如果题目是以图的形式给出的话，我们可以很容易通过「BFS / 迭代加深」找到距离为  的节点集。
 * 而树是一类特殊的图，我们可以通过将二叉树转换为图的形式，再进行「BFS / 迭代加深」。
 * 由于二叉树每个点最多有  个子节点，点和边的数量接近，属于稀疏图，因此我们可以使用「邻接表」的形式进行存储。
 * 建图方式为：对于二叉树中相互连通的节点（root 与 root.left、root 和 root.right），建立一条无向边。
 * 建图需要遍历整棵树，使用 DFS 或者 BFS 均可。
 * 由于所有边的权重均为 1 ，我们可以使用 「BFS / 迭代加深」 找到从目标节点 target 出发，与目标节点距离为 k 的节点，然后将其添加到答案中。
 */
public class 二叉树中所有距离为K的结点 {
    int N=1010,M=N*2;
    int[] he=new int[N],e=new int[M],ne=new int[M];
    int idx;
    void add(int a,int b){
        e[idx]=b;
        ne[idx]=he[a];
        he[a]=idx++;
    }
    boolean[] visited=new boolean[N];
    public List<Integer> distanceK(TreeNode root, TreeNode t, int k) {
        List<Integer> ans=new ArrayList<>();
        Arrays.fill(he,-1);
        dfs(root);
        Deque<Integer> d=new ArrayDeque<>();
        d.addLast(t.val);
        visited[t.val]=true;
        while (!d.isEmpty() && k >=0){
            int size=d.size();
            while (size-- > 0){
                int poll=d.pollFirst();
                if (k == 0){
                    ans.add(poll);
                    continue;
                }
                for (int i = he[poll]; i !=-1 ; i=ne[i]) {
                    int j = e[i];
                    if (!visited[j]) {
                        d.addLast(j);
                        visited[j] = true;
                    }
                }
            }
            k--;
        }
        return ans;
    }
    void dfs(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            add(root.val, root.left.val);
            add(root.left.val, root.val);
            dfs(root.left);
        }
        if (root.right != null) {
            add(root.val, root.right.val);
            add(root.right.val, root.val);
            dfs(root.right);
        }
    }
}
//空间复杂度：O(n)
class Solution {
    HashMap<Integer, TreeNode> parent=new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //遍历所有节点，记录每个结点的父节点
        traverse(root,null);
        //开始从target节点释放bfs算法，找到距离为k的节点
        Queue<TreeNode> q=new LinkedList<>();
        HashSet<Integer> visited=new HashSet<>();
        q.offer(target);
        visited.add(target.val);
        //记录target的距离
        int dist=0;
        List<Integer> res=new LinkedList<>();
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                TreeNode cur=q.poll();
                if(dist==k){
                    res.add(cur.val);
                }
                //向父节点，左右子节点扩散
                TreeNode parentNode=parent.get(cur.val);
                if(parentNode !=null && !visited.contains(parentNode.val)){
                    visited.add(parentNode.val);
                    q.offer(parentNode);
                }
                if(cur.left!=null &&!visited.contains(cur.left.val)){
                    visited.add(cur.left.val);
                    q.offer(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right.val)) {
                    visited.add(cur.right.val);
                    q.offer(cur.right);
                }
            }
            //向外扩展一圈
            dist++;
        }
        return res;
    }
    public void traverse(TreeNode root, TreeNode parentNode){
        if (root == null) {
            return;
        }
        parent.put(root.val, parentNode);
        // 二叉树递归框架
        traverse(root.left, root);
        traverse(root.right, root);
    }
}
