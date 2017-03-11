package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.DisponibilidadeDAO;
import br.com.Agendamento.domain.Disponibilidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DisponibilidadeBean implements Serializable {

	Disponibilidade disponibilidade = new Disponibilidade();
	List<Disponibilidade> disponibilidades;

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

	public void novo() {
		disponibilidade = new Disponibilidade();
	}

	@PostConstruct
	public void listar() {
		try {
			DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
			disponibilidades = disponibilidadedao.listar();

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
			disponibilidade.setQtd_manha(disponibilidade.getQtd_manha());
			disponibilidade.setQtd_tarde(disponibilidade.getQtd_tarde());
			disponibilidadedao.merge(disponibilidade);
			novo();
			disponibilidades = disponibilidadedao.listar();
			Messages.addGlobalInfo("Disponibilidade gravada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar a Disponibilidade ");
			erro.printStackTrace();
		}

	}

}
