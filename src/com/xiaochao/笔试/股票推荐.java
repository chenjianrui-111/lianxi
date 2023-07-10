package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 股票推荐系统。每个用户可能关注几个公司，比如A,B,C，如果有另一个用户只关注了A，那么就会给他推荐B，C。这时候如果来了一个用户关注C，D，那么后来关注C的用户，都会推荐A，B，关注A的用户都会推荐BCD。
 * 在有上面的关系后，求用户查询的时候，会给它推荐的公司的个数。
 * 第一行给出命令的个数
 * 然后每一行
 * 如果第一个数字是1，代表注册，然后第二个参数是用户名字，第三个参数是用户关注的公司的个数。下一行则是用户关注的公司的名字。
 * 如果第一个数字是2，代表查询，返回会给该用户推荐的公司数量，如果该用户没注册，则返回error
 * 思路
 * 并查集模拟
 */
public class 股票推荐 {
    static class UnionSet{
        List<Integer> parent;
        List<Integer> cnt;
        List<String> company;
        HashMap<String,Integer> companyMap;

        public UnionSet(){
            parent = new ArrayList<>();
            cnt = new ArrayList<>();
            company = new ArrayList<>();
            companyMap = new HashMap<>();
        }
        public void addCompany(String companyName){
            if (companyMap.containsKey(companyName)){
                return;
            }
            cnt.add(1);
            parent.add(cnt.size() - 1);
            company.add(companyName);
            companyMap.put(companyName,company.size() - 1);
        }

        public void union(String u,String v){
            int ufather = getParent(u);
            int vfather = getParent(v);
            if(ufather == vfather) return;
            if(cnt.get(ufather)>cnt.get(vfather)){
                parent.set(vfather,ufather);
                cnt.set(ufather,cnt.get(ufather)+cnt.get(vfather));
            }
            else{
                parent.set(ufather,vfather);
                cnt.set(vfather,cnt.get(ufather)+cnt.get(vfather));
            }
        }

        public int getParent(String cName){
            int idx = companyMap.get(cName);
            while (parent.get(idx) != idx){
                parent.set(idx,parent.get(idx));
                idx = parent.get(idx);
            }
            return idx;
        }

        public int getSetsByCompanies(int k){
            int parent = getParent("k");//
            return cnt.get(parent);
        }
    }
    public static class Main{
        public static void main(String[] args) {
            Scanner sc =new Scanner(System.in);
            HashMap<String,String[]> person = new HashMap<>();
            UnionSet us =new UnionSet();
            int ops = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < ops ; i++) {
                String command = sc.nextLine();
                String[] commands = command.split(" ");
                String name = commands[1];
                if (commands[0].equals("1")){
                    if (person.containsKey(name)){
                        System.out.println("error");
                        continue;
                    }
                    String companyStr =sc.nextLine();
                    String[] companies = companyStr.split(" ");
                    for (int j = 0; j < companies.length ; j++) {
                        us.addCompany(companies[j]);
                        if (j > 0){
                            us.union(companies[j],companies[j-1]);
                        }
                    }
                    person.put(name,companies);
                }
                else {
                    if (person.containsKey(name)){
                        int p = us.getParent(person.get(name)[0]);
                        int cnt = us.getSetsByCompanies(p);
                        System.out.println(cnt - person.get(name).length);
                    }
                    else {
                        System.out.println("error");
                    }
                }
            }
        }
    }
}
