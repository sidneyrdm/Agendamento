package br.com.Agendamento.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Disponibilidade extends GenericDomain {

	@Column(length= 5)
	private String mt;
	
	@Column(length= 2)
	private int qtd;	
	
	@Column(nullable = false)
	private String Date;
		
	@ManyToOne
	@JoinColumn()
	private Turno turno;
	
	@Column
	private int agendado;

	
	private Date data;

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	
}
