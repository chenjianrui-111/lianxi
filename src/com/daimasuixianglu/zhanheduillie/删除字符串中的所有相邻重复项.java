package com.daimasuixianglu.zhanheduillie;

import java.util.Deque;
import java.util.LinkedList;

/**
 *给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 示例：
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 */
public class 删除字符串中的所有相邻重复项 {
    public String removeDuplicates(String s) {
        // 将 sb 当做栈
       StringBuilder sb=new StringBuilder();
        // top为 sb 的长度
       int top=-1;
        for (int i = 0; i <s.length() ; i++) {
            char ch=s.charAt(i);
            // 当 top > 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top>=0 &&sb.charAt(top)==ch){
                sb.deleteCharAt(top);
                top--;
                // 否则，将该字符 入栈，同时top++
            }else {
                sb.append(ch);
                top++;
            }
        }
        return sb.toString();
    }
}
