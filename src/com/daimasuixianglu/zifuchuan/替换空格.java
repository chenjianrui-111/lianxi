package com.daimasuixianglu.zifuchuan;

/**
 *请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 * 限制：
 * 0 <= s 的长度 <= 10000
 * 如果想把这道题目做到极致，就不要只用额外的辅助空间了！
 * 首先扩充数组到每个空格替换成"%20"之后的大小。
 * 然后从后向前替换空格，也就是双指针法，过程如下：
 * i指向新长度的末尾，j指向旧长度的末尾。
 * 从前向后填充就是O(n^2)的算法了，因为每次添加元素都要将添加元素之后的所有元素向后移动。
 * 其实很多数组填充类的问题，都可以先预先给数组扩容带填充后的大小，然后在从后向前进行操作。
 * 这么做有两个好处：
 * 1.不用申请新数组。
 * 2.从后向前填充元素，避免了从前先后填充元素要来的 每次添加元素都要将添加元素之后的所有元素向后移动。
 */
public class 替换空格 {
        public String replaceSpace(String s) {
            StringBuilder res = new StringBuilder();
            for(Character c : s.toCharArray())
            {
                if(c == ' ') res.append("%20");
                else res.append(c);
            }
            return res.toString();
        }
}
