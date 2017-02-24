package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.EstadoDAO;
import br.com.Agendamento.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void novo() {
		estado = new Estado();
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadodao = new EstadoDAO();
			estados = estadodao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar o Estado ");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

			EstadoDAO estadodao = new EstadoDAO();
			estadodao.excluir(estado);
			estados = estadodao.listar();
			Messages.addGlobalInfo("Estado excluído com sucesso!");
			estadodao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir estado");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir estado");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EstadoDAO estadodao = new EstadoDAO();
			estado.setNome(estado.getNome().toUpperCase());
			estado.setSigla(estado.getSigla().toUpperCase());
			estadodao.merge(estado);
			novo();
			estados = estadodao.listar();
			Messages.addGlobalInfo("Estado gravado com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar o Estado ");
			erro.printStackTrace();
		}

	}
}
