package com.cjr.qita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 如果一个单词通过循环右移获得的单词，我们称这些单词都为一种循环单词。 例如：picture 和 turepic 就是属于同一种循环单词。 现在给出n个单词，需要统计这个n个单词中有多少种循环单词。
 * 输入描述:
 * 输入包括n+1行：
 *
 * 第一行为单词个数n(1 ≤ n ≤ 50)
 * 接下来的n行，每行一个单词word[i]，长度length(1 ≤ length ≤ 50)。由小写字母构成
 * 输出描述:
 * 输出循环单词的种数
 * 示例1
 * 输入
 * 5
 * picture
 * turepic
 * icturep
 * word
 * ordw
 * 输出
 * 2
 */
public class RepeatWords {
    public static void main(String[] args) throws IOException {
        BufferedReader bfd=new BufferedReader(new InputStreamReader(System.in));
        int wordsNums = Integer.parseInt(bfd.readLine());
        //创建一个数组，用于存放读进来的字符串
        String []words=new String[wordsNums];
        for (int i=0;i<words.length;i++){
            words[i]=bfd.readLine();
        }

        //开始判断第j个字符串是否是第i个字符串的循环单词
        //判断方法：把第i个字符串的两遍拼在一起，找第j个字符串是否被包含其中，如果是，属于循环单词，
        //把后面重复的单词改成一个标记，用于统计
        String doubleWords;
        int count=0;
        for (int i=0;i<wordsNums;i++){
            if (!"1".equals(words[i])){
                doubleWords=words[i]+words[i];
                //把后续单词中与当前单词构成循环单词的找出来，并改为“1”
                int k=0;
                for (int j=i+1;j<wordsNums;j++){
                    if (doubleWords.contains(words[j]) &&words[j].length()==words[i].length()){
                        words[j]="1";
                    }
                }
                count++;
            }
        }
        System.out.println(count);
    }
}
