package com.xiaochao.树.线段树;

/**
 * 给你一个数组 nums ，请你完成两类查询。
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * 示例 1：
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 104
 */
class NumArray {
    int[] tree;
    int n;
    int[] a;
    private void buildTree(int[] nums,int node,int start,int end){
        if (start == end){
            tree[node] = nums[start];
        }else {
            int mid = start + (end - start) / 2;
            buildTree(nums, node * 2, start, mid);
            buildTree(nums, node * 2 + 1, mid + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }
    public NumArray(int[] nums) {
        n = nums.length;
        if (n == 0) return;
        a = new int[n + 1];
        tree = new int[(int) Math.pow(2,n)];
        System.arraycopy(nums,0,a,1,n);
        buildTree(a,1,1,n);
    }

    public void updateTree(int node,int start,int end,int i,int val) {
        if (start == end){
            tree[node] = val;
            a[i] = val;
        }else {
            int mid = start + (end - start) / 2;
            if (start <= i && i <= mid){
                updateTree(node * 2,start,mid,i,val);
            }else {
                updateTree(node * 2 + 1, mid + 1, end, i, val);
            }
        }
    }

    public void update(int i,int val){
        updateTree(1,1,n,i + 1,val);
    }

    public int sumRange(int i,int j){
        return query(1,1,n,i + 1,j + 1);
    }

    private int query(int node,int start,int end,int i,int j){
        if (j < start || i > end) return 0;
        if (i <= start && end <= j) return tree[node];
        int mid = start + (end - start) / 2;
        return query(node * 2,start,mid,i,j)
                + query(node * 2 + 1,mid + 1,end,i,j);
    }
}
class NumArray2 {

    class TreeNode{
        int sum;
        int start, end;
        TreeNode left, right;
        public TreeNode(int s,int e){
            left = null;
            right = null;
            start = s;
            end = e;
            this.sum = 0;
        }
    }
    TreeNode root = null;
    private TreeNode buildTree(int[] nums,int start,int end){
        if (start > end) return null;
        TreeNode res = new TreeNode(start,end);
        if (start == end){
            res.sum = nums[start];
        }else {
            int mid = start + (end - start) / 2;
            res.left = buildTree(nums, start, mid);
            res.right = buildTree(nums, mid + 1, end);
            res.sum = res.left.sum + res.right.sum;
        }
        return res;
    }
    public NumArray2(int[] nums) {
        root = buildTree(nums,0,nums.length - 1);
    }

    private void update(TreeNode root,int i,int val){
        if (root.start == root.end){
            root.sum = val;
        }else {
            int mid = root.start + (root.end - root.start) / 2;
            if (i <= mid){
                update(root.left,i,val);
            }else {
                update(root.right,i,val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }
    public void update(int index, int val) {
        update(root,index,val);
    }
    private int query(TreeNode root,int left,int right){
        if (root.start == left && root.end == right){
            return root.sum;
        }else {
            int mid = root.start + (root.end - root.start) / 2;
            if (right <= mid){
                return query(root.left, left, right);
            }else if (left >= mid + 1){
                return query(root.right, left, right);
            }else {
                return query(root.left, left, mid) + query(root.right,mid + 1, right);
            }
        }
    }

    public int sumRange(int left, int right) {
        return query(root,left,right);
    }
}
