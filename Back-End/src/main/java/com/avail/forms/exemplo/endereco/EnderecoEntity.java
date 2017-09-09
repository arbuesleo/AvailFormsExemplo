package com.avail.forms.exemplo.endereco;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.avail.availForms.annotation.CampoForm;
import com.avail.availForms.annotation.Form;
import com.avail.availForms.enuns.TipoCampo;
import com.avail.forms.exemplo.cidade.CidadeEntity;
import com.avail.forms.exemplo.utils.BaseEntity;

@Entity
@Table(name = "endereco")
@AttributeOverride(name = "id", column = @Column (name = "id_endereco"))
@Form(nomeEntidade="Endereço")
public class EnderecoEntity extends BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;
	
	@CampoForm(label = "Rua", listagem = true)
	@Column(nullable = false, length = 255)
	private String rua;
	
	@CampoForm(label = "Número", listagem = true, tipo = TipoCampo.NUMERICO)
	@Column(nullable = false)
	private Integer numero;
	
	@CampoForm(label = "CEP", listagem = true, tipo = TipoCampo.CEP)
	@Column(nullable = false)
	private Integer cep;
	
	@CampoForm(label = "Bairro", listagem = true)
	@Column(nullable = false)
	private String bairro;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	CidadeEntity cidade;
	
	public CidadeEntity getCidade() {
		return cidade;
	}

	public void setCidade(CidadeEntity cidade) {
		this.cidade = cidade;
	}

	public EnderecoEntity() {
	
	}
	
	public EnderecoEntity(String rua, Integer numero, Integer cep, String bairro, CidadeEntity cidade) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep.toString();
	}
	
	public void setCep(Integer cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
