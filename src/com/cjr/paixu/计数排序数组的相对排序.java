package com.cjr.paixu;

/**
 * 具体地，我们使用一个长度为 1001下标从（下标从 0到 1000）的数组 frequency，记录每一个元素在数组 arr1
 中出现的次数。随后我们遍历数组 arr2
当遍历到元素 x 时，我们将 frequency[x] 个 x 加入答案中，并将 frequency[x] 清零。当遍历结束后，所有在 arr2
 *   中出现过的元素就已经有序了。
 * 此时还剩下没有在 arr2
 * 中出现过的元素，因此我们还需要对整个数组 frequency 进行一次遍历。
 * 当遍历到元素 x时，如果frequency[x] 不为 0，我们就将 frequency[x] 个 x 加入答案中。
 */
public class 计数排序数组的相对排序 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int upper=0;
        for (int x:arr1){
            upper=Math.max(upper,x);
        }
        int [] frequency=new int[upper+1];
        //记录每一个元素在数组 arr1
        // 中出现的次数
        for (int x:arr1){
            ++frequency[x];
        }
        //遍历数组 arr2
        //当遍历到元素 x 时，我们将 frequency[x] 个 x 加入答案中
        int[]ans=new int[arr1.length];
        int index=0;
        for (int x:arr2){
          for (int i=0;i<frequency[x];i++){
              ans[index++]=x;
          }
          //并将 frequency[x] 清零
            frequency[x]=0;
        }
        //因此我们还需要对整个数组 frequency 进行一次遍历。
        //当遍历到元素 x时，如果frequency[x] 不为 0，我们就将 frequency[x] 个 x 加入答案中
        for (int x=0;x<=upper;x++){
            for (int i = 0;i <frequency[x] ; i++) {
                ans[index++]=x;
            }
        }
      return ans;
    }
}
