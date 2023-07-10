package com.leixing.递归迭代.DFS;

import sun.reflect.annotation.AnnotationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * DFS
 * 既然题目是求所有的方案，那只能爆搜了，爆搜可以使用 DFS 来做。
 * 从数据范围 1 <= n <= 8 来说，DFS 应该是稳稳的 AC。
 * 这题的关键是我们要从题目中发掘一些性质：
 * 括号数为 n，那么一个合法的括号组合，应该包含 n 个左括号和 n 个右括号，组合总长度为 2n
 * 一对合法的括号，应该是先出现左括号，再出现右括号。那么意味着任意一个右括号的左边，至少有一个左括号
 * 其中性质 2 是比较难想到的，我们可以用反证法来证明性质 2 总是成立：
 * 假设某个右括号不满足「其左边至少有一个左括号」，即其左边没有左括号，那么这个右括号就找不到一个与之对应的左括号进行匹配。
 * 这样的组合必然不是有效的括号组合。
 * 使用我们「20. 有效的括号（简单）」的思路（栈）去验证的话，必然验证不通过。
 * 掌握了这两个性质之后，我们可以设定一个初始值为 0 的得分值，令往组合添加一个 ( 得分值 + 1，往组合添加一个 ) 得分值 -1。
 * 这样就有：
 * 一个合法的括号组合，最终得分必然为 0 （左括号和右括号的数量相等，对应了性质 1）
 * 整个 DFS 过程中，得分值范围在 [0, n]（得分不可能超过 n 意味着不可能添加数量超过 n 的左括号，对应了性质 1；得分不可能为负数，意味着每一个右括号必然有一个左括号进行匹配，对应性质 2）
 */
public class 括号生成 {
    public List<String> generateParenthesis(int n) {
       List<String> list=new ArrayList<>();
       dfs(0,n*2,0,n,"", list);
       return list;
    }
    /**
     * i: 当前遍历到位置
     * n: 字符总长度
     * score: 当前得分，令 '(' 为 1， ')' 为 -1
     * max: 最大得分值
     * path: 当前的拼接结果
     * ans: 最终结果集
     */

    void dfs(int i,int n,int score,int max,String path,List<String> list){
        if (i == n){
            if (score == 0) list.add(path);
        }else {
            if (score + 1<=max) dfs(i+1,n,score+1,max,path+"(",list);
            if (score - 1 >=0) dfs(i+1,n,score-1,max,path+")",list);
        }
    }
}
/**
 *时间复杂度：放置的左括号数量为 n，右括号的个数总是小于等于左括号的个数，典型的卡特兰数问题。复杂度为 O(C(2n)^n)
 * 空间复杂度：O(1)
 */
