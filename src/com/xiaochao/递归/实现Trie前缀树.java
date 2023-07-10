package com.xiaochao.递归;

public class 实现Trie前缀树 {

    TrieSet1 set1= new TrieSet1();
    public void insert(String word) {
        set1.add(word);
    }

    public boolean search(String word) {
        return set1.contains(word);
    }

    public boolean startsWith(String prefix) {
        return set1.hasKeyWithPrefix(prefix);
    }
}

class TreeMap1<V> {

    private static final int R = 256;
    private int size = 0;
    private TreeNode<V> root =null;

    public static class TreeNode<V>{
        V val = null;
        TreeNode<V> [] children = new TreeNode[R];
    }
    /***** 增/改 *****/
    public void put(String key,V val){
        if (!containskey(key)){
            size++;
        }
        root = put(root,key,val,0);
    }

    public TreeNode<V> put(TreeNode<V> node,String key,V val,int i){
        if (node == null){
            // 如果树枝不存在，新建
            node = new TreeNode<>();
        }
        if (i == key.length()) {
            // key 的路径已插入完成，将值 val 存入节点
            node.val = val;
            return node;
        }
        char c =key.charAt(i);
        node.children[c] = put(node.children[c],key,val,i+1);
        return node;
    }

    // 判断是和否存在前缀为 prefix 的键
    public boolean hasKeyWithPrefix(String prefix) {
        // 只要能找到一个节点，就是存在前缀
        return getNode(root, prefix) != null;
    }

    public boolean containskey(String key){
        return get(key) != null;
    }
    public V get(String key){
        TreeNode<V> x =getNode(root,key);
        if (x == null || x.val == null){
            return null;
        }
        return x.val;
    }

    // 从节点 node 开始搜索 key，如果存在返回对应节点，否则返回 null
    public TreeNode<V> getNode(TreeNode<V> node,String key){
        TreeNode<V> p =node;
        for (int i = 0; i <key.length() ; i++) {
            if (p ==null){
                return null;
            }
            char c = key.charAt(i);
            p = p.children[c];
        }
        return p;
    }
    public int size(){
        return size;
    }
}
class TrieSet1{
    private  TreeMap1<Object> map = new TreeMap1<>();
    // 在集合中添加元素 key
    public void add(String key) {
        map.put(key, new Object());
    }

    /***** 查 *****/

    // 判断元素 key 是否存在集合中
    public boolean contains(String key) {
        return map.containskey(key);
    }

    // 判断集合中是否存在前缀为 prefix 的元素
    public boolean hasKeyWithPrefix(String prefix) {
        return map.hasKeyWithPrefix(prefix);
    }

    // 返回集合中元素的个数
    public int size() {
        return map.size();
    }
}
