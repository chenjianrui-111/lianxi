package com.xiaochao.笔试;

public class Main6 {
    private  static  Main6  instance = null;
    private Main6(){}
    public static Main6 getInstance() {
//        if (instance == null){
//            instance = new Main6();
//        }
//        return instance;
//    }
        if (instance == null) {
            synchronized (Main6.class) {
                if (instance == null) {
                    instance = new Main6();
                }
            }
        }
        return instance;
    }
static int a (int tab){
        int n = tab -1;
        n |= n >> 1;
        return n;
}
    public static void main(String[] args) {
        int a = a(5);
        System.out.println(a);
    }
}
