package br.com.Agendamento.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Agendamento extends GenericDomain {

	@OneToOne
	@JoinColumn(nullable = false)
	private Representante representante;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;
	

	private Character turno;
	private Date data;

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Character getTurno() {
		return turno;
	}

	public void setTurno(Character turno) {
		this.turno = turno;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	

}
