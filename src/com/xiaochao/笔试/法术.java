package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小明是一名魔法师，他会n种法术，其中第i种法术的威力为ai，他经常通过双手各自释放一种法术来提升威力，
 * 能得到的威力值为双手各自释放的法术的威力的乘积，但是他还不够强大，
 * ，即不能双手释放同一种法术。这天他接到了一个任务，需要释放威力值至少为K才能完成，他想请你帮他算一算，
 * 在两只手都释放法术的情况下，共有多少方案能达到威力值K。每种方案可记作（u，v），u≠v，其威力值为au * av，（u，v）和（v，u）会被视为不同的方案。
 * 输入描述
 * 第一行两个正整数n和K，表示法术数量和威力值需求。
 * 第二行为n个正整数a1, a2,...... an，其中ai表示第i个法术的威力为ai。
 * 输出描述
 * 输出威力值至少为K的方案数。
 * 样例输入
 * 3 3
 * 3 2 1
 * 样例输出
 * 4
 * 提示
 * 1 ≤ n ≤30000, 1 ≤ K ≤ 1016, 1 ≤ ai ≤109。
 */
public class 法术 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        long arr[] = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextLong();
        }
        Arrays.sort(arr);
        long ret = 0;
        for(int i = 0; i < n-1; i++){
            long value = arr[i];
            int left = i+1, right = n-1;
            while(left < right) {
                int mid = (left+right)/2;
                if(value * arr[mid] >= k) {
                    right = mid;
                }else{
                    left = mid+1;
                }
            }
            if(value * arr[left] >= k){
                ret += n - left;
            }
        }
        System.out.println(ret*2);
    }
}
//#include <bits/stdc++.h>
//        using namespace std;
//        #define ll long long
//        ll n, k;
//        const ll maxx = 1e6 + 7;
//
//        const ll mod = 1e9 + 7;
//        ll a[maxx];
//        int main() {
//        cin >> n >> k;
//        for (int i = 1; i <= n; i++) cin >> a[i];
//        sort(a + 1, a + 1 + n);
//        //n = unique(a + 1, a + 1 + n) - (a + 1);
//        ll ans = 0;
//        for (int i = 1; i <= n; i++) {
//        //printf("%d ", a[i]);
//        //if (a[i] == a[i - 1]) continue;
//        int l = i + 1, r = n;
//        ll pos = 1e9;
//        while(l <= r) {
//        ll mid = (l + r) >> 1;
//        if (a[i] * a[mid] >= k) {
//        pos = min(pos, mid);
//        r = mid - 1;
//        }
//        else l = mid + 1;
//        }
//        if (pos != 1e9) {
//        ans += (n - pos + 1);
//        }
//        }
//        printf("%lld\n", ans * 2);
//        return 0;
//        }
