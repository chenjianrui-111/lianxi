package com.xiaochao.数组链表;

/**
 * 给定数组[2,1,3,4,5] 和范围(0,3,2) 在 0~3 范围中找 2 出现的次数
 */
public class 给定数组和范围查询k的次数 {

    //暴力
    public static class QueryBox1{
        private int [] arr;
        public QueryBox1(int[] array){
            arr = new int[array.length];
            for (int i = 0; i < array.length ; i++) {
                arr[i] = array[i];
            }
        }
        public int query(int L,int R,int V){
            int ans = 0;
            for (;L <= R;L++){
                if (arr[L] == V){
                    ans++;
                }
            }
            return ans;
        }
    }

}
