package com.avail.forms.exemplo.estado;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.avail.availForms.annotation.CampoForm;
import com.avail.availForms.annotation.Form;
import com.avail.forms.exemplo.utils.BaseEntity;

@Entity
@Table(name = "estado")
@AttributeOverride(name = "id", column = @Column (name = "id_estado"))
@Form(nomeEntidade = "Estado")
public class EstadoEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@CampoForm(label = "Nome", listagem = true)
	@Column(nullable = false)
	private String nome;
	
	@CampoForm(label = "Sigla", listagem = true)
	@Column(nullable = false, length = 4)
	private String sigla;
	
	
	public EstadoEntity() {	
	}
	
	public EstadoEntity(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
