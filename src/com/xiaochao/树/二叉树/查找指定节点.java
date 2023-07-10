package com.xiaochao.树.二叉树;

import com.cjr.shu.Node;

//public class 查找指定节点 {
//
//    //前序遍历
//    public Node preOrderSearch(int id) {
//        //先判断根节点
//        if (this.id == id){
//            return this;
//        }
//        Node resNode = null;
//        //递归向左子树前序查找
//        if (this.left != null) {
//            Node node = this.left.preOrderSearch(id);
//            if (node != null){
//                resNode = node;
//            }
//        }
//        //递归向右子树前序查找
//        if (this.right != null) {
//            Node node = this.right.preOrderSearch(id);
//            if (node != null){
//                resNode = node;
//            }
//        }
//        return resNode;
//    }
//}
