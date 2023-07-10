package com.cjr.qita.有序表与并查集;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 在 Normal Mode 下
 * - 按下 i ：进入 Insert Mode 。
 * - 按下 f ：紧接着一个小写字母 char，若当前光标后（右）方有至少一个 char ，将光标移动到其所在位置，否则不移动。
 * - 按下 x ：删除当前光标所在位的字符，后面的字符均会前移一格。
 * - 按下 h ：将光标向左（前）移动一格，若无法移动就不移动。
 * - 按下 l ：将光标向右（后）移动一格，若无法移动就不移动。
 * - 若按下了其他字符：无任何效果。
 * 在 Insert Mode 下
 * - 按下非 e 小写字母 char ：在光标当前位置前插入这个字母 char。
 * - 按下 e ：退出 Insert Mode（进入 Normal Mode）。
 * 输入描述:
 * 两行，第一行字符串 s ，第二行字符串 t 。
 * 输出描述:
 * 一行，威穆里最后留下的字符串。
 * 示例1
 * 输入
 * applese
 * xfllhlia
 * 输出
 * pplaese
 * 说明
 * - 初始时，字符串为 威穆处于 Normal Mode 。下划线表示光标所在位置。
 * - 第一步操作为 x ，删除当前光标所在位的字符，并且光标后移一格。字符串变为 。威穆仍处于 Normal Mode。
 * - 下一步操作为 f ，之后跟有一个字符 `l` 。光标后存在字符 `l` ，故移动到该位置。字符串变为  。威穆仍处于 Normal Mode。
 * - 下一步操作为 l ，光标后移一格。字符串变为 。威穆仍处于 Normal Mode。
 * - 下一步操作为 h ，光标前移一格。字符串变为  。威穆仍处于 Normal Mode。
 * - 下一步操作为 l ，光标后移一格。字符串变为  。威穆仍处于 Normal Mode。
 * - 下一步操作为 i ，威穆进入 Insert Mode。字符串仍为 。
 * - 下一步操作为 a ，而非 e ，故插入字符 a 。字符串变为  。
 * 示例2
 * 输入
 * pppp
 * iaefpfpia
 * 输出
 * appapp
 */
class bianHuanMoShi {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        StringBuilder sb = new StringBuilder(s);
        char[] chs = t.toCharArray();
        boolean normalMode = true;
        int index=0;
        for(int j=0;j<chs.length;++j){
            if (normalMode) {
                switch (chs[j]){
                    case 'i':
                        normalMode = false;
                        break;
                    case 'f':
                        int tmpindex = sb.indexOf(String.valueOf(t.charAt(++j)),index+1);
                        index = tmpindex==-1?index:tmpindex;
                        break;
                    case 'x':
                        sb.deleteCharAt(index);
                        break;
                    case 'h':
                        if (index > 0) {
                            index--;
                        }
                        break;
                    case 'l':
                        if (index < s.length() - 1) {
                            index++;
                        }
                        break;
                }
            }else{
                switch (chs[j]){
                    case 'e':
                        normalMode = true;
                        break;
                    default:
                        sb.insert(index++,chs[j]);
                }
            }
        }

        String res = sb.toString();
        System.out.println(res);
    }
}
