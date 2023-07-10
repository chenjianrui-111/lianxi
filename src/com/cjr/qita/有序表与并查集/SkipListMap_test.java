package com.cjr.qita.有序表与并查集;

import java.util.ArrayList;

public class SkipListMap_test {

    //跳表的定义
    public static class SkipListNode<K extends Comparable<K>,V>{
        public K key;
        public V val;
        //0 nextNodes.get(0)
        public ArrayList<SkipListNode<K,V>> nextNodes;

        public SkipListNode(K k, V v){
            key=k;
            val=v;
            nextNodes=new ArrayList<SkipListNode<K,V>>();
        }

        public boolean isKeyLess(K otherKey){
            return otherKey !=null &&(key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey){
            return (key == null && otherKey == null)
                    || (key !=null && otherKey !=null && key.compareTo(otherKey) == 0);
        }

    }

    public static class SkipListMap<K extends Comparable<K> , V>{
        private static final double PROBABILITY=0.5;
        private  SkipListNode<K,V> head;
        private int size;
        private int maxLevel;

        public SkipListMap(){
            head=new SkipListNode<K,V>(null,null);
            head.nextNodes.add(null);
            size=0;
            maxLevel=0;
        }

        //从最高层开始，一路找下去
        //最终，找到第0层的<key的最右的节点
        private SkipListNode<K,V> mostRightLessNodeInTree(K key){
            if (key == null){
                return null;
            }
            int level=maxLevel;
            SkipListNode<K,V> cur=head;
            while (level >= 0){
                //从上层跳下层
                cur=mostRightLessNodeInTree(key,cur,level--);
            }
            return cur;
        }

        //在level层里从左往右移动找< key的最后一个节点
        private SkipListNode<K,V> mostRightLessNodeInTree(K key,SkipListNode<K,V> cur,int level){
            SkipListNode<K,V> next=cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)){
                cur=next;
                next=cur.nextNodes.get(level);
            }
            return cur;
        }

        public boolean containsKey(K key){
            if (key == null){
                return false;
            }
            SkipListNode<K,V> less=mostRightLessNodeInTree(key);
            SkipListNode<K,V> next=less.nextNodes.get(0);
            return next !=null && next.isKeyEqual(key);
        }

        public void put(K key,V value){
            if (key == null){
                return;
            }
            SkipListNode<K,V> less=mostRightLessNodeInTree(key);
            SkipListNode<K,V> find=less.nextNodes.get(0);
            if (find !=null && find.isKeyEqual(key)){
                find.val=value;
            }else {
                size++;
                int newNodeLevel=0;
                while (Math.random() < PROBABILITY){
                    newNodeLevel++;
                }
                while (newNodeLevel > maxLevel){
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K,V> newNode=new SkipListNode<>(key,value);
                for (int i = 0; i <= newNodeLevel ; i++) {
                    newNode.nextNodes.add(null);
                }
                int level=maxLevel;
                SkipListNode<K,V> pre=head;
                while (level >=0){
                    pre=mostRightLessNodeInTree(key,pre,level);
                    if (level <= newNodeLevel){
                        newNode.nextNodes.set(level,pre.nextNodes.get(0));
                        pre.nextNodes.set(level,newNode);
                    }
                    level--;
                }
            }
        }

        public V get(K key){
            if (key == null){
                return null;
            }
            SkipListNode<K,V> less=mostRightLessNodeInTree(key);
            SkipListNode<K,V> next=less.nextNodes.get(0);
            return next !=null && next.isKeyEqual(key) ? next.val: null;
        }

//        public void  remove(K key){
//            if (containsKey(key)){
//                size--;
//                int level=maxLevel;
//                SkipListNode<K,V> pre=head;
//                while (level >= 0){
//                    pre=mostRightLessNodeInTree();
//                }
//
//            }
//        }
    }
}
