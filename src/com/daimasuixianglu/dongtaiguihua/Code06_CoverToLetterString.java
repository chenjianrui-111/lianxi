package com.daimasuixianglu.dongtaiguihua;

public class Code06_CoverToLetterString {
    public static int number(String str){
        if (str == null || str.length()== 0){
            return 0;
        }
        return process(str.toCharArray(),0);
    }

    //i之前的位置如何转化已经做过决定了
    //str[i...]有多少种转化的结果
    public static int process(char[] str,int i){
        if (i == str.length){// base case
            return 1;
        }
        //i没有到终止位置
        if (str[i] == '0'){
            return 0;
        }

        if (str[i] == '1'){
            int res=process(str,i+1);//i自己做决定，后续有多少种办法
            if (i+1 < str.length){
                res+=process(str, i+2);//i和i+1作为单独的部分，后续有多少种办法
            }
            return res;
        }
        if (str[i] == '2'){
            int res=process(str,i+1);//i自己做决定，后续有多少种办法
            if (i+1 < str.length &&(str[i+1] >='0' && str[i+1]<='6') ){
                res+=process(str, i+2);//i和i+1作为单独的部分，后续有多少种办法
            }
            return res;
        }
        //str[i]='3'~'9,拼完之后超26只有决定一的答案
        return process(str, i+1);
    }
}
