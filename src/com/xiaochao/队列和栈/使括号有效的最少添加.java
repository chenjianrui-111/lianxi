package com.xiaochao.队列和栈;

import java.util.ArrayDeque;
import java.util.Deque;

public class 使括号有效的最少添加 {
    public int minAddToMakeValid(String s) {
        Deque<Character> stack=new ArrayDeque<>();
        for(char c:s.toCharArray()){
            if(c=='('){stack.addLast(c);}
            else{
                if(stack.size()>0&&stack.getLast()=='('){stack.removeLast();}
                else{stack.addLast(c);}
            }
        }
        return stack.size();
    }
}
class Solution {
    public int minAddToMakeValid(String s) {
        int l=0,r=0;
        char c[]=s.toCharArray();
        for(int i=0;i<c.length;i++){
            if(c[i]=='('){r++;}
            else{r--;}
            if(r<0){
                l++;
                r=0;
            }
        }
        return l+r;
    }
}
