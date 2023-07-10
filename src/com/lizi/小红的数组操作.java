package com.lizi;

import java.util.ArrayList;

/**
 *
 */
public class 小红的数组操作 {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         *
         * @param a int整型ArrayList
         * @param k int整型
         * @param x int整型
         * @return int整型
         */
        public int minMax (ArrayList<Integer> a, int k, int x) {
            // write code here
            for(int i=0;i<k;i++){
                int max=0;
                int index=0;
                for(int j=0;j<a.size();j++){
                    if(a.get(j)>max){
                        index=j;
                        max=a.get(j);
                    }
                }
                a.set(index,a.get(index)-x);
            }
            int max=0;
            for(int j=0;j<a.size();j++){
                if(a.get(j)>max){
                    max=a.get(j);
                }
            }
            return max;
        }
}
