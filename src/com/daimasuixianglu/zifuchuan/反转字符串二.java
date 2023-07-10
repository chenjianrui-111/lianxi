package com.daimasuixianglu.zifuchuan;

/**
 *给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 *
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 示例 1：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由小写英文组成
 * 1 <= k <= 104
 */
public class 反转字符串二 {
    public String reverseStr(String s, int k) {
        StringBuffer sb=new StringBuffer();
        int length=s.length();
        int start=0;
        while (start<length){
            // 找到k处和2k处
            StringBuffer temp = new StringBuffer();
            // 与length进行判断，如果大于length了，那就将其置为length
            int firstK=(start+k>length)?length:start+k;
            int secondK=(start+(2*k)>length)?length:start+(2*k);

            //无论start所处位置，至少会反转一次
            temp.append(s.substring(start, firstK));
            sb.append(temp.reverse());

            // 如果firstK到secondK之间有元素，这些元素直接放入res里即可。
            if (firstK < secondK) { //此时剩余长度一定大于k。
                sb.append(s.substring(firstK, secondK));
            }
            start += (2 * k);
        }
        return sb.toString();
    }
}
