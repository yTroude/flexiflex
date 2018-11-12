package com.m2i.flexiflex;

import org.mindrot.jbcrypt.BCrypt;

public class JBCryptApplication {

    public static void main(String[] args) {

        String myPassword = "MyPassword";
        System.out.println("Mon mot de passe est: " + myPassword);
        String hashed = BCrypt.hashpw(myPassword, BCrypt.gensalt());
        System.out.println("Je conserve en base de données une string qui contient la clé et le hash: " + hashed);

        String testPassword1 = "MyPassword";
        String testPassword2 = "NotMyPassword";
        System.out.println("Je vérifie ensuite qu'un password donné, correspond au hashage gardé en base de donneés");
        System.out.println("Test du password: \"" + testPassword1 + "\":" + BCrypt.checkpw(testPassword1,hashed));
        System.out.println("Test du password: \"" + testPassword2 + "\":" + BCrypt.checkpw(testPassword2,hashed));


    }
}
