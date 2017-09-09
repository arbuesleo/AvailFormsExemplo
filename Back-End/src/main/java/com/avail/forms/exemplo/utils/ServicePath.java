package com.avail.forms.exemplo.utils;

public final class ServicePath {
	
	public static final String ALL = "/**";
	
	public static final String ROOT_PATH = "/api";
	
	public static final String PUBLIC_ROOT_PATH = ROOT_PATH + "/public";
	
	public static final String PRIVATE_ROOT_PATH = ROOT_PATH + "/private";
	
	public static final String USER_PATH = PRIVATE_ROOT_PATH + "/usuario";
	
	public static final String PERMISSAO_PRIVATE_PATH = PRIVATE_ROOT_PATH + "/permissao";
	
	public static final String ESTADO_PRIVATE_PATH = PRIVATE_ROOT_PATH + "/estado";
	
	public static final String CIDADE_PRIVATE_PATH = PRIVATE_ROOT_PATH + "/cidade";
	
	public static final String FILTRO_PRIVATE_PATH = PRIVATE_ROOT_PATH + "/filtro";
	
	///////////////////////////////////////////////////////////////
	// PUBLIC PATHS
	///////////////////////////////////////////////////////////////

	public static final String LOGIN_PATH = PUBLIC_ROOT_PATH + "/login";

	public static final String LOGOUT_PATH = PUBLIC_ROOT_PATH + "/logout";
	
	public static final String USER_PUBLIC_PATH = PUBLIC_ROOT_PATH + "/usuario";
	
}
