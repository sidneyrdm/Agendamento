package br.com.Agendamento.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Disponibilidade extends GenericDomain {

	@Column(length = 5)
	private String turno;

	@Column(length = 2)
	private int qtd;

	@Column(nullable = false)
	private String dataView;

	@Column
	private int agendado;

	@Column
	private Date data;

	public String getDataView() {
		return dataView;
	}

	public void setDataView(String date) {
		dataView = date;
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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

}
