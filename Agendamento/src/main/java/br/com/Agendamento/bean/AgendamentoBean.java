package br.com.Agendamento.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.AgendamentoDAO;
import br.com.Agendamento.dao.EmpresaDAO;
import br.com.Agendamento.dao.RepresentanteDAO;
import br.com.Agendamento.domain.Agendamento;
import br.com.Agendamento.domain.Empresa;
import br.com.Agendamento.domain.Representante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {

	private Calendar dataMinima;
	private Calendar dataMaxima;
	private Agendamento agendamento;
	private List<Agendamento> agendamentos;
	private List<Representante> representantes;
	private List<Empresa> empresas;
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public Calendar getDataMinima() {
		return dataMinima;
	}

	public void setDataMinima(Calendar dataMinima) {
		this.dataMinima = dataMinima;
	}

	public Calendar getDataMaxima() {
		return dataMaxima;
	}

	public void setDataMaxima(Calendar dataMaxima) {
		this.dataMaxima = dataMaxima;
	}

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

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
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
			representantes = new RepresentanteDAO().listar();
			empresas = new EmpresaDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Representantes ");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			AgendamentoDAO agendamentodao = new AgendamentoDAO();
			agendamento.setData(agendamento.getData());
			agendamento.setRepresentante(agendamento.getRepresentante());
			agendamento.setTurno(agendamento.getTurno());
			agendamento.setEmpresa(agendamento.getEmpresa());

			agendamentodao.merge(agendamento);
			novo();
			agendamentos = agendamentodao.listar();
			Messages.addGlobalInfo("Agendamento gravado com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar o Agendamento");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");

			AgendamentoDAO agendamentodao = new AgendamentoDAO();
			agendamentodao.excluir(agendamento);
			agendamentos = agendamentodao.listar();
			Messages.addGlobalInfo("Agendamento excluído com sucesso!");
			agendamentodao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir agendamento");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar editar agendamento");
			erro.printStackTrace();
		}
	}

}
