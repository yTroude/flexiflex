package com.m2i.flexiflex.service;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Cryptor {

    private static String salt="D36B32H34F31L31";

    public static String getSHA256(String input){

        String toReturn = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update((input+salt).getBytes("utf8"));
            toReturn = String.format("%040x", new BigInteger(1, messageDigest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public static boolean verifySHA256(String input, String hash) {
        return hash.equals(getSHA256(input));
    }
}
