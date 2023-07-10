package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 查找和排序
 * 题目：输入任意（用户，成绩）序列，可以获得成绩从高到低或从低到高的排列,相同成绩
 * 都按先录入排列在前的规则处理。
 * 示例：
 * jack      70
 * peter     96
 * Tom       70
 * smith     67
 * 从高到低  成绩
 * peter     96
 * jack      70
 * Tom       70
 * smith     67
 * 从低到高
 * smith     67
 * jack      70
 * Tom      70
 * peter     96
 * 输入描述:
 * 注意一个case里面有多组样例，请用循环处理输入
 * 输入多行，先输入要排序的人的个数，然后输入排序方法0（降序）或者1（升序）再分别输入他们的名字和成绩，以一个空格隔开。
 * 输出描述:
 * 按照指定方式输出名字和成绩，名字和成绩之间以一个空格隔开
 * 示例1
 * 输入
 * 3
 * 0
 * fang 90
 * yang 50
 * ning 70
 * 输出
 * fang 90
 * ning 70
 * yang 50
 * 示例2
 * 输入
 * 3
 * 1
 * fang 90
 * yang 50
 * ning 70
 * 3
 * 0
 * moolgouua 43
 * aebjag 87
 * b 67
 * 输出
 * yang 50
 * ning 70
 * fang 90
 * aebjag 87
 * b 67
 * moolgouua 43
 * 说明
 * 第一组用例:
 * 3
 * 1
 * fang 90
 * yang 50
 * ning 70
 * 升序排序为：
 * yang 50
 * ning 70
 * fang 90
 * 第二组降序为:
 * aebjag 87
 * b 67
 * moolgouua 43
 */
public class 成绩排序 {
    public static class Student{
        public String name;
        public int score;
        public Student(String name,int score){
            this.name=name;
            this.score=score;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
      while (bf.ready()){
          int n= Integer.parseInt(bf.readLine());
          int type=Integer.parseInt(bf.readLine());
          Student[] stu=new Student[n];
          for (int i = 0; i <n ; i++) {
              String[] s=bf.readLine().split(" ");
              stu[i]=new Student(s[0],Integer.parseInt(s[1]));
          }
          //比较器
          Arrays.sort(stu, new Comparator<Student>() {
              @Override
              public int compare(Student o1, Student o2) {
                  return type == 0 ? (o2.score - o1.score) : (o1.score - o2.score);
              }
          });
          //输出
          StringBuilder sb=new StringBuilder();
          for (int i = 0; i <n ; i++) {
              sb.append(stu[i].name +" "+stu[i].score+ "\n");
          }
          System.out.println(sb.substring(0,sb.length()-1));
      }
    }
}
