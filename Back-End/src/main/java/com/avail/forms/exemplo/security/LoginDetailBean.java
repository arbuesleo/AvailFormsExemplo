package com.avail.forms.exemplo.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.avail.forms.exemplo.fileBase64.FileBase64;
import com.avail.forms.exemplo.utils.BaseBean;


public class LoginDetailBean extends BaseBean implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private final String name;

	private final String email;

	private final String password;

	private final Set<String> roles;
	
	private final FileBase64 imagemPerfil;
	
	private Long idEmpresaLogada;
	
	private String nomeEmpresaLogada;

	public LoginDetailBean(String name, String email, String passwordHash, FileBase64 imagem) {
		this.name = name;
		this.email = email;
		this.password = passwordHash;
		this.roles = new HashSet<String>();
		this.imagemPerfil = imagem;
	}

	public Set<String> getRoles() {
		return this.roles;
	}

	public void addRole(String role) {
		this.roles.add(role);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<String> roles = this.getRoles();

		if (roles == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return authorities;
	}

	public FileBase64 getImagemPerfil() {
		return imagemPerfil;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	public String getEmail() {
		return email;
	}

	public Long getIdEmpresaLogada() {
		return idEmpresaLogada;
	}

	public void setIdEmpresaLogada(Long idEmpresaLogada) {
		this.idEmpresaLogada = idEmpresaLogada;
	}

	public String getNomeEmpresaLogada() {
		return nomeEmpresaLogada;
	}

	public void setNomeEmpresaLogada(String nomeEmpresaLogada) {
		this.nomeEmpresaLogada = nomeEmpresaLogada;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
