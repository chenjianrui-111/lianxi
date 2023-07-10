package com.demo;

import java.util.Arrays;
import java.util.Scanner;

public class 直角三角形个数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[2 * n];
        if (sc.hasNextLine()) {
            for (int i = 0; i < 2 * n; i++) {
                nums[i] = sc.nextInt();
            }
        }

        Arrays.sort(nums);
        int length =nums.length;
        int res = 0;
        for (int i = 0; i < length; ++i) {
            for (int j = i + 1; j < length; ++j) {
                int left = j + 1, right = length - 1, k = j;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                res += k - j;
            }
        }
        System.out.println(res);;
    }

}

//        #include <bits/stdc++.h>
//        using namespace std;
//
//        void handler(){
//        int n;
//        cin >> n;
//        vector<pair<double,double>> points;
//        points.reserve(n);
//        for(int i = 0; i < n; ++i){
//        double posx,posy;
//        cin >> posx >> posy;
//        points.emplace_back(posx,posy);
//        }
//        int count = 0;
//        vector<vector<pair<double,double>>> visited;
//        for(int i = 0; i < n; ++i){
//        for(int j = i + 1; j < n; ++j){
//        for(int k = j + 1; k < n; ++k){
//        vector<double> lens;
//        lens.emplace_back(
//        (points[i].first-points[j].first) * (points[i].first-points[j].first)
//        +(points[i].second-points[j].second) * (points[i].second-points[j].second)
//        );
//        lens.emplace_back(
//        (points[i].first-points[k].first) * (points[i].first-points[k].first)
//        +(points[i].second-points[k].second) * (points[i].second-points[k].second)
//        );
//        lens.emplace_back(
//        (points[k].first-points[j].first) * (points[k].first-points[j].first)
//        +(points[k].second-points[j].second) * (points[k].second-points[j].second)
//        );
//        sort(lens.begin(),lens.end());
//        if(lens[2] == lens[1] + lens[0]){
//        ++count;
//        }
//        }
//        }
//        }
//        cout << count;
//        }
//
//        int main(){
//        handler();
//        }
