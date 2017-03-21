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

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DisponibilidadeBean implements Serializable {

	private Disponibilidade disponibilidade;
	private Disponibilidade selecao;
	private List<Disponibilidade> disponibilidadesCarregadas;
	private List<Disponibilidade> disponibilidades;
	private List<Disponibilidade> disponibilidadesAbertas;

	public List<Disponibilidade> getDisponibilidadesAbertas() {
		return disponibilidadesAbertas;
	}

	public void setDisponibilidadesAbertas(List<Disponibilidade> disponibilidadesAbertas) {
		this.disponibilidadesAbertas = disponibilidadesAbertas;
	}

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
			disponibilidade.setTurno(disponibilidade.getTurno());
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
				disponibilidadesCarregadas = new ArrayList<Disponibilidade>();
				disponibilidadesAbertas = ddao.buscarPorTurno(selecao.getTurno());
				for (Disponibilidade disp : disponibilidadesAbertas) {
					if (disp.getAgendado() < disp.getQtd())
						if (!disponibilidadesCarregadas.contains(disp))
							disponibilidadesCarregadas.add(disp);
				}
				if (disponibilidadesCarregadas.size() == 0)
					Messages.addGlobalWarn("Não temos mais vagas para o turno da "+selecao.getTurno());

			} else {
				disponibilidadesCarregadas = new ArrayList<Disponibilidade>();
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar filtrar Disponibilidades");
			erro.printStackTrace();
		}

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
