package br.com.Agendamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Agendamento extends GenericDomain {

	@OneToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@OneToOne
	@JoinColumn(nullable = false)
	private Disponibilidade disponibilidade;

	@Column(nullable = false, length = 12)
	private String status;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Disponibilidade getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
