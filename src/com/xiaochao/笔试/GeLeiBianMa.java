package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，我们可以通过递归的方式构造出n位的格雷编码序列，其中n位的格雷编码序列是由n-1位格雷编码序列演化而来的。
 * 我们可以按照以下步骤构造n位的格雷编码序列：
 * 计算n-1位的格雷编码序列
 * 将n-1位的格雷编码序列倒序排列，然后在每个元素前面加上前缀1
 * 将n-1位的格雷编码序列与倒序排列后的格雷编码序列合并起来，得到n位的格雷编码序列
 * 最后将n位的格雷编码序列转换成十进制数，即可得到最终结果
 * 时间复杂度：O(2^n)，其中n为格雷编码的位数
 */
public class GeLeiBianMa {

    static  class B{};
    public static List<Integer> grayCode(int n) {
        if (n == 0) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }
        List<Integer> res = grayCode(n - 1);
        int addNum = 1 << (n - 1);
        int size = res.size();
        for (int i = size - 1; i >= 0; i--) {
            res.add(addNum + res.get(i));
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> res = grayCode(n);
        System.out.println(res.toString());
    }
}
