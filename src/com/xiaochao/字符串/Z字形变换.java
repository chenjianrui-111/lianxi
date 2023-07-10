package com.xiaochao.字符串;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */
public class Z字形变换 {
    public String convert(String s, int numRows) {
        if (numRows < 2) return s; // 给定行数为 1 时结果与原字符串一样
        List<StringBuffer> rows = new ArrayList<>();
        for (int i = 0; i < numRows ; i++) {
            rows.add(new StringBuffer());
        }
        int i = 0 ,flag = -1;// i 行数标志; flag 往上走还是往下走的标志
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) flag = - flag;//在达到 Z 字形转折点时，执行反向
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuffer row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
