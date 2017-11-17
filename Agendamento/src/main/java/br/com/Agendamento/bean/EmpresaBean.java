package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.CidadeDAO;
import br.com.Agendamento.dao.EmpresaDAO;
import br.com.Agendamento.dao.EstadoDAO;
import br.com.Agendamento.domain.Cidade;
import br.com.Agendamento.domain.Empresa;
import br.com.Agendamento.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EmpresaBean implements Serializable {

	private Empresa empresa;
	private AutenticacaoBean aut;
	public AutenticacaoBean getAut() {
		return aut;
	}

	public void setAut(AutenticacaoBean aut) {
		this.aut = aut;
	}

	private List<Empresa> empresas;
	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

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
		estado = new Estado();

		EstadoDAO estadoDAO = new EstadoDAO();
		estados = estadoDAO.listar();

		cidades = new ArrayList<Cidade>();
	}

	@PostConstruct
	public void listar() {
		try {
			EmpresaDAO empresadao = new EmpresaDAO();
			empresas = empresadao.listar();

			cidades = new CidadeDAO().listar();
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
			empresas = new EmpresaDAO().listar();

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
			empresa.setRazaoSocial(empresa.getRazaoSocial());
			empresa.setBairro(empresa.getBairro());
			empresa.setCep(empresa.getCep());
			empresa.setCidade(empresa.getCidade());
			empresa.setInscricaoEstadual(empresa.getInscricaoEstadual());
			empresa.setNumero(empresa.getNumero());
			empresa.setRua(empresa.getRua());
			empresadao.merge(empresa);
			novo();
			empresas = empresadao.listar();
			Messages.addGlobalInfo("Empresa gravada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar a Empresa ");
			erro.printStackTrace();
		}

	}

	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
				System.out.println("cidades.: " + cidades.size());
			}else{
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}
}
