package com.daimasuixianglu.dongtaiguihua;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//要求不出现重复的子序列
public class Code03_PrintAll {

    //set中的所有字符都可以选择
    //形成的所有全排列放入ans
    //沿途的决定是path
    public static void process(ArrayList<Character> set, String path, List<String> ans){
        if (set.isEmpty()){
            ans.add(path);
            return;
        }
        HashSet<Character> picks=new HashSet<>();
        //set中每一个字符，都可以作为当前字符，但是一旦当前决定要，后续就不能再使用了
        for (int index = 0; index < set.size() ; index++) {
            if (!picks.contains(set.get(index))){
                picks.add(set.get(index));
                String pick=path + set.get(index);
                ArrayList<Character> next=new ArrayList<>(set);
                next.remove(index);
                process(next,pick,ans);
            }
        }
    }
}
