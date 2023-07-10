package com.cjr.shu;

public class numIslands {
    public int numIslands(char[][] grid) {
        //边界条件判断
        if (grid.length==0|| grid==null)
            return 0;
        //统计岛屿个数
        int count=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                //只有当前格子是1才算
                if (grid[i][j]=='1'){
                    count++;
                    //然后通过dfs把当前格子的上下左右4个位置为1的都要置为0，因为他们是连着的算为一个岛屿
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }
    public void dfs(char[][]grid,int i,int j){
        //边界条件判断
        if (i<0 ||j<0||i>grid.length||j>grid[0].length||grid[i][j]=='0'){
            return;
        }
        //把当前格子置为0,让后延它上下左右方向寻找
        grid[i][j]= '0';
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }
}
