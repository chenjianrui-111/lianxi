package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**对整数数组,把前7个元素人栈如果不足7个,就把所有元素人栈)，然后把 4 个元素出(如果不足 4 个,就把所有元素出
 栈).将栈里剩余的元素 以栈顶为开始以栈底为结束,作为一个数组返回
 * 输入：[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
 * 输出：[3, 2, 1]
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] splits = line.substring(1, line.length() - 1).split(",");
        int[] arr = new int[splits.length];
        for (int i = 0; i < splits.length; i++) {
            arr[i] = Integer.parseInt(splits[i].trim().toString());
        }
        List<Integer> list = processArray(arr);
        int[] arr2 = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr2[i] = list.get(i).intValue();
        }
        int i = lastRemainingChildIndex(arr2);
        int i2 = processInteger(i);
        System.out.println(i2);
    }

    public static List<Integer> processArray(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        // 入栈前7个元素或者所有元素
        for (int i = 0; i < Math.min(7, arr.length); i++) {
            stack.push(arr[i]);
        }

        // 出栈4个元素或者所有元素
        for (int i = 0; i < Math.min(4, stack.size()); i++) {
            stack.pop();
        }

        // 从栈顶到栈底构成新数组并返回
        List<Integer> result = new ArrayList<>(stack);
        return result;
    }

    public static int lastRemainingChildIndex(int[] children) {
        if (children.length < 1) {
            return -1;
        }

        List<Integer> childList = new ArrayList<>();
        for (int i = 0; i < children.length; i++) {
            childList.add(i);
        }

        int index = 0;
        while (childList.size() > 1) {
            index = (index + 4) % childList.size(); // 报数到5的小朋友的下标
            childList.remove(index); // 将报数到5的小朋友移出圈
        }
        return childList.get(0); // 返回最后留下来的小朋友的下标
    }

    public static int processInteger(int number) {
        int absNumber = Math.abs(number);
        int newNumber = absNumber < 62 ? 62 : absNumber;

        int sum;
        do {
            sum = 0;
            while (newNumber > 0) {
                sum += newNumber % 10;
                newNumber /= 10;
            }
            newNumber = sum;
        } while (sum >= 10);

        return sum;
    }
}
