package com.xiaochao.笔试;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 中心老城区有很多奇施的停车场，假设现在有一条单出入口的胡路用来做停车场，注意:1.单出入口;2.假设胡同路宽度只够停1辆小车(不可并排停车)。
 * 现在给出当天车辆到停车场的顺字数列A(小车用正整数标只别)和当天停车场出车顺序数列8。请根撮A数列顺序，判断8数列的出车顺序是否合理的，如B是合理的出车顺序，则输出true; 如B是不可能存在的出车顺序，则城出false。
 * 例1
 * 输入:
 * [1,2,3,4]
 * [4,3,2,1]
 * 输出: true
 */
public class demo01 {

    public static boolean isValidExitOrder(int[] enterOrder, int[] exitOrder) {
        Stack<Integer> stack = new Stack<>();
        int enterIndex = 0;
        int exitIndex = 0;
        while (exitIndex < exitOrder.length) {
            if (!stack.isEmpty() && stack.peek() == exitOrder[exitIndex]) {
                stack.pop();
                exitIndex++;
            } else if (enterIndex < enterOrder.length) {
                stack.push(enterOrder[enterIndex]);
                enterIndex++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1 = br.readLine();
        String line2 = br.readLine();

        int[] array1 = Arrays.stream(line1.replaceAll("[\\[\\]\\s]", "").split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] array2 = Arrays.stream(line2.replaceAll("[\\[\\]\\s]", "").split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        Boolean validExitOrder = isValidExitOrder(array1, array2);
        System.out.println(validExitOrder);
        br.close();
    }
}


