package com.xiaochao.笔试;

import java.util.*;

/**
 * 小明正在刷栅栏。为了使得栅栏在经过风吹雨打后依然不掉色，需要用两种不同的油漆刷栅栏。
 * 栅栏被按顺序编号为1到1000000000。每一段栅栏需要至少刷 p 遍的1号油漆和 q 遍的2号油漆后才能保证长时间不掉色。
 * 每次刷漆都会使用某种类型的油漆，并将编号属于某个区间内的栅栏都刷上这种油漆。
 * 现在给出每次刷漆的栅栏编号范围和油漆种类，请你求出有多少段栅栏能够长时间不掉色。
 * 输入描述
 * 第一行有三个正整数n,p,q(1<=n<=100000,1<=p,q<=n)，代表刷漆的次数，以及两个参数 p 和 q。
 * 第二到四行给出了一个大小为3*n的矩阵，第 i 列的三个数从上到下记为l,r,t(1<=l,r<=1000000000,1<=t<=2)，
 * 代表第i次刷漆将编号在 l 到 r 之间的栅栏刷了一遍 t号油漆。
 * 输出描述
 * 输出一个正整数，代表有多少栅栏可以长时间不掉色。
 * 样例输入
 * 5 2 2
 * 1 1 2 3 2
 * 3 5 4 5 4
 * 1 2 1 1 2
 * 样例输出
 * 3
 */
public class 刷栅栏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt(), q = sc.nextInt();
        int arr[][] = new int[n][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < n; j++){
                arr[j][i] = sc.nextInt();
            }
        }
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(arr[i][2] == 1){
                m1.put(arr[i][0], m1.getOrDefault(arr[i][0], 0) + 1);
                m1.put(arr[i][1]+1, m1.getOrDefault(arr[i][1]+1, 0) - 1);
            }else{
                m2.put(arr[i][0], m2.getOrDefault(arr[i][0], 0) + 1);
                m2.put(arr[i][1]+1, m2.getOrDefault(arr[i][1]+1, 0) - 1);
            }
        }

        List<int[]> l1 = getOk(m1, p);
        List<int[]> l2 = getOk(m2, q);
        System.out.println(func(l1, l2));
    }


    public static int func(List<int[]> l1, List<int[]> l2){
        if(l1.isEmpty() || l2.isEmpty()) return 0;
        // 查看两个区间重叠的个数
        int i = 0, j = 0;
        int a = l1.size(), b = l2.size();
        int cnt = 0;
        while(i < a && j < b){
            int arr1[] = l1.get(i);
            int arr2[] = l2.get(j);
            cnt += Math.max(Math.min(arr1[1], arr2[1]) - Math.max(arr1[0], arr2[0]) + 1, 0);
            if(arr1[1] >= arr2[1]){
                j++;
            }else{
                i++;
            }
        }
        return cnt;
    }

    public static List<int[]> getOk(Map<Integer, Integer> map, int p){
        List<Integer> keys = new ArrayList<>(map.keySet());
        List<int[]> ret = new ArrayList<>();
        if(keys.isEmpty()) return ret;

        keys.sort((a, b) -> a - b);

        int pre = keys.get(0);
        int cnt = map.get(pre);
        int len = 0;
        for(int i = 1; i < keys.size(); i++){
            int idx = keys.get(i);
            if(cnt >= p){
                len = idx - pre;
            }else{
                if(len > 0){
                    ret.add(new int[]{pre, pre + len-1});
                }
                pre = idx;
                len = 0;
            }
            cnt += map.get(idx);
        }
        if(len > 0){
            ret.add(new int[]{pre, pre + len-1});
        }

        return ret;
    }
}
//#include <bits/stdc++.h>
//        #include <memory.h>
//        #include <vector>
//#include <algorithm>
//using namespace std;
//        #define ll long long
//        const ll maxx = 1000009;
//        const ll mod = 1000000007;
//        const ll inf = mod * mod;
//        #define pb(x) push_back(x)
//        #define rep(i, j, n) for (ll i = j; i <= n; i++)
//
//        ll n, m, p;
//        int a[maxx];
//        int p1[maxx * 10], p2[maxx * 10];
//        struct node {
//        ll l, r;
//        ll id;
//        }A[maxx];
//        int cmp(node a, node b) {
//        if (a.l == b.l) return a.r < b.r;
//        return a.l < b.l;
//        }
//        int main() {
//
//        cin >> n;
//        ll x, y;
//        cin >> x >> y;
//        ll nowx = 0, nowy = 0;
//        for (int i = 1 ; i <= n; i++)
//        cin >> A[i].l;
//        for (int i = 1 ; i <= n; i++)
//        cin >> A[i].r;
//        for (int i = 1 ; i <= n; i++)
//        cin >> A[i].id;
//        sort(A + 1, A + 1 + n, cmp);
//
//        for (int i = 1; i <= n; i++) {
//        if (A[i].id == 1) {
//        p1[A[i].l] += 1;
//        p1[A[i].r + 1] -= 1;
//        }
//        else {
//        p2[A[i].l] += 1;
//        p2[A[i].r + 1] -= 1;
//        }
//        }
//        ll ans = 0;
//        for (int i = 1; i < maxx * 10; i++) {
//        p1[i] += p1[i - 1];
//        p2[i] += p2[i - 1];
//        if (p1[i] >= x && p2[i] >= y) ans++;
//        }
//        printf("%lld\n", ans);
//        return 0;
//        }
