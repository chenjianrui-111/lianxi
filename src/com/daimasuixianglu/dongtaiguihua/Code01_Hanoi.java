package com.daimasuixianglu.dongtaiguihua;

public class Code01_Hanoi {
    public static void hanoi(int n){
        if (n>0){
            func(n,"左","中","右");
        }
    }
    //1~i 圆盘目标是from->to,other是另外一个
    public static void func(int N,String from,String to,String other){
        if (N == 1){
            System.out.println("Move 1 from"+from +"to" +to);
        }else {
            func(N - 1,from,other,to);
            System.out.println("Move"+N+"from"+from+"to"+to);
            func(N - 1,other,to,from);
        }
    }

    public static void all(int N){
        LeftToRight(N);
    }

    //N层汉诺塔问题从左到右
    public static void LeftToRight(int N){
        if (N == 1){
            System.out.println("Move 1 from left yo right");
        }else { //N>1
            LeftToMid(N-1);//1~N-1
            System.out.println("Move"+N+"from Left to Right");
            MidToLeft(N-1);
        }
    }
    //N层汉诺塔问题从左到中
    public static void LeftToMid(int N){
        if (N == 1){
            System.out.println("Move 1 from left to Mid");
        }else { //N>1
            LeftToRight(N-1);//1~N-1
            System.out.println("Move"+N+"from Left to Mid");
            RightToMid(N-1);
        }
    }
    public static void MidToLeft(int N){
        if (N==1){
            System.out.println("Move 1 from Mid to Left");
        }else {
            MidToRight(N-1);//1~N-1 from->other
            System.out.println("Move"+N+"from Mid to Right");//N自己from->to
            RightToLeft(N-1);//1~N-1 other->to
        }
    }
    public static void RightToMid(int N){
        if (N == 1){
            System.out.println("Move 1 from Right to Mid");
        }else { //N>1
            RightToLeft(N-1);//1~N-1
            System.out.println("Move"+N+"from Left to Mid");
            LeftToMid(N-1);
        }
    }
    public static void MidToRight(int N) {
        if (N == 1) {
            System.out.println("Move 1 from Mid to Right");
        } else { //N>1
            MidToLeft(N - 1);
            System.out.println("Move 1 from Mid to Left");
            LeftToRight(N - 1);
        }
    }
    public static void RightToLeft(int N){
        if (N == 1) {
            System.out.println("Move 1 from Right to Left");
        } else { //N>1
            RightToMid(N - 1);
            System.out.println("Move 1 from Right to Mid");
            MidToRight(N - 1);
        }
    }
}
