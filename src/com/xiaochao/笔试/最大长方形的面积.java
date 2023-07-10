package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 给定一个无符号整数数组，每个元素表示一个点，数组元素的索引作为直角坐标系的x坐标，元素作为y坐标
 * 任意两个点：
 * x轴方向的距离作为长方形的长，y轴方向的最小值作为长方形的宽，找到两个点使长方形面积最大，输出面积
 * 例子[4,1,2,7] 代表四个点(0,4)(1,1)(2,2)(3,7),点(0,4)(1,1)表示长方形的长：长=(1-0),宽=min(4,1)
 * 面积 = 长 * 宽 = 1
 * 2 <=array.length <= 105
 * 0 <=array[i] <= 10000
 */
public class 最大长方形的面积 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s =sc.nextLine();
        s = s.replaceAll("\\[","");
        s = s.replace("]","");
        String[] split = s.split(",");
        int length = split.length;
        int[] arr = new int[length];
        for (int i = 0;i < length;i++){
            arr[i] = Integer.parseInt(split[i]);
        }
        System.out.println(getMaxAreas(arr));
    }
    public static int getMaxAreas(int[] arr){
        int res = -1;
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j){
                    //x 是长
                    int x = j - i;
                    //y 是宽
                    int y = Math.min(arr[i],arr[j]);
                    res = Math.max(res,Math.abs(x * y));
                }
            }
        }
        return res;
    }
}
//#include<bits/stdc++.h>
//        using namespace std;
//
//        #define ll long long
//
//        ll a[21342];
//
//        ll n,m,k;
//
//        int main(){
//        string s;
//        cin>>s;
//        int cnt=0;
//        // 按字符串分开数字
//        for(int i=0;s[i];i++){
//        int j=i;
//        if(s[j]>='0'&&s[j]<='9'){
//        while(s[j]>='0'&&s[j]<='9'){ // 一直走到不是数字的位置
//        j++;
//        }
//        j--;
//        int num=0;
//        int t=1;
//        //从后面以此*10把数计算出来
//        for(int k=j;k>=i;k--){
//        num=num+t*(s[k]-'0');
//        }
//        a[++cnt]=num;
//        }
//        i=j;
//
//        }
//        int ans=0;
//        //暴力求最大结果
//        for(int i=1;i<=cnt;i++){
//        for(int j=i+1;j<=cnt;j++){
//        if((j-i)*min(a[i],a[j])>ans)
//        ans=(j-i)*min(a[i],a[j]);
//        }
//        }
//        printf("%d\n",ans);
//
//        // j -i * min(a[i], a[j])
//
//        return 0;
//        }

/** 解法二 **/
//#include<iostream>
//#include<vector>
//#include<string>
//#include<algorithm>
//using namespace std;
//class Solution {
//    public:
//    int MaxArea(vector<int> &points){
//        int left = 0, right = points.size() - 1;
//        int ans = 0;
//        while (left<right)
//        {
//            ans = max(ans, (right - left)*min(points[left], points[right]));
//            if (points[left] <= points[right])
//                ++left;
//            else
//                --right;
//        }
//        return ans;
//    }
//};
//int main(){
//        //vector<int> points = { 4, 1, 2, 7 };
//        Solution s;
//        string nums;
//        while (getline(cin, nums)){
//        vector<int> points;
//        string t = "";
//        for (int i = 1; i < nums.size() - 1; ++i){
//        if (nums[i] == ','){
//        points.emplace_back(stoi(t));
//        t.clear();
//        }
//        else{
//        t += nums[i];
//        }
//        }
//        if (t.size()>0)
//        points.emplace_back(stoi(t));
//        cout << s.MaxArea(points) << endl;
//        getchar();
//        }
//        return 0;
//        }
