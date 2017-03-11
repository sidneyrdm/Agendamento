package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.AgendamentoDAO;
import br.com.Agendamento.domain.Agendamento;
import br.com.Agendamento.domain.Disponibilidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {

	Agendamento agendamento = new Agendamento();
	List<Agendamento> agendamentos;
	List<Disponibilidade> disponibilidades;

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<Disponibilidade> getDisponibilidades() {
		return disponibilidades;
	}

	public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}

	@PostConstruct
	public void listar() {
		try {
			AgendamentoDAO agendamentodao = new AgendamentoDAO();
			agendamentos = agendamentodao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar o Agendamentos ");
			erro.printStackTrace();
		}

	}

	public void novo() {
		try {
			agendamento = new Agendamento();
			agendamentos = new AgendamentoDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Agendamentos ");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			AgendamentoDAO agendamentodao = new AgendamentoDAO();
			agendamento.setDisponibilidade(agendamento.getDisponibilidade());
			agendamento.setUsuario(agendamento.getUsuario());
			agendamentodao.merge(agendamento);
			novo();
			agendamentos = agendamentodao.listar();
			Messages.addGlobalInfo("Agendamento gravado com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar a Agendamento");
			erro.printStackTrace();
		}

	}

}
