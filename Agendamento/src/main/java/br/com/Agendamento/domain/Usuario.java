package br.com.Agendamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	
	@Column(length = 32, nullable = false)
	private String senha;

	@Transient
	private String senhaNaoCriptografada;

	@PrimaryKeyJoinColumn
	@Column(length = 14, nullable = false, unique = true)
	private String cpf;

	@Column(length = 45, nullable = false)
	private String nome;

	@Column(length = 15)
	private String celular;
	
	@Column(length = 14)
	private String telefone;

	@Column(length = 45)
	private String email;

	@Column()
	private Character tipo;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

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

	public String getSenhaNaoCriptografada() {
		return senhaNaoCriptografada;
	}

	public void setSenhaNaoCriptografada(String senhaNaoCriptografada) {
		this.senhaNaoCriptografada = senhaNaoCriptografada;
	}

}
