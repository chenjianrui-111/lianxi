package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 小美非常热衷于数据竞赛，数据竞赛是当下非常流行的一种比赛形式，在这种比赛中往往会给出一个训练集和一个测试集，由于测试集是没有标注的，因此大家为了线下评测模型的性能，通常会将训练集拆分成两个部分，即自建的训练集和测试集。
 *         现在给出某比赛的一个训练集，小美需要按照如下规则将其拆分为训练集和测试集。
 *         已知该训练集包含n个样本，每个样本有一个样本编号和一个类别编号。假设某一类别的样本共有m个，则将编号最小的m/2(向上取整)个样本作为训练集，将其他样本作为测试集。
 * 输入描述
 *     输入第一行包含两个正整数n和k，分别表示样本数量和类别数量。(1<=n<=10000,1<=k<=100)
 *     输入第二行包含n个正整数，第i个正整数j表示第i个样本的类别编号是j。
 * 输出描述
 *     输出包含两行，第一行是训练集样本编号，编号从小到大输出，中间用空格隔开。第二行是测试集的样本编号，编号从小到大输出，中间用空格隔开。
 * 样例输入
 * 10 3
 * 3 2 2 1 2 3 1 3 3 3
 * 样例输出
 * 1 2 3 4 6 8
 * 5 7 9 10
 */
public class 小美的数据拆分 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        int[] count = new int[k+1];
        for(int i=0;i<n;i++){
            count[nums[i]]++;
        }

        for(int i=0;i<=k;i++){
            if(count[i]%2==1){
                count[i]=(count[i]/2+1);
            }else
                count[i]=(count[i]/2);
        }
        List<Integer> res = new ArrayList<>();
        List<Integer> tr = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(count[nums[i]]>0){
                res.add(i+1);
                count[nums[i]]--;
            }else {
                tr.add(i+1);
            }
        }
        for (int i=0;i<res.size();i++){
            System.out.print(res.get(i));
            if(i!=res.size()-1)
                System.out.print(" ");
        }
        System.out.println();
        for (int i=0;i<tr.size();i++){
            System.out.print(tr.get(i));
            if(i!=tr.size()-1)
                System.out.print(" ");
        }
    }
}
