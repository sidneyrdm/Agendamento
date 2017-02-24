package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
			representantedao.merge(representante);
			novo();
			representantes = representantedao.listar();
			Messages.addGlobalInfo("Representante gravado com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar o Representante");
			erro.printStackTrace();
		}

	}
}
