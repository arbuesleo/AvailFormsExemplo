package com.avail.forms.exemplo.usuario;

import com.avail.forms.exemplo.security.LoginDetailBean;

public class UserHelper {
	
	public static boolean contemPermissao(String role, LoginDetailBean user){
		return user.getRoles().contains("ADMIN");
	}
}
