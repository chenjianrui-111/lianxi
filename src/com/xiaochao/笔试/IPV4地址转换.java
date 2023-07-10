package com.xiaochao.笔试;

public class IPV4地址转换 {
    public static void main(String[] args) {
        String ipStr = "192.168.0.1";
        boolean correctIp = isCorrectIp(ipStr);
        if (correctIp){
            long i = ipv4ToIntAndConvert(ipStr);
            System.out.println(i);
        }else {
            System.out.println("ipv4地址不合法");
        }
    }
    //转换为int方法
    public static long ipv4ToIntAndConvert(String ip) {
        String[] octets = ip.split("\\.");
        long result = ((long)Integer.parseInt(octets[0]) << 24) + ((long)Integer.parseInt(octets[1]) << 16)
                + ((long)Integer.parseInt(octets[2]) << 8) + (long)Integer.parseInt(octets[3]);
        // 判断转换后的10进制数字是否合法
        if (result < 0 || result > 4294967295L) {
            throw new IllegalArgumentException("IPv4地址转换为10进制数字不合法");
        }
        return result;
    }
    public static boolean isCorrectIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        if (ip.length()<7||ip.length()>15) {
            return false;
        }
        String[] octets = ip.split("\\.");
        if (octets.length != 4) {
            return false;
        }
        for (String octet : octets) {
            try {
                int value = Integer.parseInt(octet);
                if (value < 0 || value > 255) {
                    return false;
                }
                if (octet.length() > 1 && octet.charAt(0) == '0') {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
