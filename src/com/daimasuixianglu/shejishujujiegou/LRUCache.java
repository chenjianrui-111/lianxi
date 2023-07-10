package com.daimasuixianglu.shejishujujiegou;

import java.util.HashMap;
import java.util.Map;

/**
 * 将 LRU 翻译成大白话就是：当不得不淘汰某些数据时（通常是容量已满），选择最久未被使用的数据进行淘汰。
 * 题目让我们实现一个容量固定的 LRUCache 。如果插入数据时，发现容器已满时，则先按照 LRU 规则淘汰一个数据，再将新数据插入，其中「插入」和「查询」都算作一次“使用”。
 * 可以通过 🌰 来理解，假设我们有容量为  的 LRUCache 和 测试键值对 [1-1,2-2,3-3] ，将其按照顺序进行插入 & 查询：
 * 插入 1-1，此时最新的使用数据为 1-1
 * 插入 2-2，此时最新使用数据变为 2-2
 * 查询 1-1，此时最新使用数据为 1-1
 * 插入 3-3，由于容器已经达到容量，需要先淘汰已有数据才能插入，这时候会淘汰 2-2，3-3 成为最新使用数据
 * 键值对存储方面，我们可以使用「哈希表」来确保插入和查询的复杂度为 。
 * 另外我们还需要额外维护一个「使用顺序」序列。
 * 我们期望当「新数据被插入」或「发生键值对查询」时，能够将当前键值对放到序列头部，这样当触发 LRU 淘汰时，只需要从序列尾部进行数据删除即可。
 * 期望在 O(1) 复杂度内调整某个节点在序列中的位置，很自然想到双向链表。
 * 具体的，我们使用哈希表来存储「键值对」，键值对的键作为哈希表的 Key，而哈希表的 Value 则使用我们自己封装的 Node 类，Node 同时作为双向链表的节点。
 * 插入：检查当前键值对是否已经存在于哈希表：
 * 没达到容量：插入哈希表，并将当前键值对所对应的 Node 节点调整到链表头部（refresh 操作）
 * 已达到容量：先从链表尾部找到待删除元素进行删除（delete 操作），然后再插入哈希表，并将当前键值对所对应的 Node 节点调整到链表头部（refresh 操作）
 * 如果存在，则更新键值对，并将当前键值对所对应的 Node 节点调整到链表头部（refresh 操作）
 * 如果不存在，则检查哈希表容量是否已经达到容量：
 * 查询：如果没在哈希表中找到该 Key，直接返回 ；如果存在该 Key，则将对应的值返回，并将当前键值对所对应的 Node 节点调整到链表头部（refresh 操作）
 * 一些细节：
 * 为了减少双向链表左右节点的「判空」操作，我们预先建立两个「哨兵」节点 head 和 tail。
 */
public class LRUCache {
    class Node{
        int k,v;
        Node l,r;
        Node(int _k,int _v){
            k=_k;
            v=_v;
        }
    }
    int n;
    Node head,tail;
    Map<Integer,Node> map;
    public LRUCache(int capacity){
        n=capacity;
        map=new HashMap<>();
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.r=tail;
        tail.l=head;
    }
    public int get(int key){
        if (map.containsKey(key)){
            Node node=map.get(key);
            refresh(node);
        }
        return -1;
    }
    public void put(int key,int value){
        Node node=null;
        if (map.containsKey(key)){
            node=map.get(key);
            node.v=value;
        }else {
            if (map.size() == n){
                Node del=tail.l;
                map.remove(del.k);
                delete(del);
            }
            node=new Node(key,value);
            map.put(key,node);
        }
        refresh(node);
    }
    // refresh 操作分两步：
    // 1. 先将当前节点从双向链表中删除（如果该节点本身存在于双向链表中的话）
    // 2. 将当前节点添加到双向链表头部
    void refresh(Node node) {
        delete(node);
        node.r = head.r;
        node.l = head;
        head.r.l = node;
        head.r = node;
    }
    // delete 操作：将当前节点从双向链表中移除
    // 由于我们预先建立 head 和 tail 两位哨兵，因此如果 node.l 不为空，则代表了 node 本身存在于双向链表（不是新节点）
    void delete(Node node) {
        if (node.l != null) {
            Node left = node.l;
            left.r = node.r;
            node.r.l = left;
        }
    }
}
