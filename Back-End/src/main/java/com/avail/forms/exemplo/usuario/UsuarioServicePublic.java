package com.avail.forms.exemplo.usuario;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avail.forms.exemplo.permissao.PermissaoEntity;
import com.avail.forms.exemplo.permissao.PermissaoRepository;
import com.avail.forms.exemplo.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.USER_PUBLIC_PATH)
public class UsuarioServicePublic {
		
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired 
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	private final Logger LOGGER = Logger.getLogger(getClass());	

	
	@RequestMapping(path = "/addMobile", method = RequestMethod.POST)
	public ResponseEntity<?> insert (@RequestBody @Validated UsuarioEntity user, Errors errors){	
		
		this.LOGGER.info("Setando permissao default para usuario mobile.");
		
		List<PermissaoEntity> permissoes = new ArrayList<>();
		
		permissoes.add(permissaoRepository.findByRole("USER"));
		
		user.setPermissoes(permissoes);
		
		return usuarioService.insert(user, errors);
	}

}
