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

	Usuario usuario = new Usuario();
	List<Usuario> usuarios;
	private List<Empresa> empresas;

	public List<Empresa> getEmpresas() {
		return empresas;
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

	public void novo() {
		try {
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
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuarios = usuariodao.listar();

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

}
