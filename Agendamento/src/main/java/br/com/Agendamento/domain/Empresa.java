package br.com.Agendamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Empresa extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 18, nullable = false)
	private String cnpj;

	@Column(length = 18, nullable = true)
	private String bairro;

	@Column(length = 18, nullable = true)
	private String rua;

	@Column(length = 4, nullable = true)
	private String numero;

	@Column(length = 9, nullable = true)
	private String cep;

	@Column(length = 10, nullable = true)
	private String inscricaoEstadual;

	@Column(length = 50, nullable = false)
	private String razaoSocial;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cidade cidade;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNome() {
		return nome;
	}
	
	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
