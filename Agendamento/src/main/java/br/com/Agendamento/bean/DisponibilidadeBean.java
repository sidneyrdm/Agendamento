package br.com.Agendamento.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.DisponibilidadeDAO;
import br.com.Agendamento.domain.Disponibilidade;
import br.com.Agendamento.domain.Turno;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DisponibilidadeBean implements Serializable {

	private Disponibilidade disponibilidade;
	private Disponibilidade selecao;
	private List<Disponibilidade> disponibilidadesCarregadas;
	private List<Disponibilidade> disponibilidades;
	private List<Turno> turnos;
	private Turno turno;

	public List<Disponibilidade> getDisponibilidadesCarregadas() {
		return disponibilidadesCarregadas;
	}

	public void setDisponibilidadesCarregadas(List<Disponibilidade> disponibilidadesCarregadas) {
		this.disponibilidadesCarregadas = disponibilidadesCarregadas;
	}

	public Disponibilidade getSelecao() {
		return selecao;
	}

	public void setSelecao(Disponibilidade selecao) {
		this.selecao = selecao;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

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
		disponibilidadesCarregadas = new ArrayList<Disponibilidade>();
		selecao = new Disponibilidade();
		disponibilidades = new DisponibilidadeDAO().listar();
	}

	@PostConstruct
	public void listar() {
		try {
			novo();
			disponibilidades = new DisponibilidadeDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar o Disponibilidade para agendamento");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
			disponibilidade.setAgendado(0);
			disponibilidade.setDate(formataData(disponibilidade.getData()));
			disponibilidade.setMt(disponibilidade.getMt());
			disponibilidade.setQtd(disponibilidade.getQtd());
			disponibilidadedao.merge(disponibilidade);
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
			if (selecao != null) {
				DisponibilidadeDAO ddao = new DisponibilidadeDAO();
				disponibilidadesCarregadas = ddao.buscarPorTurno(selecao.getMt());
				System.out.println("selecao.: " + disponibilidadesCarregadas.size());

			} else {
				disponibilidadesCarregadas = new ArrayList<Disponibilidade>();
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar filtrar Disponibilidades");
			erro.printStackTrace();
		}
		/*
		 * try { if (selecao != null) { DisponibilidadeDAO ddao = new
		 * DisponibilidadeDAO(); disponibilidadesCarregadas =
		 * ddao.buscarPorData(selecao.getDate());
		 * 
		 * } else { disponibilidadesCarregadas = new
		 * ArrayList<Disponibilidade>(); }
		 * 
		 * } catch (RuntimeException erro) {
		 * Messages.addGlobalError("erro ao tentar filtrar Disponibilidades");
		 * erro.printStackTrace(); }
		 */

	}

	public void excluir(ActionEvent evento) {
		try {
			disponibilidade = (Disponibilidade) evento.getComponent().getAttributes().get("disponibilidadeSelecionada");
			DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
			disponibilidadedao.excluir(disponibilidade);
			disponibilidades = disponibilidadedao.listar();
			Messages.addGlobalInfo("Disponibilidade excluída com sucesso!");
			disponibilidadedao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir Disponibilidade");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			disponibilidade = (Disponibilidade) evento.getComponent().getAttributes().get("disponibilidadeSelecionada");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir Disponibilidade");
			erro.printStackTrace();
		}
	}

	public String formataData(Date data) {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

		return dataFormatada.format(data);
	}

}
