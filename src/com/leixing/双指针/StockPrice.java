package com.leixing.双指针;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳 和该时间点股票对应的 价格 。
 *
 * 不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。
 * 如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正 前一条错误的记录。
 *
 * 请你设计一个算法，实现：
 * 更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将 更正 之前的错误价格。
 * 找到当前记录里 最新股票价格 。最新股票价格 定义为时间戳最晚的股票价格。
 * 找到当前记录里股票的 最高价格 。
 * 找到当前记录里股票的 最低价格 。
 * 请你实现 StockPrice 类：
 *
 * StockPrice() 初始化对象，当前无股票价格记录。
 * void update(int timestamp, int price) 在时间点 timestamp 更新股票价格为 price 。
 * int current() 返回股票 最新价格 。
 * int maximum() 返回股票 最高价格 。
 * int minimum() 返回股票 最低价格 。
 * 解題思路：
 * 容易想到我们需要使用「哈希表」来记录 {时间:价格} 的映射关系。
 * 关于 current 操作，我们可以维护一个最大的时间戳 cur，在调用 current 的时候直接 O(1) 查得结果。
 * 然后考虑解决 update 操作中对相同时间点的更新问题，我们可以使用 TreeMap（红黑树）来解决该问题。
 * 以 {价格:该价格对应的时间点数量} 的 KV 形式进行存储，key 按照「升序」进行排序。
 * 然后对传入的 timestamp 是否已经被记录（是否已经存在哈希表中）进行分情况讨论：
 * 传入的 timestamp 未被记录，直接更新哈希表和 TreeMap；
 * 传入的 timestamp 已被记录，此时需要先从哈希表取出旧价格 old，然后用旧价格对 TreeMap 进行修改
 * （如果该价格只有一个时间点，将该价格直接从 TreeMap 中移除；
 * 若有多个时间点，则对该价格对应的时间点数量进行减一操作），然后再使用传入的新价格 price 更新哈希表和 TreeMap。
 * minimum 和 maximum 操作则只需要取得 TreeMap 的首尾 Key 即可。
 */
public class StockPrice {
    Map<Integer,Integer> map=new HashMap<>();
    TreeMap<Integer,Integer> ts=new TreeMap<>();
    int cur;

    public StockPrice() {

    }

    public void update(int timestamp, int price) {
     cur=Math.max(cur,timestamp);
     if (map.containsKey(timestamp)){
         int old=map.get(timestamp);
         int cnt=ts.get(old);
         if (cnt == 1)
             ts.remove(old);
         else
             ts.put(old,cnt-1);
     }
     map.put(timestamp,price);
     ts.put(price,ts.getOrDefault(price,0)+1);
    }

    public int current() {
        return map.get(cur);
    }

    public int maximum() {
        return ts.lastKey();
    }

    public int minimum() {
      return ts.firstKey();
    }
}
