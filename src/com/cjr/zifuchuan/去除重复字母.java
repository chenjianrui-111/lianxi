package com.cjr.zifuchuan;

import java.util.ArrayDeque;
import java.util.Deque;

public class 去除重复字母 {
    public String removeDuplicateLetters(String s) {
        int len=s.length();
        char[] charArray=s.toCharArray();
        int[] lastIndex=new int[26];//使用小写字母26位，记录了我们在遍历过程中出现字符的最后一个下标
        for (int i = 0; i <len ; i++) {
            lastIndex[charArray[i]-'a']=i;
        }
        //声明一个栈
        Deque<Character> stack = new ArrayDeque<Character>();
        //记录某一个字母在栈中是否出现
        boolean [] visited=new boolean[26];
        for (int i = 0; i <len ; i++) {
            if (visited[charArray[i]-'a']){ //如果出现舍弃当前字符
                continue;
            }
            //当前字符在栈顶元素之前，且栈顶元素在后面还有
            while (!stack.isEmpty() &&stack.pollLast() >charArray[i] &&lastIndex[stack.peekLast()-'a']>i){
                Character top = stack.removeLast(); //移除栈顶元素
                visited[top-'a']=false;//表示该字符没有在栈中出现
            }
            stack.addFirst(charArray[i]);
            visited[charArray[i]-'a']=true;
        }
        StringBuilder sb=new StringBuilder();
        for (char c:stack){
            sb.append(c);
        }
        return sb.toString();
    }
}
