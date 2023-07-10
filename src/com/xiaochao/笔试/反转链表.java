package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 反转链表 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(",");
        String s = split.toString();
        String substring = s.substring(1, s.length() - 1);
        Stack<Character> stack = new Stack<Character>();
        char[] chars = substring.toCharArray();
        int n =chars.length;
        for (char c : chars) {
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (!stack.isEmpty()){
            sb.append(stack.pop());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        String toString = sb.toString();
        int length = toString.length();
        for (int i = 0; i < length ; i++) {
            System.out.print(toString.charAt(i));
        }
    }
}
