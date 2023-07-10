package com.xiaochao.笔试;

import java.math.BigInteger;

public class RSA解密 {

    public int Decrypt(int encryptedNumber, int decryption, int number) {
        BigInteger mod = new BigInteger(encryptedNumber + "").pow(decryption).mod(new BigInteger(number + ""));
        int res = Integer.parseInt(mod.toString());
        return res;
    }
}
