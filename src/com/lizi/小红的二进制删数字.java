package com.lizi;

public class 小红的二进制删数字 {
    public int minCnt (String s) {
        // write code here
        if(s.startsWith("1")){
            boolean flag=true;
            for(int i=1;i<s.length();i++){
                if(s.charAt(i) !='0'){
                    flag=false;
                    break;
                }
            }
            if(flag){
                return 0;
            }
        }
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1'){
                count++;
            }
        }
        return count-1;
    }
}
