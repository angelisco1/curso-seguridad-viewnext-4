package com.curso.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	
	public static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	//pw => a -> hashPassword -> 1=11=hd29h3d892h3doh23d8h293hd 
	
	//a, sal=1 -> hash11 -> aaah12
	//o, sal=1 -> hash11 -> otth39
	
	//hash -> cost&sal&hash
	
	//    $2a$10$mpAopqAnbVnxyZKDtCj.1u3n.11XxDhP5oSkoKSB1lxKHB1scxEKO
	
	
	public static boolean checkPassword(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}

}
