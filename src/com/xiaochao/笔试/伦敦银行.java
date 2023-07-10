package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class 伦敦银行 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Double count = 0.0;
        Double day = 0.0;
        String line;
        while((line = br.readLine()) != null){
        // 注意 hasNext 和 hasNextLine 的区别
            String[] split = line.split("% ");
            double a = Double.parseDouble(split[0]);
            double b = Double.parseDouble(split[1]);
            count += a * b;
            day += b;
        }
        double ans =count / day;
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(ans)+"%");
    }
}
/***
 * 2.41% 1
 * 2.42% 1
 * 2.45% 1
 * 2.43% 1
 * 2.41% 3
 *
 * 2.42%
 ***/
