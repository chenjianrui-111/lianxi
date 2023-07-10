package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 小美的礼盒包装
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 *         小美开的西点屋举办一周年活动，她准备制作一批礼盒作为对消费者的回馈，每个礼盒中都有三枚西点屋的招牌点心。西点屋共有A和B两种招牌点心，为了让消费者都能品尝到两种点心，因此每个礼盒中都要包含至少一枚A点心和一枚B点心。现在小美的西点屋内共有x枚A点心和y枚B点心，请问小美最多可以制作多少个礼盒。
 * 输入描述
 *     输入第一行包含一个正整数T，表示数据组数。(1<=T<=10000)
 *     然后有T行，每行包含两个整数x和y，空格隔开，表示有x枚A点心和y枚B点心。(1<=x,y<=10^9)
 * 输出描述
 *      输出包含T行，每行一个整数，表示最多可以制作的礼盒数量。
 */
public class 小美的礼盒包装 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            for(int i=0;i<num;i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                int min = Math.min(a,b);
                int max = Math.max(a,b);
                if(max>=min*2){   //如果max>=2*min 由于每一份都至少需要一个min 所以答案就是min种（一个min，两个max）
                    System.out.println(min);
                }
                else{       //如果  min<=max<2*min 那么此时肯定凑不够 一个min配2个max
                    // 此时需要有min填补max的位置 答案从min开始减小 每次减小有两重效果
                    // 例如 44 85  min=44 max=85  min = 44 max =44+41 不够
                    // 当min取43时 min减少1 同时有多余的一个min和一个max可以补充 所以min-1的效果相当于补充了3个max
                    //min=43 max = 43+42+1
                    //本方***TLE 因此需要优化
                    //将此处的两种情况总结 可以发现答案要么是min 要么是 a+b//3
                    for(int k=min;k>=0;k--){
                        if(k<=(min-k)+(max-k)){
                            System.out.println(k);
                            break;
                        }
                    }
                }
            }
        }

//    Scanner sc = new Scanner(System.in);
//    int num = sc.nextInt();
//        for(int i=0;i<num;i++){
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//        int min = Math.min(a,b);
//        System.out.println(Math.min(min,(a+b)/3));
//    }
}
