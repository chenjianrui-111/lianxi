package com.xiaochao.树.霍夫曼树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给出一个数列{13 7 8 3 29 6 1}，要求转成一颗霍夫曼树。
 * 步骤：
 * 将数列从小到大进行排序，将每一个数据看做一颗最简单的二叉树，构成n颗二叉树的集合F
 * 取出根节点权值最小的两颗二叉树
 * 组成一颗新的二叉树，该新的二叉树根节点的权值是前面两颗二叉树根节点权值的和
 * 在F中删除这两颗小树，同时将合并的树加入集合F中。再将这颗新的二叉树，以根节点的权值大小再次排序
 * 重复上面的步骤，直到数列的所有数据都被处理，就得到了一颗霍夫曼树。
 * 总之，思想就是从下到上，从小到大构建二叉树，保证权值大的一定靠近根节点，这样WPL就是最小的，也就是霍夫曼树。
 */
public class Huffman01 {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        Node root = huffman(array);
        //show(root);
    }
    //前序遍历的方法
    public static void show(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //创建霍夫曼树的方法
    public static Node huffman(int[] array) {
        //将每个数看做一个节点，构建二叉树集合
        List<Node> nodeList = new ArrayList<Node>();
        for (int value : array) {
            nodeList.add(new Node(value));
        }
        //循环处理，直到集合中只剩下一颗二叉树，它就是霍夫曼树
        while (nodeList.size() > 1){
            //从小到大排序
            Collections.sort(nodeList);
            //取出根节点权值最小的两颗二叉树
            Node left = nodeList.get(0);
            Node right = nodeList.get(1);
            //构建一颗新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            System.out.println(left +"和"+right + "组合成了新的二叉树：" + parent);

            //移除两颗小二叉树
            nodeList.remove(left);
            nodeList.remove(right);
            //将新的二叉树加入集合
            nodeList.add(parent);
        }
        //返回霍夫曼树的根节点
        return nodeList.get(0);
    }
}
//节点类
//为了让Node对象持续自动排序，使用选择器
class Node implements Comparable<Node> {

    //节点的权值
    int value;
    //左子节点
    Node left;
    //右子节点
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node node) {
        //从小到大排序
        return this.value - node.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
