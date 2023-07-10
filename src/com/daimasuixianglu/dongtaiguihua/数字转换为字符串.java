package com.daimasuixianglu.dongtaiguihua;

/**
 * 给定一个数字，我们按照如下规则把它转换为字符串：1转换成 “a” ，2 转换成 “b”，……，12 转换成 “l”，……，26转换成 “z”。一个数字可能有多个转换。请编程实现一个函数，用来计算一个数字有多少种不同的转换方法。
 * 示例1
 * 输入
 * "11111"
 * 输出
 * 8
 */
public class 数字转换为字符串 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 用来计算一个数字有多少种不同的转换方法
     * @param number string字符串 给定的数字
     * @return int整型
     */
       public int translateNumber (String number) {
        // write code here
        if (number == null || number.length()== 0){
            return 0;
        }
        return process(number.toCharArray(),0);
    }
    public static int process(char[] ch,int index){
        if (index == ch.length){
            return 1;
        }
        //i没有到终止位置
        if (ch[index] == '0'){
            return 0;
        }
        if (ch[index] == '1'){
            int res=process(ch, index+1);
            if (index+1 <ch.length){
                res+=process(ch, index+2);
            }
            return res;
        }
        if (ch[index] == '2'){
            int res=process(ch, index+1);
            if (index+1<ch.length &&(ch[index+1]>='0' && ch[index+1]<='6')){
                res+=process(ch, index+2);
            }
            return res;
        }
        return process(ch, index+1);
    }
}
