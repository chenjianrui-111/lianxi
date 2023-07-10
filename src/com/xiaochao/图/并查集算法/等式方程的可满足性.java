package com.xiaochao.图.并查集算法;

/**
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
 * 示例 1：
 * 输入：["a==b","b!=a"]
 * 输出：false
 * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 */
public class 等式方程的可满足性 {
       public boolean equationsPossible(String[] equations) {
        //26个英文字母
        UF1 uf =new UF1(26);
        for (String eq : equations){
            if (eq.charAt(1) == '='){
                char x = eq.charAt(0);
                char y =eq.charAt(3);
                uf.union(x-'a',y-'a');
            }
        }
        for (String eq : equations){
            if (eq.charAt(1) == '!'){
                char x = eq.charAt(0);
                char y =eq.charAt(3);
                // 如果相等关系成⽴，就是逻辑冲突
                if (uf.connected(x-'a',y-'a'))
                    return false;
            }
        }
        return true;
    }
}
class UF1{
    private int count ;
    private int[] parent;
    public UF1(int n){
        this.count = n;
        parent = new int[n];
        for (int i = 0; i <n ; i++) {
            parent[i] = i;
        }
    }

    //将节点p和节点q联通
    public void union(int p, int q) {
        int rootP =find(p);
        int rootQ =find(q);
        if (rootP == rootQ){
            return;
        }
        parent[rootP] = rootQ;
        count--;
    }
    int find(int x){
        if (parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }


    public boolean connected(int p, int q) {
        int rootP =find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
}
