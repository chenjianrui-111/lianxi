package com.leixing.二分;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 二分 + 倍增乘法
 由于题目限定了我们不能使用乘法、除法和 mod 运算符。
 我们可以先实现一个「倍增乘法」，然后利用对于 x 除以 y，结果 x / y 必然落在范围 [0, x] 的规律进行二分。
 */
public class 两数相除 {
    public int divide(int a, int b) {
        long x = a, y = b;
        boolean isNeg = false;
        if ((x > 0 && y < 0) || (x < 0 && y > 0)) isNeg = true;
        if (x < 0) x = -x;
        if (y < 0) y = -y;
        long l = 0, r = x;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (mul(mid, y) <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        long ans = isNeg ? -l : l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int)ans;
    }
    long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
            k >>= 1;
            a += a;
        }
        return ans;
    }
}
/**
 *时间复杂度：对 x 采用的是二分策略。复杂度为 O(logn)
 * 空间复杂度：O(1)
 * 总结
 * 这道题的解法，主要涉及的模板有两个。
 * 一个是「二分」模板，一个是「快速乘法」模板。都是高频使用的模板。
 * 其中「二分」模板其实有两套，主要是根据 check(mid) 函数为 true 时，需要调整的是 l 指针还是 r 指针来判断。
 * 当 check(mid) == true 调整的是 l 时：计算 mid 的方式应该为 mid = l + r + 1 >> 1：
 * Java
 * long l = 0, r = 1000009;
 * while (l < r) {
 *     long mid = l + r + 1 >> 1;
 *     if (check(mid)) {
 *         l = mid;
 *     } else {
 *         r = mid - 1;
 *     }
 * }
 * 当 check(mid) == true 调整的是 r 时：计算 mid 的方式应该为 mid = l + r >> 1：
 * Java
 * long l = 0, r = 1000009;
 * while (l < r) {
 *     long mid = l + r >> 1;
 *     if (check(mid)) {
 *         r = mid;
 *     } else {
 *         l = mid + 1;
 *     }
 * }
 * 另外一个是「快速乘法」模板，采用了倍增的思想：
 * Java
 * long mul(long a, long k) {
 *     long ans = 0;
 *     while (k > 0) {
 *         if ((k & 1) == 1) ans += a;
 *         k >>= 1;
 *         a += a;
 *     }
 *     return ans;
 * }
 * 关于「二分」模板的说明
 * 为啥修改左边指针 l 的时候要进行 +1 操作？
 * 「模板一」的 +1 操作主要是为了避免发生「死循环」，因为 >> 和 直接使用 / 一样，都属于「下取整」操作。
 * 考虑 l = 0, r = 1 的简单情况，如果不 +1 的话，l + r >> 1 等于 0 + 1 / 2，l 仍然是 0，陷入死循环。
 * 为啥模板不考虑 int 溢出问题？
 * 事实上，二分模板确实有考虑 int 溢出的写法，评论区我也有提到，但是一般我们不会去用那样的模板，因为太难记了。
 * 而且如果数据范围确实存在 int 溢出的情况，正确的做法是使用 long 数据结构，因为你无法确保只会在「二分」逻辑中进行下标运算，这里改用模板，在别的地方也可能会溢出。
 * 使用 long 的说明
 * 评论区有小伙伴提醒三叶：假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^{31}−2
 * 31
 *  , 2^{31} − 12
 * 31
 *  −1]
 * 我认为这个提示有两层理解含义：
 * 实现过程中完全不能使用 long
 * 实现过程不限制使用 long，只是解释为什么某些情况下需要我们返回 2^{31}2
 * 31
 *   − 1
 * 在本题，我是按照第二种解释方式进行理解。
 * 当然也可以按照第一种解释方式进行理解，在 7. 整数反转(简单) 中，我就提供了实现过程中不使用 long 的「完美解决」方案。可以看看 ~
 * 不使用 long 其实十分简单，只需要将越界判断放到循环里即可，建议你动手试试 ~
 */
