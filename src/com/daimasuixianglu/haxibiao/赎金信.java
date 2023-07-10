package com.daimasuixianglu.haxibiao;

/**
 *给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * 示例 1：
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 因为题目所只有小写字母，那可以采用空间换取时间的哈希策略， 用一个长度为26的数组还记录magazine里字母出现的次数。
 * 然后再用ransomNote去验证这个数组是否包含了ransomNote所需要的所有字母。
 */
public class 赎金信 {
    public boolean canConstruct(String ransomNote, String magazine) {
        //记录杂志字符串出现的次数
        int[] arr = new int[26];
        int temp;
        for (int i = 0; i <magazine.length() ; i++) {
            temp=magazine.charAt(i)-'a';
            arr[temp]++;
        }
        for (int j = 0; j <ransomNote.length() ; j++) {
            temp=ransomNote.charAt(j)-'a';
            //对于金信中的每一个字符都在数组中查找
            //找到相应位减一，否则找不到返回false
            if (arr[temp]>0){
                arr[temp]--;
            }else {
                return false;
            }
        }
        return true;
    }
}
