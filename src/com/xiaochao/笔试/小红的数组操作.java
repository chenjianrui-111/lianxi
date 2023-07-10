package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 小红拿到了一个数组，他每次可以进行如下操作
 * 选择一个数，使其减去k
 * 小红希望k次操作后，该数组的最大值尽可能地小
 * 输入描述：
 * 第一行输入三个正整数n,k,x 代表数组的长度，操作次数以及每次操作减的数
 * 第二行输入n个正整数ai,代表小红拿到的数组。
 * 1<=n<=10^5
 * 1<=ai,k,x<=10^9
 * 输入
 * 5 3 5
 * 4 3 11 2 1
 * 输出
 * 3
 * 说明
 * 第一个数操作一次，第三个数操作2次，数组变成{-1，3，1，2，1}，最大值为3
 */
public class 小红的数组操作 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();
        int[] arr = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            arr[i] = sc.nextInt();
            list.add(arr[i]);
        }
        int max = minMax2(list, k, x);
        System.out.println(max);
    }
    public static int minMax (int[] arr, int k, int x) {
        // write code here
        PriorityQueue<Integer> q=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<arr.length;i++){
            q.add(arr[i]);
        }
        for(int i=0;i<k;i++){
            int n=q.poll();
            n-=x;
            q.add(n);
        }
        return q.peek();
    }
    public static int minMax2 (ArrayList<Integer> a, int k, int x) {
        // write code here
        // 初始化数据
        ArrayList<Integer> alist = new ArrayList();
        alist.add(0);
        alist.addAll(a);

        // 建堆
        for (int i=alist.size()/2; i>0; i--) {
            adjust(alist,i,alist.size());
        }

        // k次操作，每次操作完重新建堆
        for (int j=k; j>0; j--) {
            alist.set(1,alist.get(1)-x);
            adjust(alist,1,alist.size());
        }
        return alist.get(1);
    }

    public static void adjust(ArrayList<Integer> arrayList, int k, int len) {

        arrayList.set(0,arrayList.get(k)); // 用arrayList[0]暂存子树的根节点

        for (int i = 2*k; i < len; i*=2) {  // 根顶元素下坠
            if(i+1 < len && arrayList.get(i) < arrayList.get(i+1)) { //左右子元素比较，取最大的子元素
                i++;
            }
            if( arrayList.get(0) >= arrayList.get(i) ) { // 父元素更大，不做处理
                break;
            } else {
                arrayList.set(k,arrayList.get(i));  // 将子元素替换父元素
                k = i;
            }
        }
        arrayList.set(k,arrayList.get(0));  //将被筛选的结点的值放入最终位置
    }
}
/**
 * 题目给了k和x，那么这些数字最多减少k*x。我是先从大到小排序，找到一个位置，使得这个位置之前的数比它多出来总和大于kx
 * 加入这个数记为std，那么前面的数我都减去x的整数倍，让他刚好小于std
 * 但是我减的总次数可能会大于k，所以最后再暴力把这k个数加回去就行了
 */
/**
 *import math
 * n, k, x = [int(xx) for xx in input().split()]
 * a = [int(x) for x in input().split()]
 * a.sort(reverse=True)
 *
 * if n == 1:
 *     print(a[0] - x * k)
 * else:
 *     total = k * x
 *     dif = [0]
 *     for i in range(1, n):
 *         total -= (a[i-1] - a[i]) * (i)
 *         if total < 0:
 *             break
 *     if total > 0:
 *         std = a[-1] - total // n - 1
 *         b = []
 *     else:
 *         std = a[i]
 *         a = a[:i]
 *         b = a[i:]
 *     for j in range(len(a)):
 *         tmp = math.ceil((a[j] - std) / x)
 *         k -= tmp
 *         a[j] -= tmp * x
 *     a.sort()
 *     t = 0
 *     for i in range(-k):
 *         a[t] += x
 *         t = (t + 1) % len(a)
 *     print(max(a+b))
 */
