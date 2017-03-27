package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.AgendamentoDAO;
import br.com.Agendamento.dao.DisponibilidadeDAO;
import br.com.Agendamento.domain.Agendamento;
import br.com.Agendamento.domain.Disponibilidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {

	private AutenticacaoBean aut;
	private boolean desabilitaBotaoSalvar;
	private int disponiveis;
	AutenticacaoBean ab;
	Agendamento agendamento = new Agendamento();
	List<Agendamento> agendamentos;
	List<Agendamento> agendamentosUser;
	List<Disponibilidade> disponibilidades;

	public AutenticacaoBean getAut() {
		return aut;
	}

	public void setAut(AutenticacaoBean aut) {
		this.aut = aut;
	}

	public List<Agendamento> getAgendamentosUser() {
		return agendamentosUser;
	}

	public void setAgendamentosUser(List<Agendamento> agendamentosUser) {
		this.agendamentosUser = agendamentosUser;
	}

	public boolean getDesabilitaBotaoSalvar() {
		return desabilitaBotaoSalvar;
	}

	public void setDesabilitaBotaoSalvar(boolean ativo) {
		this.desabilitaBotaoSalvar = ativo;
	}

	public int getDisponiveis() {
		return disponiveis;
	}

	public void setDisponiveis(int disponiveis) {
		this.disponiveis = disponiveis;
	}

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
			agendamentosUser = agendamentodao.buscarPorUsuario(aut.getUsuario().getCodigo());
			agendamentos = agendamentodao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar o Agendamentos ");
			erro.printStackTrace();
		}

	}

	public void novo() {
		try {
			aut = new AutenticacaoBean();
			this.desabilitaBotaoSalvar = true;
			agendamento = new Agendamento();
			agendamentosUser = new AgendamentoDAO().buscarPorUsuario(aut.getUsuario().getCodigo());
			agendamentos = new AgendamentoDAO().listar();
			this.disponiveis = 0;
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
			atualizaDisponibilidade(agendamento.getDisponibilidade().getCodigo(), 's');
			novo();
			Messages.addGlobalInfo("Agendamento gravado com sucesso!");
			agendamentosUser = agendamentodao.buscarPorUsuario(aut.getUsuario().getCodigo());
			agendamentos = agendamentodao.listar();
			refresh();
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
			atualizaDisponibilidade(agendamento.getDisponibilidade().getCodigo(), 'e');
			agendamentosUser = agendamentodao.buscarPorUsuario(aut.getUsuario().getCodigo());
			agendamentos = agendamentodao.listar();
			Messages.addGlobalInfo("Agendamento excluido com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir Agendamento");
			erro.printStackTrace();
		}
	}

	public void atualizaDisponibilidade(Long codigo, Character funcao) {
		Disponibilidade disponibilidade = new Disponibilidade();
		DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
		disponibilidade = disponibilidadedao.buscar(codigo);
		if (funcao == 's')
			disponibilidade.setAgendado(disponibilidade.getAgendado() + 1);
		else
			disponibilidade.setAgendado(disponibilidade.getAgendado() - 1);
		disponibilidadedao.editar(disponibilidade);

	}

	//função que habilita e desabilita o botão gravar
	public void habilitaBotaoGravar() {
		try {

			Disponibilidade disponibilidade = new Disponibilidade();
			DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
			disponibilidade = disponibilidadedao.buscar(agendamento.getDisponibilidade().getCodigo());
			this.disponiveis = disponibilidade.getQtd() - disponibilidade.getAgendado();

			int semana = getSemana(agendamento.getDisponibilidade().getData());
			int mes = getMes(agendamento.getDisponibilidade().getData());
			int ano = getAno(agendamento.getDisponibilidade().getData());

			for (Agendamento a : agendamentosUser) {
				if (getAno(a.getDisponibilidade().getData()) == ano) {
					if (getMes(a.getDisponibilidade().getData()) == mes) {
						if (getSemana(a.getDisponibilidade().getData()) == semana) {
							Messages.addGlobalError("Voce já está agendado para esta semana!");
							this.desabilitaBotaoSalvar = true;
						} else
							this.desabilitaBotaoSalvar = false;
					}

				} else {

					if (this.disponiveis == 0) {
						Messages.addGlobalError("Não temos mais vagas para a data selecionada!");
						this.desabilitaBotaoSalvar = true;
					} else
						this.desabilitaBotaoSalvar = false;
				}
			}

		} catch (RuntimeException erro) {

			erro.printStackTrace();
		}

	}

	public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		javax.faces.application.ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}

	@SuppressWarnings("static-access")
	public int getSemana(Date data) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(data);
		return c.get(c.WEEK_OF_MONTH);
	}

	@SuppressWarnings("static-access")
	public int getMes(Date data) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(data);
		return c.get(c.MONTH);
	}

	@SuppressWarnings("static-access")
	public int getAno(Date data) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(data);
		return c.get(c.YEAR);
	}

	/*
	public void mostrar() {
		int semana = getSemana(agendamento.getDisponibilidade().getData());
		int mes = getMes(agendamento.getDisponibilidade().getData());
		for (Agendamento agend : agendamentosUser) {
			System.out.println("usuario logado.: " + agend.getUsuario().getNome());
			System.out.println("quantidade de agendamentos.: " + agendamentosUser.size());
			System.out.println("data do agendamento.: " + agend.getDisponibilidade().getDataView());
			System.out.println(mes + " Mes do agendamento.: " + getMes(agend.getDisponibilidade().getData()));
			System.out.println(semana + " semana do agendamento.: " + getSemana(agend.getDisponibilidade().getData()));

		}

	}*/
}
