package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.EmpresaDAO; 
import br.com.Agendamento.dao.RepresentanteDAO;
import br.com.Agendamento.domain.Empresa;
import br.com.Agendamento.domain.Representante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RepresentanteBean implements Serializable {

	private Representante representante;
	private List<Representante> representantes;
	private List<Empresa> empresas;

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public List<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public void novo() {
		try {
			representante = new Representante();
			empresas = new EmpresaDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Empresas ");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			RepresentanteDAO representantedao = new RepresentanteDAO();
			representante.setNome(representante.getNome().toUpperCase());
			representante.setSobrenome(representante.getSobrenome().toUpperCase());
			representante.setEmail(representante.getEmail().toUpperCase());
			representante.setTelefone(representante.getTelefone().toUpperCase());
			representante.setLogin(representante.getLogin().toUpperCase());
			representante.setSenha(representante.getSenha().toUpperCase());
			representante.setAtivo(representante.getAtivo());
			representante.setTipo(representante.getTipo());
			representante.setEmpresa(representante.getEmpresa());
			
			representantedao.merge(representante);
			novo();
			representantes = representantedao.listar();
			Messages.addGlobalInfo("Representante gravado com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar o Representante");
			erro.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {
		try {
			RepresentanteDAO representantedao = new RepresentanteDAO();
			representantes = representantedao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Representantes ");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			representante = (Representante) evento.getComponent().getAttributes().get("representanteSelecionado");

			RepresentanteDAO representantedao = new RepresentanteDAO();
			representantedao.excluir(representante);
			representantes = representantedao.listar();
			Messages.addGlobalInfo("Representante excluído com sucesso!");
			representantedao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir representante");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			representante = (Representante) evento.getComponent().getAttributes().get("representanteSelecionado");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir representante");
			erro.printStackTrace();
		}
	}

}
