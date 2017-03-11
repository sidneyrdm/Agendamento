package br.com.Agendamento.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Disponibilidade extends GenericDomain {

	@Column
	private int qtd_manha;
	
	@Column
	private int qtd_tarde;
	
	@Column
	private int agendado;
	
	@Column(nullable= false)
	private Date data;

	public int getQtd_manha() {
		return qtd_manha;
	}

	public void setQtd_manha(int qtd_manha) {
		this.qtd_manha = qtd_manha;
	}

	public int getQtd_tarde() {
		return qtd_tarde;
	}

	public void setQtd_tarde(int qtd_tarde) {
		this.qtd_tarde = qtd_tarde;
	}

	public int getAgendado() {
		return agendado;
	}

	public void setAgendado(int agendado) {
		this.agendado = agendado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
