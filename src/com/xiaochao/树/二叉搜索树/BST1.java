package com.xiaochao.树.二叉搜索树;

import com.cjr.shu.TreeNode;

//public class BST1 {
//    private TreeNode root;
//
//    public void insert(int data){
//        if(root == null){
//            root = new TreeNode(data);
//            return;
//        }
//        TreeNode tmp = root;
//        while(tmp != null){
//            if(data > tmp.val){
//                if(tmp.right == null){
//                    tmp.right = new Node(data);
//                    return;
//                }
//                tmp = tmp.right;
//            }else{
//                if(tmp.left == null){
//                    tmp.left = new Node(data);
//                    return;
//                }
//                tmp = tmp.left;
//            }
//        }
//    }
//    public TreeNode find(TreeNode root, int data){
//        TreeNode tmp = root;
//        while(tmp != null){
//            if(data < tmp.val){
//                tmp = tmp.left;
//            }else if(data > tmp.val){
//                tmp = tmp.right;
//            }else{
//                return tmp;
//            }
//        }
//        return null;
//    }
//    //查找此节点的父节点
//    public Node getParentByNode(Node node) {
//        Node left = this.left;
//        Node right = this.right;
//        int targetData = node.data;
//        int data = this.data;
//        //如果当前节点就是传入节点的父节点
//        if (left != null && left == node || right != null && right == node) {
//            return this;
//        } else {
//            if (targetData < data && left!=null){
//                //向左子树找
//                return left.getParentByNode(node);
//            }else if (targetData > data && right != null){
//                //向右子树找
//                return right.getParentByNode(node);
//            }else{
//                return null;
//            }
//        }
//    }
//
//    /**
//     * 删除数据 删除叶子结点
//     * 要删除的数，位于叶子结点
//     * 找到要删除的节点（targetNode）的父节点（parent），判断target是parent的左节点还是右节点。
//     * 是左节点，就让parent.left = null；
//     * 是右节点，就让parent.right= null；
//     * @param data
//     */
//    //删除方法
////    public void remove(int data) {
////        if (root == null) {
////            System.out.println("二叉排序树为空");
////            return;
////        } else {
////            //根据数值，获取节点
////            Node targetNode = getNodeByData(data);
////            //如果找不到节点，直接返回
////            if (targetNode == null) {
////                System.out.println("无法找到这个节点");
////                return;
////            }
////            //如果整棵树只有根节点一个节点，直接删除返回
////            if (root.left == null && root.right == null) {
////                root = null;
////                return;
////            }
////
////            //找到要删除节点的父节点
////            Node parent = getParentByNode(targetNode);
////
////            //如果要删除的节点是叶子结点
////            if (targetNode.left == null && targetNode.right == null) {
////                //判断它是父节点的左子节点还是右子节点，进行删除
////                if (parent.left != null && parent.left == targetNode) {
////                    parent.left = null;
////                } else if (parent.right != null && parent.right == targetNode) {
////                    parent.right = null;
////                }
////            } else {
////                //其他情况
////            }
////
////        } else {
////            System.out.println("无法找到这个节点");
////            return;
////        }
////    }
//    /**
//     * 删除数据 删除有一个孩子的节点
//     * 要删除的数，是一颗子树的根节点，且有一个子节点（子节点可能也有自己的子节点）
//     * 首先找到要删除的节点，再找到它的父节点
//     * 确定要删除的节点的孩子，是左子节点还是右子节点
//     * 确定要删除的节点，是父节点的左子节点还是右子节点
//     * 对应情况，进行删除。
//     * 如果target有左子节点，且它是parent的左子节点，那么令paretn.left = target.left即可
//     * ...
//     * 除此之外，还要考虑，要删除的节点是否为根节点。如果是的话，将它的孩子节点作为新的根节点。
//     */
//    //删除方法
//    public void remove(int data) {
//        if (root == null) {
//            System.out.println("二叉排序树为空");
//            return;
//        } else {
//            //根据数值，获取节点
//            Node targetNode = getNodeByData(data);
//            //如果找不到节点，直接返回
//            if (targetNode == null) {
//                System.out.println("无法找到这个节点");
//                return;
//            }
//            //如果整棵树只有根节点一个节点，直接删除返回
//            if (root.left == null && root.right == null) {
//                root = null;
//                return;
//            }
//
//            //找到要删除节点的父节点
//            Node parent = getParentByNode(targetNode);
//
//            //如果要删除的节点是叶子结点
//            if (targetNode.left == null && targetNode.right == null) {
//                //判断它是父节点的左子节点还是右子节点，进行删除
//                if (parent.left != null && parent.left == targetNode) {
//                    parent.left = null;
//                } else if (parent.right != null && parent.right == targetNode) {
//                    parent.right = null;
//                }
//            } else if (targetNode.left != null && targetNode.right != null) {
//                //要删除的节点含有两个子节点
//            } else {
//                //要删除的节点只含有一个子节点
//                //判断，它的孩子是左子节点还是右子节点
//                if (targetNode.left != null) {
//                    //要删除的节点只包含左子节点
//                    //判断要删除的节点，是父节点的左子节点还是右子节点
//                    if (parent != null) {
//                        if (parent.left == targetNode) {
//                            parent.left = targetNode.left;
//                        } else {
//                            parent.right = targetNode.left;
//                        }
//                    } else {
//                        root = targetNode.left;
//                    }
//                } else {
//                    //要删除的节点只包含右子节点
//                    if (parent != null) {
//                        if (parent.left == targetNode) {
//                            parent.left = targetNode.right;
//                        } else {
//                            parent.right = targetNode.right;
//                        }
//                    } else {
//                        root = targetNode.right;
//                    }
//                }
//
//            }
//
//        }
//    }
//    /**
//     * 删除数据 删除有两个孩子的节点
//     * 要删除的数，是一颗子树的根节点，且有两个子节点（子节点可能也有自己的子节点）
//     * 分析。二叉排序树的最小子树（三个节点）内，最大的是最右边的值，其次是中间结点，最小的是左侧节点。
//     * 此时要删除的是中间结点，那么可以保留最右边节点的值，删除最右边的节点，然后让它的值等于中间结点即可。
//     * 扩展到宏观的二叉树来说，如果对值的大小进行排序，要删除的节点的下一个值，是它的右子树中的最小值。所以，删除那个值对应的节点，然后将那个值赋给此节点即可。
//     * 同样，也可以从左子树中找到最大的值，来替换要删除的值。
//     * 步骤：
//     * 首先找到要删除的节点，再找到它的父节点
//     * 找到要删除节点的右子树的最小值，用临时变量储存
//     * 删除上面说的最小值节点
//     * 让要删除节点的值等于临时变量中的值即可。
//     * 辅助方法，用于查找删除节点右子树的最小值
//     */
//    //查找传入节点的右子树的最小值所在的节点，删除它，返回它的值
//    public int delRightMinNode(Node node){
//        Node target = node;
//        while (target.left != null){
//            target = target.left;
//        }
//        //此时，target就是所需的最小节点
//        remove(target.value);
//        return target.value;
//    }
//}
//左旋的方法
//private void leftRotate(){
//        //创建新的节点，值为当前根节点的值
//        Node newNode = new Node(this.data);
//        //把新的节点的左子树，设置成当前节点的左子树
//        newNode.left = this.left;
//        //把新的节点的右子树，设置成当前节点的右子节点的左子树
//        newNode.right = this.right.left;
//        //把当前节点的值，替换成右子节点的值
//        newNode.data = right.data;
//        //把当前节点的右子树，设置成右子节点的右子树
//        right = right.right;
//        //把当前节点的左子节点，设置成新的节点
//        left = newNode;
//        }
//class Node extends TreeNode implements Comparable<Node> {
//
//    //节点的权值
//    int value;
//    //左子节点
//    Node left;
//    //右子节点
//    Node right;
//
//    public Node(int value) {
//        this.value = value;
//    }
//
//    //前序遍历
//    public void preOrder() {
//        System.out.println(this);
//        if (this.left != null) {
//            this.left.preOrder();
//        }
//        if (this.right != null) {
//            this.right.preOrder();
//        }
//    }
//
//    @Override
//    public int compareTo(Node node) {
//        //从小到大排序
//        return this.value - node.value;
//    }
//
//    @Override
//    public String toString() {
//        return "Node{" +
//                "value=" + value +
//                '}';
//    }
//}
