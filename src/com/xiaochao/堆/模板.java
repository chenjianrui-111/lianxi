package com.xiaochao.堆;
import java.util.*;

//    class Heap{
//        private int[] array; //开辟一个数组保存堆
//        private int length;  // 记录堆的实际长度（非常必要），可以节省出堆的操作。
//        Heap(int n){
//            array = new int[n];
//            length = 0;
//        }
//        public void push(int x){
//            //将新添加的元素放置到最后。
//            array[length++] = x;
//            //此时调整最后一个子节点所在的子树，可以自己画棵树推一下
//            for(int i = length / 2 - 1; i >= 0; i = (i - 1) / 2){
//                adjustHeap(i);
//                //这里是由于（i-1）/2取下整，当i=0会出现死循环
//                if(i == 0){
//                    break;
//                }
//            }
//        }
//
//        public void pop(){
//            if(length > 0){
//                System.out.println(array[0]);
//                array[0] = array[length - 1];
//                length--;
//                adjustHeap(0);
//            }else{
//                System.out.println("empty");
//            }
//        }
//        //这个方法无需多说
//        public void top(){
//            if(length <= 0)
//                System.out.println("empty");
//            else{
//                System.out.println(array[0]);
//            }
//        }
//        private void adjustHeap(int i){
//            int temp = array[i];
//            for(int k = 2 * i + 1; k < length; k = 2 * k + 1){
//                //判断左孩子大还是右孩子大
//                if( k + 1 < length && array[k+1] > array[k])
//                    k++;
//                //如果存在子树比当前节点大
//                if(array[k] > temp){
//                    //将大的数据赋给当前节点
//                    array[i] = array[k];
//                    //调整赋值的子树
//                    i = k;
//                }else{
//                    break;
//                }
//            }
//            array[i] = temp;
//        }
//    }
//    public class Main{
//        public static void main(String... args){
//            Scanner scan = new Scanner(System.in);
//            int n = scan.nextInt();
//            Heap heap = new Heap(n);
//            scan.nextLine();
//            for(int i = 1; i <= n; i++){
//                String s = scan.nextLine();
//                String[] action = s.split(" ");
//                if(action[0].equals("push")){
//                    heap.push(Integer.parseInt(action[1]));
//                }else if(action[0].equals("pop")){
//                    heap.pop();
//                }else if(action[0].equals("top")){
//                    heap.top();
//                }
//            }
//        }
//    }
//
//
