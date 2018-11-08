package com.m2i.flexiflex;

import com.m2i.flexiflex.service.Cryptor;

public class CryptorApplication {

	public static void main(String[] args) {
		String emailRef = "flexiflex.emailvalidation@gmail.com";
		String emailTest1 = "flexiflex.emailvalidation@gmail.com";
		String emailTest2 = "nimportequoi@quelquepart.com";

		String hash = Cryptor.getSHA256(emailRef);
		System.out.println("L'email: " + emailRef + " est hashé par la méthode static Cryptor.getSHA256 pour donner:" + hash);

		System.out.println("La méthode Cryptor.verifySHA256 vérifie que le mail \"" + emailTest1 + "\" est " + (Cryptor.verifySHA256(emailTest1, hash)?" bon":"le mauvais"));
		System.out.println("La méthode Cryptor.verifySHA256 vérifie que le mail \"" + emailTest2 + "\" est " + (Cryptor.verifySHA256(emailTest2, hash)?"bon":"le mauvais"));
	}
}
