package com.xiaochao.树.线索二叉树;

//public class 中序遍历 {
//    //节点
//    class Node {
//        private int id;
//        private String msg;
//        //左节点
//        private Node left;
//        //右节点
//        private Node right;
//
//        //left域的类型。为0，存放的是子节点；为1，存放的是前驱节点
//        private int left_type = 0;
//        //right域的类型。为0，存放的是子节点；为1，存放的是后继节点
//        private int right_type = 0;
//    }
//    //重载线索化方法，提供入口
//    public void threadedNode() {
//        threadedNode(root);
//    }
//
//    //对二叉树进行中序线索化
//    public void threadedNode(Node node) {
//        if (node == null) {
//            return;
//        }
//
//        //先线索化左子树
//        threadedNode(node.getLeft());
//
//        //线索化当前节点
//        //处理前驱结点
//        if (node.getLeft() == null) {
//            node.setLeft(preNode);
//            //修改标志类型为线索
//            node.setLeft_type(1);
//        }
//        //处理后继结点
//        if (preNode != null && node.getRight() == null) {
//            //让前一个节点的后继指向当前节点
//            preNode.setRight(node);
//            //修改标志类型为线索
//            preNode.setRight_type(1);
//        }
//
//        //让当前节点成为之前的节点
//        preNode = node;
//
//        //最后线索化右子树
//        threadedNode(node.getRight());
//
//    }
//    //对中序线索化二叉树的遍历
//    public void threadList() {
//        //存储当前遍历的节点
//        Node node = root;
//
//        while (node != null) {
//            //找到left_Type=1的节点，它是线索
//            if (node.getLeft_type() == 0) {
//                node = node.getLeft();
//            }
//            //输出当前节点
//            System.out.println(node);
//
//            //如果当前节点的right域存放了后继节点，就线性输出
//            while (node.getRight_type() == 1) {
//                node = node.getRight();
//                System.out.println(node);
//            }
//
//            //替换遍历的节点
//            node = node.getRight();
//        }
//
//    }
//}
