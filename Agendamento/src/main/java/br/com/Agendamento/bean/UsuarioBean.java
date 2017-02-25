package br.com.Agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.UsuarioDAO;
import br.com.Agendamento.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	Usuario usuario = new Usuario();
	List<Usuario> usuarios;

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
		usuario = new Usuario();
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
			usuario.setNome(usuario.getNome().toUpperCase());
			usuario.setSobrenome(usuario.getSobrenome().toUpperCase());
			usuario.setEmail(usuario.getEmail().toUpperCase());
			usuario.setTelefone(usuario.getTelefone().toUpperCase());
			usuario.setCpf(usuario.getCpf().toUpperCase());
			usuario.setSenhaNaoCriptografada(usuario.getSenha());
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaNaoCriptografada());
			usuario.setSenha(hash.toHex());
			usuario.setAtivo(usuario.getAtivo());
			usuario.setTipo(usuario.getTipo());
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
