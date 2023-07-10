package com.xiaochao.笔试;

import java.util.HashMap;
import java.util.Map;

public class 文本替换 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param my_template string字符串
     * @param keys string字符串一维数组
     * @param values string字符串一维数组
     * @return string字符串
     */
    public String token_replace (String my_template, String[] keys, String[] values) {
        // write code here
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < keys.length; ++i) map.put(keys[i], values[i]);

        StringBuilder ans = new StringBuilder(my_template);

        while(true){
            int minIndex = my_template.length();
            String target = "", source = "";
            // 每次取匹配的最左端的值 进行替换
            boolean flag = false;
            for(String temp : keys){
                String next = "%"+ temp +"%";

                int index = ans.indexOf(next);
                if(index != -1){
                    flag = true;
                    if(index < minIndex){
                        minIndex = index;
                        source = next;
                        target = temp;
                    }
                }
            }
            if(!flag) break;

            ans.replace(minIndex, minIndex + source.length(), map.get(target));
        }

        return ans.toString();
    }
}
