package com.avail.forms.exemplo.usuario;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.avail.availForms.annotation.CampoForm;
import com.avail.availForms.annotation.Form;
import com.avail.availForms.annotation.Image;
import com.avail.availForms.enuns.TipoCampo;
import com.avail.forms.exemplo.endereco.EnderecoEntity;
import com.avail.forms.exemplo.fileBase64.FileBase64;
import com.avail.forms.exemplo.permissao.PermissaoEntity;
import com.avail.forms.exemplo.utils.BaseEntity;
import com.avail.forms.exemplo.utils.Situacao;
import com.avail.forms.exemplo.utils.ViewJson;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "usuario")
@AttributeOverride(name = "id", column = @Column (name = "id_usuario"))
@Form(nomeEntidade = "Usuário")
public class UsuarioEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	@CampoForm(label = "Nome", listagem = true)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	@CampoForm(label = "Email", tipo = TipoCampo.EMAIL, listagem = true)
	private String email;
	
	@Column(nullable = false)
	@CampoForm(label = "Senha", tipo = TipoCampo.SENHA)
	private String senha;
	
	@Column(nullable = false)
	@CampoForm(label = "Situação", tipo = TipoCampo.CHECKMARK)
	private Situacao situacao;
	
	@CampoForm(label = "Telefone", tipo = TipoCampo.TELEFONE, listagem = true)
	@Column(nullable = false, length = 11)	
	private String telefone;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonView(ViewJson.ComImg.class)
	@Image(label = "Imagem de Perfil")
	private FileBase64 imagemPerfil;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permissao") )
	private List<PermissaoEntity> permissoes;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
	private List<EnderecoEntity> endereco;
	
	public UsuarioEntity() {
		
	}
	
	public UsuarioEntity(String nome, String email, String senha, Situacao situacao, String telefone,
			FileBase64 imagemPerfil, List<PermissaoEntity> permissions,
			List<EnderecoEntity> endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.situacao = situacao;
		this.telefone = telefone;
		this.imagemPerfil = imagemPerfil;
		this.permissoes = permissions;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public FileBase64 getImagemPerfil() {
		return imagemPerfil;
	}
	public void setImagemPerfil(FileBase64 imagemPerfil) {
		this.imagemPerfil = imagemPerfil;
	}
	public List<PermissaoEntity> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<PermissaoEntity> permissions) {
		this.permissoes = permissions;
	}
	public List<EnderecoEntity> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<EnderecoEntity> endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public boolean containsPermision(String role){
		for (PermissaoEntity permissaoUser : this.permissoes) {
			if(role.equals(permissaoUser.getRole())){
				return true;
			}
		}
		return false;
	}
}
