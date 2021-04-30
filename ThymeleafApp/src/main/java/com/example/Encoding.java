package com.example;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class Encoding {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// user1 のパスワード
		System.out.printf(new Pbkdf2PasswordEncoder().encode("demo"));

	}

}
