package com.lizi;

import java.util.Scanner;

class Solution {
    /* Write Code Here */
    public int TotalCost(int[][] cost) {

        return 0 ;
    }

    public static class Main {
        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            int res;

            int cost_rows = 0;
            int cost_cols = 0;
            cost_rows = in.nextInt();
            cost_cols = in.nextInt();

            int[][] cost = new int[cost_rows][cost_cols];
            for(int cost_i=0; cost_i<cost_rows; cost_i++) {
                for(int cost_j=0; cost_j<cost_cols; cost_j++) {
                    cost[cost_i][cost_j] = in.nextInt();
                }
            }

            if(in.hasNextLine()) {
                in.nextLine();
            }


            res = new Solution().TotalCost(cost);
            System.out.println(String.valueOf(res));

        }
    }
}
