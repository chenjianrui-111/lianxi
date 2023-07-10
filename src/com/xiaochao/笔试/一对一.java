package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 某公司正在组织新员工团建，其中一项活动是两两配对进行石头剪刀布。
 * 因为新员工来自不同的学校和专业，他们许多人之间并不熟悉，但他们之间的朋友关系形成了一棵树。即若将朋友关系描述为一张无向图，
 * 则这张无向图中任意两点之间有且仅有一条路径。为了避免尴尬，组织者希望互为朋友的配对数量尽可能多。现在他希望你求出互为朋友的配对数量最多能有多少。
 * 输入描述
 * 第一行有一个偶数n(2<=n<=1000)，代表有n个新员工。
 * 第二行有n-1个空格隔开的数，第i个数ai代表编号为i+1的新员工与编号为ai的员工互为朋友。
 * 输入保证新员工之间的朋友关系形成了一棵树
 * 输出描述
 * 输出在所有可能的配对方案中，互为朋友的配对数量最多是多少。
 * 样例输入
 * 6
 * 1 2 2 3 3
 * 样例输出
 * 2
 * 提示
 * 如样例中，一共有6个新员工，朋友关系有以下五个(1,2),(2,3),(2,4),(3,5),(3,6)。
 * 可以证明无论如何匹配这6个人， 最多只能有两对是互为朋友的，因此输出2。
 */
//map 用于记忆，避免重复计算；
//dfs 方法用于仿照树进行深搜，其中pre用于记录当前节点的父节点，canuse 用来表示当前节点是否可用。
//
//如果当前节点不可用，则子节点均可用；
//如果当前节点均可用，则（1）子节点均可用（2）当前节点和其中一个子节点建立连接，该子节点不可用，其余子节点可用。
public class 一对一 {
    static Map<Integer, Integer> map;
    static ArrayList<Integer>[] edges;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new HashMap<>();
        edges = new ArrayList[n+3];
        for(int i = 0; i < n+3; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n-1; i++) {
            int tmp = sc.nextInt();
            edges[tmp].add(i+2);
            edges[i+2].add(tmp);
        }
        System.out.println(dfs(1, 1, -1));
    }

    static int dfs(int canuse, int idx, int pre) {
        int arr = idx * canuse;
        if (map.containsKey(arr)) {
            return map.get(arr);
        }
        int cnt = 0;
        for (int next : edges[idx]) {
            if (next != pre) {
                cnt += dfs(1, next, idx);
            }
        }
        int ret = cnt;
        if (canuse == 1) {
            for(int next: edges[idx]) {
                if(next != pre) {
                    int f = dfs(-1, next, idx);
                    int t = dfs(1, next, idx);
                    ret = Math.max(ret, cnt - t + f + 1);
                }
            }
        }
        map.put(arr, ret);
        return ret;
    }
}
//#include<bits/stdc++.h>
//        #include<memory>
//using namespace std;
//        #define ll long long
//        const ll maxx=1e6+7;
//
//        #define pb(x)push_back(x)
//        ll n,m,x,dp[maxx][2];
//
//        vector<ll> v[maxx];
//
//        void dfs(ll u,ll fa)
//        {
//        for(ll e:v[u])
//        {
//        if(e==fa)
//        continue;
//        dfs(e,u);
//        dp[u][0]+=max(dp[e][1],dp[e][0]);
//        }
//        for(ll e:v[u])
//        {
//        if(e==fa)continue;
//        dp[u][1]=max(dp[u][1],dp[u][0]-max(dp[e][1],dp[e][0])+dp[e][0]+1);
//        }
//        }
//
//        void solve(){
//        cin>>n;
//        for(int i=2;i<=n;i++)
//        {
//        cin>>x;
//        v[i].pb(x);
//        v[x].pb(i);
//        }
//        dfs(1,1);
//
//        ll ans=0;
//        for(int i=1;i<=n;i++)
//        ans=max(ans,max(dp[i][0],dp[i][1]));
//
//        printf("%lld\n",ans);
//        }
//
//        int main()
//        {
//        solve();
//        return 0;
//        }
