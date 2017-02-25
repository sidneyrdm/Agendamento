package br.com.Agendamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Representante extends GenericDomain {

	@Column(length = 14, nullable = false)
	private String cpf;

	@Column(length = 32, nullable = false)
	private String senha;

	@Transient
	private String senhaNaoCriptografada;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 50)
	private String sobrenome;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;

	@Column(length = 11, nullable = false)
	private String telefone;

	@Column(length = 50)
	private String email;

	@Column(nullable = false)
	private Character tipo;

	@Column(nullable = false)
	private Boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenhaNaoCriptografada() {
		return senhaNaoCriptografada;
	}

	public void setSenhaNaoCriptografada(String senhaNaoCriptografada) {
		this.senhaNaoCriptografada = senhaNaoCriptografada;
	}

}
