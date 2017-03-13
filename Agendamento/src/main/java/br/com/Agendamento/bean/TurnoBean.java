package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.TurnoDAO;
import br.com.Agendamento.domain.Turno;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TurnoBean implements Serializable {

	private Turno turno;
	private List<Turno> turnos;

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public void novo() {
		turno = new Turno();
	}

	@PostConstruct
	public void listar() {
		try {
			TurnoDAO turnodao = new TurnoDAO();
			turnos = turnodao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os turnos ");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			turno = (Turno) evento.getComponent().getAttributes().get("turnoSelecionado");
			TurnoDAO turnodao = new TurnoDAO();
			turnodao.excluir(turno);
			turnos = turnodao.listar();
			Messages.addGlobalInfo("Turno excluido com sucesso!");
			turnodao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir Turno");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			turno = (Turno) evento.getComponent().getAttributes().get("turnoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir Turno");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			TurnoDAO turnodao = new TurnoDAO();
			turno.setNome(turno.getNome());
			turno.setQuantidade(turno.getQuantidade());
			turnodao.merge(turno);
			novo();
			turnos = turnodao.listar();
			Messages.addGlobalInfo("Turno gravado com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar o Turno ");
			erro.printStackTrace();
		}

	}
}
