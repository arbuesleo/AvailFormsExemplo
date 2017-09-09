package com.avail.forms.exemplo.permissao;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.avail.availForms.annotation.CampoForm;
import com.avail.availForms.annotation.Form;
import com.avail.forms.exemplo.utils.BaseEntity;

@Entity
@Table(name = "permissao")
@AttributeOverride(name = "id", column = @Column (name = "id_permissao"))
@Form(nomeEntidade = "Permissão")
public class PermissaoEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@Column(length = 45, nullable = false, unique = true)
	@CampoForm(label = "Nome", listagem = true)
	private String role;

	public PermissaoEntity() {
		super();
	}

	public PermissaoEntity(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
