package com;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        System.out.println(compareVersion("1.0", "0.1")); // 输出: 1
        System.out.println(compareVersion("0.1.Alpha", "1.Final")); // 输出: -1
    }

    public static int compareVersion(String version1, String version2) {
        String[] parts1 = version1.split("\\.|\\-");
        String[] parts2 = version2.split("\\.|\\-");

        int length = Math.max(parts1.length, parts2.length);
        for (int i = 0; i < length; i++) {
            String part1 = i < parts1.length ? parts1[i] : "";
            String part2 = i < parts2.length ? parts2[i] : "";

            if (part1.matches("\\d+") && part2.matches("\\d+")) {
                int num1 = Integer.parseInt(part1);
                int num2 = Integer.parseInt(part2);

                if (num1 != num2) {
                    return num1 > num2 ? 1 : -1;
                }
            } else {
                int compareResult = part1.compareToIgnoreCase(part2);
                if (compareResult != 0) {
                    return compareResult > 0 ? 1 : -1;
                }
            }
        }

        return 0;
    }
}
