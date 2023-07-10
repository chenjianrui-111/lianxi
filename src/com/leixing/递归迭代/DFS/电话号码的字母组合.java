package 递归$迭代.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * DFS 回溯解法
 * 对于字符串 ds 中的每一位数字，都有其对应的字母映射数组。
 * 在 DFS 中决策每一位数字应该对应哪一个字母，当决策的位数 i == n，
 * 代表整个 ds 字符串都被决策完毕，将决策结果添加到结果集：
 */
public class 电话号码的字母组合 {
    Map<String, String[]> map = new HashMap<String, String[]>(){{
        put("2", new String[]{"a", "b", "c"});
        put("3", new String[]{"d", "e", "f"});
        put("4", new String[]{"g", "h", "i"});
        put("5", new String[]{"j", "k", "l"});
        put("6", new String[]{"m", "n", "o"});
        put("7", new String[]{"p", "q", "r", "s"});
        put("8", new String[]{"t", "u", "v"});
        put("9", new String[]{"w", "x", "y", "z"});
    }};

    public List<String> letterCombinations(String ds) {
         int n=ds.length();
         List<String> ans=new ArrayList<>();
         if (n == 0) return ans;
         StringBuilder sb=new StringBuilder();
         dfs(ds,0,n,sb,ans);
         return ans;
    }
    void dfs(String ds,int i,int n,StringBuilder sb,List<String> ans){
        if (i == n){
            ans.add(sb.toString());
            return;
        }
        String key=ds.substring(i,i+1);
        String [] all=map.get(key);
        for (String item : all){
            sb.append(item);
            dfs(ds,i+1,n,sb,ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
/**
 *时间复杂度：n 代表字符串 ds 的长度，一个数字最多对应 4 个字符（7 对应 “pqrs"），即每个数字最多有 4 个字母需要被决策。复杂度为 O(4^n)
 * 空间复杂度：有多少种方案，就需要多少空间来存放答案。复杂度为 O(4^n)
 */