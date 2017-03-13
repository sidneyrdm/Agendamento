package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.DisponibilidadeDAO;
import br.com.Agendamento.dao.TurnoDAO;
import br.com.Agendamento.domain.Disponibilidade;
import br.com.Agendamento.domain.Turno;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DisponibilidadeBean implements Serializable {

	private Disponibilidade disponibilidade;
	private List<Disponibilidade> disponibilidades;
	private List<Turno> turnos;

	public Disponibilidade getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public List<Disponibilidade> getDisponibilidades() {
		return disponibilidades;
	}

	public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public void novo() {
		disponibilidade = new Disponibilidade();
		turnos = new TurnoDAO().listar();
	}

	@PostConstruct
	public void listar() {
		try {
			novo();
			disponibilidades = new ArrayList<Disponibilidade>();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar o Disponibilidade para agendamento");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
			disponibilidade.setAgendado(0);
			disponibilidade.setData(disponibilidade.getData());
			disponibilidadedao.merge(disponibilidade);
			disponibilidade.setTurno(disponibilidade.getTurno());
			novo();
			disponibilidades = disponibilidadedao.listar();
			Messages.addGlobalInfo("Disponibilidade gravada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar a Disponibilidade ");
			erro.printStackTrace();
		}

	}

	public void atualizar(ActionEvent evento) {
		try {
			disponibilidade = (Disponibilidade) evento.getComponent().getAttributes().get("dataselecionada");
			Messages.addGlobalInfo("seleção realizada com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir representante");
			erro.printStackTrace();
		}
	}

	public void popular() {

		try {
			if (disponibilidade != null) {
				DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
				disponibilidades = disponibilidadedao.buscarPorTurno(disponibilidade.getTurno().getCodigo());
			} else {
				disponibilidades = new ArrayList<>();
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("erro ao tentar filtrar as diponibilidades " + e);
			e.printStackTrace();
		}
	}

}
