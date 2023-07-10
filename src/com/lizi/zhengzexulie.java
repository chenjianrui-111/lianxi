package com.lizi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 我们要让每个数字移动的步数最少，或者说让每个数字找距离下标[1,n]最近的位置填充。当时思路是有了，但是我一直没有排序，所以测试样例通过了，然后整体运行通不过，百思不得解。后来发现先排序，然后将所有数字按照[1,n]的坑位从小到大填。这样就能保证距离最小，整体思路是对的，还要保证数组整体有序。
 * 保证最后所有的数值取值在[1,n]，先排序，然后从左到右遍历，这样就能取得最小步数。
 */
public class zhengzexulie {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int res = 0;
        for(int j=1;j<=n;j++){
            res += Math.abs(arr[j-1] - j);
        }
        System.out.println(res);
    }
}
