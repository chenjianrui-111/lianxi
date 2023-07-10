package com.daimasuixianglu.paixu;

import java.util.ArrayList;

public class insertinterval {
    public class Interval{
        int start;
        int end;
        public Interval(int start,int end){
            this.start=start;
            this.end=end;
        }
    }
    public ArrayList<Interval> insert (ArrayList<Interval> intervals, Interval newInterval) {
        // write code here
        ArrayList<Interval> res=new ArrayList<>();
        if (intervals.size() == 0){
            res.add(newInterval);
            return res;
        }
        for (int i = 0; i <intervals.size(); i++) {
            Interval tmp=intervals.get(i);
            if (newInterval.start <= tmp.end){
                newInterval.start = Math.min(newInterval.start,tmp.start);
                break;
            }else {
                res.add(tmp);
            }
        }
        int j=intervals.size() - 1;
        for (;j>=0;j--){
            Interval tmp=intervals.get(j);
            if (newInterval.end >= tmp.start){
                newInterval.end = Math.max(newInterval.end,tmp.end);
                break;
            }
        }
        res.add(newInterval);
        for(int k = j + 1;k < intervals.size(); k++){
            res.add(intervals.get(k));
        }
        return res;
    }
}
