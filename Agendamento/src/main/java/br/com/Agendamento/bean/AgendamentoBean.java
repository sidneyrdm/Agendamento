package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.AgendamentoDAO;
import br.com.Agendamento.domain.Agendamento;
import br.com.Agendamento.domain.Disponibilidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {
	AutenticacaoBean ab;
	Agendamento agendamento = new Agendamento();
	List<Agendamento> agendamentos;
	List<Disponibilidade> disponibilidades;

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public AutenticacaoBean getAb() {
		return ab;
	}

	public void setAb(AutenticacaoBean ab) {
		this.ab = ab;
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
			novo();
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

	public void editar(ActionEvent evento) {
		try {
			agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir Agendamento");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");
			AgendamentoDAO agendamentodao = new AgendamentoDAO();
			agendamentodao.excluir(agendamento);
			agendamentos = agendamentodao.listar();
			Messages.addGlobalInfo("Agendamento excluido com sucesso!");
			agendamentodao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir Agendamento");
			erro.printStackTrace();
		}
	}

	public void mostrar() {
		if (agendamento != null) {
			System.out.println("data Selecionada.: " + agendamento.getDisponibilidade().getDate());
			System.out.println("Vagas disponiveis.: " + agendamento.getDisponibilidade().getQtd());
			System.out.println("Usuário conectado.: " + agendamento.getUsuario().getNome());
		}

	}

}
