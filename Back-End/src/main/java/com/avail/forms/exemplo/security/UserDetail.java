package com.avail.forms.exemplo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.avail.forms.exemplo.permissao.PermissaoEntity;
import com.avail.forms.exemplo.usuario.UsuarioEntity;
import com.avail.forms.exemplo.usuario.UsuarioRepository;
import com.avail.forms.exemplo.utils.Situacao;

@Component
public class UserDetail implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioEntity user = this.usuarioRepository.findByEmailAndSituacao(email, Situacao.ativo);

		if (user == null) {
			throw new UsernameNotFoundException("Usuário com email \"" + email + "\" não encontrado");
		}

		LoginDetailBean login = new LoginDetailBean(user.getNome(), user.getEmail(), user.getSenha(), user.getImagemPerfil());

		for (PermissaoEntity permissao : user.getPermissoes()) {
			login.addRole(permissao.getRole());
		}

		return login;
	}

}