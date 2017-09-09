package com.avail.forms.exemplo.cidade;

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
import com.avail.forms.exemplo.estado.EstadoEntity;
import com.avail.forms.exemplo.utils.BaseEntity;

@Entity
@Table(name = "cidade")
@AttributeOverride(name = "id", column = @Column (name = "id_cidade"))
@Form(nomeEntidade = "Cidade")
public class CidadeEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "nome_cidade", nullable = false)
	@CampoForm(label = "Nome", listagem = true)
	private String nomeCidade;
	
	@CampoForm(label = "Numero Habitantes", tipo = TipoCampo.NUMERICO)
	@Column(name = "numero_habitantes", nullable = false)
	private Integer numeroHabitantes;
	
	@CampoForm(label = "Cod. IBGE", tipo = TipoCampo.NUMERICO, listagem = true)
	@Column(name = "codigo_ibge", nullable = false)
	Integer codigoIbge;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="estado_id")
	private EstadoEntity estado;
	
	
	
	public CidadeEntity() {
	}
	
	
	
	public CidadeEntity(String nomeCidade, Integer numeroHabitantes, Integer codigoIbge, EstadoEntity estado) {
		super();
		this.nomeCidade = nomeCidade;
		this.numeroHabitantes = numeroHabitantes;
		this.codigoIbge = codigoIbge;
		this.estado = estado;
	}



	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public Integer getNumeroHabitantes() {
		return numeroHabitantes;
	}
	public void setNumeroHabitantes(Integer numeroHabitantes) {
		this.numeroHabitantes = numeroHabitantes;
	}
	public Integer getCodigoIbge() {
		return codigoIbge;
	}
	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	public EstadoEntity getEstado() {
		return estado;
	}
	public void setEstado(EstadoEntity estado) {
		this.estado = estado;
	}
	
}
