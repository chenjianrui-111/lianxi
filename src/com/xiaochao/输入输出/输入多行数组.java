package com.xiaochao.输入输出;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 输入多行数组 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (br.readLine() != null){
            String line1 = br.readLine();
            String line2 = br.readLine();

            int[] array1 = Arrays.stream(line1.replaceAll("[\\[\\]\\s]", "").split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] array2 = Arrays.stream(line2.replaceAll("[\\[\\]\\s]", "").split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int a = br.read();
            int b = br.read();
            System.out.println(a);
            System.out.println(b);
        }
    }
}
