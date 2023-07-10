package com.daimasuixianglu.dongtaiguihua;

import java.util.ArrayList;
import java.util.List;

public class Code02_PrintAllSubsquence {
    public static List<String> allSubseq(String str){
        List<String> ans=new ArrayList<>();
        if (str==null){
            return ans;
        }
        if (str.length()==0){
            ans.add("");
            return ans;
        }
        char[] cs=str.toCharArray();
        process(cs,0,"",ans);
        return ans;
    }

    //str[0..index-1]的沿途决定，是path决定的
    //srt[index...]每一个字符，都可以选择要或不要
    //所有的子序列，都放入ans中去
    public static void process(char[] cs,int index,String path,List<String> ans){
        if (index == cs.length){
            ans.add(path);
        }else {
            //不要当前字符
            process(cs, index+1, path, ans);
            //要当前字符
            process(cs,index+1,path+String.valueOf(cs[index]),ans);
        }
    }
}
