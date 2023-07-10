package com.daimasuixianglu.shejishujujiegou;

//public class RBTreeDemo<T> {
//    Node root; //根节点
//    Node min; //最左节点
//    Node max; //最右节点
//    Boolean RED = true;
//    Boolean BLACK = false;
//
//    /**
//     * 新增
//     *
//     * @param val
//     */
//    public void add(T val) {
//        if (this.root == null) {
//            //根节点为黑色
//            this.root = new Node(val, this.BLACK, null, null, null);
//            return;
//        }
//        Node node = this.root;
//        //当前节点
//        Node current = new Node(val, this.RED, null, null, null);
//        //遍历树，进行插入
//        while (true) {
//            if (val.compareTo((T) node.val) > 0) { //放右子节点
//                if (node.right == null) {
//                    node.right = current;
//                    current.parent = node;
//                    break;
//                }
//                node = node.right;
//            } else { //放左子节点
//                if (node.left == null) {
//                    node.left = current;
//                    current.parent = node;
//                    break;
//                }
//                node = node.left;
//            }
//        }
//        //插入完进行自平衡
//        fixUp(current);
//    }
//
//    /**
//     * 自平衡
//     */
//    public void fixUp(Node node) {
//        //当前节点没父节点，则涂黑
//        if (node.parent == null) {
//            node.color = this.BLACK;
//            this.root = node;
//            return;
//        }
//        //当前节点没有祖父节点
//        if (node.parent.parent == null) {
//            return;
//        }
//        //叔父节点 可以为空
//        Node uncleNode = this.getUncleNode(node);
//        //当前节点为红，且父节点为红
//        if (node.color == this.RED && node.parent.color == this.RED) {
//            //叔父节点为空或者为红，就进行涂色
//            if (uncleNode == null || uncleNode.color == this.RED) {
//                node.parent.color = this.BLACK;
//                if (uncleNode != null) {
//                    uncleNode.color = this.BLACK;
//                }
//                node.parent.parent.color = this.RED;
//                this.fixUp(node.parent.parent);
//            } else if (uncleNode.color == this.BLACK) {
//                //自己是左子叶
//                if (node.parent.left.equals(node)) {
//                    this.right(node);
//                    this.fixUp(node.parent); //右旋后，修改当前节点颜色
//                } else { //自己是右子叶
//                    this.left(node);
//                    this.fixUp(node.left); //曾经的父节点
//                }
//            }
//        }
//    }
//
//    /**
//     * 叔父节点，可以为空
//     *
//     * @param node
//     * @return
//     */
//    private Node getUncleNode(Node node) {
//        Node uncleNode = node.parent.parent.left;
//        if (uncleNode == null || node.parent.equals(uncleNode)) {
//            uncleNode = node.parent.parent.right;
//        }
//        return uncleNode;
//    }
//
//    /**
//     * 当前结点为红，且处于右子叶上。父节点为红，叔父节点为黑或空时。以当前节点为轴进行左旋
//     * 当前节点的祖父节点，变成自己的父节点
//     * 当前节点的父节点，变成自己的左节点
//     * 当前节点的左节点，变成当前右节点的右节点
//     */
//    private void left(Node node) {
//        Node parent = node.parent;
//        Node left = node.left;
//        Node pParent = node.parent.parent;
//        //祖父节点
//        pParent.left = node;
//        //父节点
//        parent.parent = node;
//        parent.right = left;
//        //左节点
//        left.parent = parent;
//        //当前节点
//        node.parent = pParent;
//        node.left = parent;
//    }
//
//    /**
//     * 当前结点为红，且处于左子叶上。父节点为红，叔父节点为黑时。以父节点为轴进行右旋
//     */
//    private void right(Node node) {
//        Node parent = node.parent;
//        Node right = parent.right;
//        Node pParent = node.parent.parent;
//        Node ppParent = node.parent.parent.parent;
//        //涂色
//        pParent.color = this.RED;
//        parent.color = this.BLACK;
//        if (ppParent != null) {
//            //祖祖父节点
//            if (ppParent.right.equals(pParent)) {
//                ppParent.right = parent;
//            } else {
//                ppParent.left = parent;
//            }
//        }
//        //祖父节点
//        pParent.left = right;
//        pParent.parent = parent;
//        //父节点
//        parent.right = pParent;
//        parent.parent = ppParent;
//        //右子节点
//        right.parent = pParent;
//    }
//
//    /**
//     * 前序遍历 从根开始遍历
//     */
//    private String preOrder(Node node) {
//        if (node == null) {
//            return null; //如果左侧树遍历完成，返回null
//        }
//        String strLeft = this.preOrder(node.left);
//        if (strLeft == null) {
//            strLeft = "";
//        }
//        String strRight = this.preOrder(node.right);
//        if (strRight == null) {
//            strRight = "";
//        }
//        return strLeft + node.toString() + strRight;
//    }
//
//    @Override
//    public String toString() {
//        //前序遍历
//        return preOrder(this.root);
//    }
//
//    /**
//     * 结点类
//     */
//    private class Node {
//        public T val;
//        public Boolean color; //红为true
//        public Node left;
//        public Node right;
//        public Node parent;
//
//        public Node(T val, Boolean color, Node left, Node right, Node parent) {
//            super();
//            this.val = val;
//            this.color = color;
//            this.left = left;
//            this.right = right;
//            this.parent = parent;
//        }
//
//        @Override
//        public String toString() {
//            String left = "-";
//            String right = "-";
//            if (this.left != null) {
//                left = this.left.val.toString();
//            }
//            if (this.right != null) {
//                right = this.right.val.toString();
//            }
//            return "[" + left + ", " + this.val.toString() + ", " + right + ", " +
//                    getColorName(this.color) + "]";
//        }
//
//        private String getColorName(Boolean color) {
//            return color == true ? "红" : "黑";
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            Node obj1 = (Node) obj;
//            return obj1.val == this.val;
//        }
//    }
//
//}
