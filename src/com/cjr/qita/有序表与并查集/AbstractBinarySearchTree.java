package com.cjr.qita.有序表与并查集;

/*public class AbstractBinarySearchTree {
    public Node root;
    protected int size;

    protected Node createNode(int value,Node parent,Node left,Node right){
        return new Node(value,parent,left,right);
    }

    public Node search(int element){
        Node node=root;
        while (node !=null && null != node.value &&node.value!=element){
            if (element <node.value){
                node=node.left;
            }else {
                node=node.right;
            }
        }
        return node;
    }

    public Node insert(int element){
        if (root == null){
            root=createNode(element,null,null,null);
            size++;
            return root;
        }

        Node insertParentNode=null;
        Node searchTempNode=root;
        while (searchTempNode !=null &&searchTempNode.value!=null){
            insertParentNode=searchTempNode;
            if (element < searchTempNode.value){
                searchTempNode=searchTempNode.left;
            }else {
                searchTempNode=searchTempNode.right;
            }
        }

        Node newNode=createNode(element,insertParentNode,null,null);
        if (insertParentNode.value >newNode.value){
            insertParentNode.left=newNode;
        }else {
            insertParentNode.right=newNode;
        }
        size++;
        return newNode;
    }

    public Node delete(int element){
        Node deleteNode=search(element);
        if (deleteNode !=null){
            return delete(deleteNode);
        }else {
            return null;
        }
    }

    protected Node delete(Node deleteNode){
        if (deleteNode !=null){
            Node nodeToReturn=null;
            if (deleteNode !=null){
                if (deleteNode.left==null){
                    //transplant(a,b)b去替换a的环境，a断连出来把b返回
                    nodeToReturn=transplant(deleteNode,deleteNode.right);
                }else if (deleteNode.right==null){
                    nodeToReturn=transplant(deleteNode,deleteNode.left);
                }else {
                    Node successorNode=getMinimum(deleteNode.right);
                    if (successorNode.parent!=deleteNode){
                        transplant(successorNode,successorNode.right);
                        successorNode.right=deleteNode.right;
                        successorNode.right.parent=successorNode;
                    }
                    transplant(deleteNode,successorNode);
                    successorNode.left=deleteNode.left;
                    successorNode.left.parent=successorNode;
                    nodeToReturn=successorNode;
                }
                size--;
            }
            return nodeToReturn;
        }
        return null;
    }

    protected Node transplant(Node node1,Node node2){
        Node tempNode=node1;
        node1=node2;
        node2=tempNode;
        return tempNode;
    }




}
class Node {
        public Node(Integer value,Node parent,Node left,Node right ){
            super();
            this.value=value;
            this.parent=parent;
            this.left=left;
            this.right=right;
        }
        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public boolean idLeaf(){
            return left==null && right==null;
        }

    @Override
    public int hashCode() {
        final int prime=31;
        int result=1;
        result=prime * result +((null == value) ?:0:value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (this == null){
            return false;
        }
        if (getClass() !=obj.getClass()){
            return false;
        }
        Node other= (Node) obj;
        if (value == null){
            if (other.value !=null)
                return false;
        }else if (!value.equals(other.value))
            return false;
        return true;
    }


}
*/
