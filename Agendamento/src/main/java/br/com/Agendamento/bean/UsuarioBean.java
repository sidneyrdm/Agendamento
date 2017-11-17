package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.EmpresaDAO;
import br.com.Agendamento.dao.UsuarioDAO;
import br.com.Agendamento.domain.Empresa;
import br.com.Agendamento.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private boolean empresa;
	private Usuario usuario;
	private Usuario selecao;
	List<Usuario> usuarios;
	List<Usuario> usuariosR;
	private AutenticacaoBean aut;

	public AutenticacaoBean getAut() {
		return aut;
	}

	public void setAut(AutenticacaoBean aut) {
		this.aut = aut;
	}

	private List<Empresa> empresas;

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public boolean isEmpresa() {
		return empresa;
	}

	public void setEmpresa(boolean empresa) {
		this.empresa = empresa;
	}

	public Usuario getSelecao() {
		return selecao;
	}

	public void setSelecao(Usuario selecao) {
		this.selecao = selecao;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosR() {
		return usuariosR;
	}

	public void setUsuariosR(List<Usuario> usuariosR) {
		this.usuariosR = usuariosR;
	}

	public void novo() {
		try {
			selecao = new Usuario();
			usuario = new Usuario();
			empresas = new EmpresaDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Empresas ");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			empresa = true;
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuarios = usuariodao.listar();
			usuariosR = usuariodao.buscarR('R');
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as Usuarios ");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuariodao.excluir(usuario);
			usuarios = usuariodao.listar();
			Messages.addGlobalInfo("Usuario excluído com sucesso!");
			usuariodao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir Usuario");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			if (usuario.getTipo() == 'R') {
				empresa = false;
				empresas = new EmpresaDAO().listar();
			}
			empresas = new EmpresaDAO().listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar excluir usuario");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuario.setNome(usuario.getNome());
			usuario.setEmail(usuario.getEmail());
			usuario.setTelefone(usuario.getTelefone());
			usuario.setCelular(usuario.getCelular());
			usuario.setConectado(false);
			usuario.setCpf(usuario.getCpf());
			usuario.setSenhaNaoCriptografada(usuario.getSenha());
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaNaoCriptografada());
			usuario.setSenha(hash.toHex());
			usuario.setTipo(usuario.getTipo());
			usuario.setEmpresa(usuario.getEmpresa());
			usuariodao.merge(usuario);
			novo();
			usuarios = usuariodao.listar();
			Messages.addGlobalInfo("Usuario gravado com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gravar o Usuario ");
			erro.printStackTrace();
		}

	}

	public void desabilitaEmpresa() {
		if (usuario.getTipo() == 'R')
			empresa = false;
		else
			empresa = true;
	}

	public void userMain() {
		Usuario user = new Usuario();
		UsuarioDAO userdao = new UsuarioDAO();
		user = userdao.BuscaPorCpf("000.000.000-00");

		if (user == null) {
			user = new Usuario();
			user.setNome("administrador");
			user.setSenhaNaoCriptografada("00000");
			SimpleHash hash = new SimpleHash("md5", user.getSenhaNaoCriptografada());
			user.setSenha(hash.toHex());
			user.setCpf("000.000.000-00");
			user.setCelular("(81)3251-8453");
			user.setTipo('A');
			user.setConectado(false);
			userdao.salvar(user);
		} else
			System.out.println("usuário já existe");

	}

}
