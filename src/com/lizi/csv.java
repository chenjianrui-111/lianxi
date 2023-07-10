package com.lizi;

import java.util.ArrayList;

/**
 * CSV解析题
 * 背景说明:
 *
 *       CSV是一种文件格式，通常是由Microsoft Excel导出的纯文本文件，用于保存多行、多列的表格数据
 *  CSV文件的编码规则 ：
 *  1) 一行数据中, 多个字段间以逗号[,]分隔
 *  2) 如果字段值不含有逗号[,]和单引号[']和空格[ ], 编码时保持字段值不变
 *  3) 如果字段值本身含有逗号[,]，编码时在原字段值两边要加上单引号[']
 *  4) 如果字段值本身含有空格[ ]，编码时在原字段值两边也要加上单引号[']
 *
 *   5) 如果字段值本身含有单引号[']，编码时在原字段值两边也要加上单引号['], 并且原值中的每一个单引号[']替换为两个单引号['']
 * 效率低，会
 *  编程要求：
 *   1、写一个程序，读入CSV文件的每一行，正确的解析出其中每个字段的值（即实现导入算法，也就是导出CSV文件的逆向算法）
 *   2、除了包含在单引号[']之内的空格以外，其他的空格均为无效空格，解析时需要过滤掉
 *   3、该程序具有良好的时间复杂度与空间复杂度
 * 示例1
 * 输入： "1, Kate, Shopping, 49.6, 1979-12-56"
 * 输出： ["1","Kate","Shopping","49.6","1979-12-56"]
 * 示例2
 * 输入： "Jane,  'Chess, ''Fly'', Football'  , 1976-8-23"
 * 输出： ["Jane","Chess, 'Fly', Football","1976-8-23"]
 * 示例3
 * 输入： "Peter,,' ',,54.6,1980-4-21"
 * 输出： ["Peter",""," ","","54.6","1980-4-21"]
 */
public class csv {
    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         * 解析CSV文件中的一行
         * @param lineContent string字符串 CSV文本文件中的一行的内容
         * @return string字符串ArrayList
         */
        public ArrayList<String> parseCSVLine (String lineContent) {
            ArrayList<String> result = new ArrayList<>();
            boolean begin = false;
            String tmp = "";
            if(lineContent.trim().length() == 0){
                return result;
            }
            boolean flag = false;
            for(int i=0;i<lineContent.length();i++){
                char c = lineContent.charAt(i);
                if(c == ' ' && !begin){
                    continue;
                }
                if(c == ',' && !begin){

                    result.add(tmp);
                    tmp = "";
                    continue;
                }
                if(c == '\'' && lineContent.charAt(i+1) == '\'' && flag){
                    flag = false;
                    tmp += '\'';
                } else if(c == '\''){

                }else {
                    flag = true;
                    tmp += c;
                }

                if(!begin && c=='\''){
                    begin = true;
                    continue;
                }
                if(begin && c =='\''){
                    begin = false;
                }
            }
            result.add(tmp);
            return result;

        }
    }
}
