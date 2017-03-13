package br.com.Agendamento.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Disponibilidade extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Turno turno;
	
	@Column
	private int agendado;

	@Column(nullable = false)
	private Date data;

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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	
}
