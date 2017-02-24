package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.CidadeDAO;
import br.com.Agendamento.dao.EstadoDAO;
import br.com.Agendamento.domain.Cidade;
import br.com.Agendamento.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Cidade getCidade() {
		return cidade;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadedao = new CidadeDAO();
			cidades = cidadedao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar o Estado ");
			erro.printStackTrace();
		}

	}

	public void novo() {
		try {
			cidade = new Cidade();
			estados = new EstadoDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Estados ");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CidadeDAO cidadedao = new CidadeDAO();
			cidade.setNome(cidade.getNome().toUpperCase());
			cidadedao.merge(cidade);
			novo();
			cidades = cidadedao.listar();
			Messages.addGlobalInfo("Cidade gravada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar a Cidade");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			CidadeDAO cidadedao = new CidadeDAO();
			cidadedao.excluir(cidade);
			cidades = cidadedao.listar();
			Messages.addGlobalInfo("Cidade excluída com sucesso!");
			cidadedao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir cidade");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			estados = new EstadoDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Estados ");
			erro.printStackTrace();
		}
	}
}
