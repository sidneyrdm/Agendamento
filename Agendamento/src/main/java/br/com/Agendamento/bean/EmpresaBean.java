package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.Agendamento.dao.EmpresaDAO;
import br.com.Agendamento.domain.Empresa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EmpresaBean implements Serializable {

	private Empresa empresa;
	private List<Empresa> empresas;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public void novo() {
		empresa = new Empresa();
	}

	@PostConstruct
	public void listar() {
		try {
			EmpresaDAO empresadao = new EmpresaDAO();
			empresas = empresadao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as Empresas ");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			empresa = (Empresa) evento.getComponent().getAttributes().get("empresaSelecionada");
			EmpresaDAO empresadao = new EmpresaDAO();
			empresadao.excluir(empresa);
			empresas = empresadao.listar();
			Messages.addGlobalInfo("Empresa excluída com sucesso!");
			empresadao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir empresa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			empresa = (Empresa) evento.getComponent().getAttributes().get("empresaSelecionada");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir empresa");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EmpresaDAO empresadao = new EmpresaDAO();
			empresa.setNome(empresa.getNome().toUpperCase());
			empresa.setCnpj(empresa.getCnpj().toUpperCase());
			empresadao.merge(empresa);
			novo();
			empresas = empresadao.listar();
			Messages.addGlobalInfo("Empresa gravada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar a Empresa ");
			erro.printStackTrace();
		}

	}
}
