package com.daimasuixianglu.shuzu;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 而求解本题依然是要坚持循环不变量原则。
 * 模拟顺时针画矩阵的过程:
 * 填充上行从左到右
 * 填充右列从上到下
 * 填充下行从右到左
 * 填充左列从下到上
 * 由外向内一圈一圈这么画下去。
 * 可以发现这里的边界条件非常多，在一个循环中，如此多的边界条件，如果不按照固定规则来遍历，那就是一进循环深似海，从此offer是路人。
 * 这里一圈下来，我们要画每四条边，这四条边怎么画，每画一条边都要坚持一致的左闭右开，或者左开又闭的原则，这样这一圈才能按照统一的规则画下来。
 */
public class 螺旋矩阵二 {
    public int[][] generateMatrix(int n) {
        int[][] res=new int[n][n];
        //循环次数,例如n为奇数3，那么loop = 1 只是循环一圈，矩阵中间的值需要单独处理
        int loop=n/2;

        //定义每次循环起始位置
        int startX=0;
        int startY=0;

        //定义偏移量, 每一圈循环，需要控制每一条边遍历的长度
        int offset=1;

        //定义填充数字,用来给矩阵中每一个空格赋值
        int count=1;

        //定义中间位置，例如：n为3， 中间的位置就是(1，1)，n为5，中间位置为(2, 2)
        int mid=n/2;
        while (loop > 0){
            int i=startX;
            int j=startY;
            //模拟上侧从左到右
            for (;j<startY+n-offset;++j){
                res[startX][j]=count++;
            }
            // 模拟右侧从上到下
            for (; i<startX + n -offset; ++i) {
                res[i][j] = count++;
            }
            // 模拟下侧从右到左
            for (; j > startY; j--) {
                res[i][j] = count++;
            }

            // 模拟左侧从下到上
            for (; i > startX; i--) {
                res[i][j] = count++;
            }
            loop--;
            // 第二圈开始的时候，起始位置要各自加1， 例如：第一圈起始位置是(0, 0)，第二圈起始位置是(1, 1)
            startX+=1;
            startY+=1;
            // offset 控制每一圈里每一条边遍历的长度
            offset+=2;
        }
        // 如果n为奇数的话，需要单独给矩阵最中间的位置赋值
        if (n%2==1){
            res[mid][mid]=count;
        }
        return res;
    }
}
