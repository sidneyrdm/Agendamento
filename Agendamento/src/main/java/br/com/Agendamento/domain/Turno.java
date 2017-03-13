package br.com.Agendamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Turno extends GenericDomain {

	@Column(length = 5, nullable = false)
	private String nome;

	@Column(length = 2, nullable = false)
	private int quantidade;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	

}
