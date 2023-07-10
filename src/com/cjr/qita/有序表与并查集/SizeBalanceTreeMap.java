package com.cjr.qita.有序表与并查集;

public class SizeBalanceTreeMap {

    public static class SBTNode<K extends Comparable<K>,V>{
        public K key;
        public V value;
        public SBTNode<K,V> l;
        public SBTNode<K,V> r;
        //不同的key的数量
        public int size;

        public SBTNode(K key,V value){
            this.key=key;
            this.value=value;
            size=1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>,V>{
        private SBTNode<K,V> root;

        private SBTNode<K,V> rightRotate(SBTNode<K,V> cur){
            SBTNode<K,V> leftNode=cur.l;
            cur.l=leftNode.r;
            leftNode.r=cur;
            leftNode.size=cur.size;
            cur.size=(cur.l !=null ?cur.l.size:0)+(cur.r !=null?cur.r.size:0)+1;
            return leftNode;
        }

        private SBTNode<K,V> leftRotate(SBTNode<K,V> cur){
            SBTNode<K,V> rightNode=cur.r;
            cur.r=rightNode.l;
            rightNode.l=cur;
            rightNode.size=cur.size;
            cur.size=(cur.l !=null ?cur.l.size:0)+(cur.r !=null?cur.r.size:0)+1;
            return rightNode;
        }

        private SBTNode<K,V> maintain(SBTNode<K,V> cur){
            if (cur == null){
                return  null;
            }
            //LL违规
            if (cur.l !=null &&cur.l.l !=null &&cur.r !=null &&cur.l.l.size>cur.r.size){
                cur=rightRotate(cur);
                cur.r=maintain(cur.r);
                cur=maintain(cur);
            }else if (cur.l !=null &&cur.l.r !=null &&cur.r !=null &&cur.l.r.size>cur.r.size){
                cur.l=leftRotate(cur.l);
                cur=rightRotate(cur);
                cur.l=maintain(cur.l);
                cur=maintain(cur);
            }else if (cur.r !=null &&cur.r.r !=null &&cur.l !=null &&cur.r.r.size>cur.l.size){
                cur=leftRotate(cur);
                cur.l=maintain(cur.l);
                cur=maintain(cur);
            }else if (cur.r !=null &&cur.r.l !=null &&cur.l !=null &&cur.r.l.size>cur.l.size){
                cur.r=rightRotate(cur.r);
                cur=leftRotate(cur);
                cur.l=maintain(cur.l);
                cur.r=maintain(cur.r);
                cur=maintain(cur);
            }
            return cur;

        }

        private SBTNode<K,V> findLastIndex(K key){
            SBTNode<K,V> pre=root;
            SBTNode<K,V> cur=root;
            while (cur !=null){
                pre=cur;
                if (key.compareTo(cur.key)==0){
                    break;
                }else if (key.compareTo(cur.key) <0){
                    cur=cur.l;
                }else {
                    cur=cur.r;
                }
            }
            return cur;
        }

        private SBTNode<K,V> findLastNoSmallIndex(K key){
            SBTNode<K,V> ans=null;
            SBTNode<K,V> cur=root;
            while (cur !=null){
                if (key.compareTo(cur.key)==0){
                    ans=cur;
                    break;
                }else if (key.compareTo(cur.key)<0){
                    ans=cur;
                    cur=cur.l;
                }else {
                    ans=cur;
                    cur=cur.r;
                }
            }
            return ans;
        }

        private SBTNode<K,V> findLastNoBigIndex(K key){
            SBTNode<K,V> ans=null;
            SBTNode<K,V> cur=root;
            while (cur !=null){
                if (key.compareTo(cur.key)==0){
                    ans=cur;
                    break;
                }else if (key.compareTo(cur.key)<0){
                    cur=cur.l;
                }else {
                    ans=cur;
                    cur=cur.r;
                }
            }
            return ans;
        }

        //现在以cur为头的树上，加(key,value)这样的记录
        //加完之后，会对cur做检查调整
        //返回，调整完之后，整棵树的新头部
        private SBTNode<K,V> add(SBTNode<K,V> cur,K key,V value){
            if (cur == null){
                return new SBTNode<>(key,value);
            }else {
                cur.size++;
                if (key.compareTo(cur.key)<0){
                    cur.l=add(cur.l,key,value);
                }else {
                    cur.r=add(cur.r,key,value);
                }
                return maintain(cur);
            }
        }

        //在cur这棵树上，删掉key所代表的节点
        //返回新头部
        private SBTNode<K,V> delete(SBTNode<K,V> cur,K key){
            cur.size--;
            if (key.compareTo(cur.key) > 0){
                cur.r=delete(cur.r,key);
            }else if (key.compareTo(cur.key) < 0){
                cur.l=delete(cur.l,key);
            }else {//删掉cur
                if (cur.l ==null && cur.r== null){
                    cur=null;
                }else if (cur.l == null && cur.r!=null){
                    cur=cur.r;
                }else if (cur.l !=null &&cur.r==null){
                    cur=cur.l;
                }else {
                    SBTNode<K,V> pre=null;
                    SBTNode<K,V> des=cur.r;
                    des.size--;
                    while (des.l !=null){
                        pre=des;
                        des=des.l;
                        des.size--;
                    }
                    if (pre !=null){
                        pre.l=des.r;
                        des.r=cur.r;
                    }
                    des.l=cur.l;
                    des.size=des.l.size+(des.r ==null?0:des.r.size)+1;
                    cur=des;
                }
            }
            return cur;
        }

        public void put(K key,V value){
            if (key==null){
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K,V> lastNode=findLastIndex(key);
            if (lastNode !=null &&key.compareTo(lastNode.key)==0){
                lastNode.value=value;
            }else {
                root=add(root,key,value);
            }
        }

        public void  remove(K key){
            if (key == null){
                throw new RuntimeException("invalid parameter");
            }
            if (containsKey(key)){
                root=delete(root,key);
            }
        }

        public K getIndexKey(int index){
            if (index <0 || index >=this.size()){
                throw new RuntimeException("invalid parameter");
            }
            return getIndex(root,index+1).key;
        }

        public SBTNode<K,V> getIndex(SBTNode<K,V> cur,int kth){
            if (kth == (cur.l !=null ?cur.l.size :0) + 1){
                return cur;
            }else if (kth <=(cur.l !=null ?cur.l.size : 0)){
                return getIndex(cur.l,kth);
            }else {
                return getIndex(cur.r,kth - (cur.l !=null ? cur.l.size :0 ) -1);
            }
        }

        public int size(){
            return root == null ? 0:root.size;
        }

        public boolean containsKey(K key){
            if (key == null){
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K,V> lastNode=findLastIndex(key);
            return lastNode !=null && key.compareTo(lastNode.key) == 0? true:false;
        }

    }
}
