package com.avail.forms.exemplo.erros;

public class GenericErros extends RuntimeException{
	private static final long serialVersionUID = 1869300553614629710L;

	public GenericErros(String mensagem) {
		super(mensagem);
	}
	
	public GenericErros(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
