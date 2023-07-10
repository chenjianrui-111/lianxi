package com.daimasuixianglu.haxibiao;

/**
 *给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 提示:
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 *
 * 数组其实就是一个简单哈希表，而且这道题目中字符串只有小写字符，
 * 那么就可以定义一个数组，来记录字符串s里字符出现的次数。
 * 需要定义一个多大的数组呢，定一个数组叫做record，大小为26 就可以了，初始化为0，
 * 因为字符a到字符z的ASCII也是26个连续的数值。
 * 定义一个数组叫做record用来上记录字符串s里字符出现的次数。
 * 需要把字符映射到数组也就是哈希表的索引下表上，因为字符a到字符z的ASCII是26个连续的数值，所以字符a映射为下表0，相应的字符z映射为下表25。
 * 再遍历 字符串s的时候，只需要将 s[i] - ‘a’ 所在的元素做+1 操作即可，并不需要记住字符a的ASCII，只要求出一个相对数值就可以了。 这样就将字符串s中字符出现的次数，统计出来了。
 * 那看一下如何检查字符串t中是否出现了这些字符，同样在遍历字符串t的时候，对t中出现的字符映射哈希表索引上的数值再做-1的操作。
 * 那么最后检查一下，record数组如果有的元素不为零0，说明字符串s和t一定是谁多了字符或者谁少了字符，return false。
 * 最后如果record数组所有元素都为零0，说明字符串s和t是字母异位词，return true。
 * 时间复杂度为O(n)，空间上因为定义是的一个常量大小的辅助数组，所以空间复杂度为O(1)。
 */
public class 有效的字母异位词 {
    public boolean isAnagram(String s, String t) {
        int []record=new int[26];
        for (char c:s.toCharArray()){
            record[c-'a']++;
        }
        for (char c:t.toCharArray()){
            record[c-'a']--;
        }
        for (int i:record){
            if (i!=0){
                return false;
            }
        }
        return true;
    }
}
