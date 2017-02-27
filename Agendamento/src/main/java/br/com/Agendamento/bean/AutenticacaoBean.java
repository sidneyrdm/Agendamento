package br.com.Agendamento.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.UsuarioDAO;
import br.com.Agendamento.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuario;
	private Usuario usuariologado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuariologado() {
		return usuariologado;
	}

	public void setUsuariologado(Usuario usuariologado) {
		this.usuariologado = usuariologado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuariologado = usuariodao.autenticar(usuario.getCpf(), usuario.getSenha());
			if (usuariologado == null) {
				Messages.addGlobalError("Usuário e/ou senha incorretos");
				return;
			}

			Faces.redirect("./Pages/principal.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public boolean temPermissoes(List<String> permissoes){
		for(String permissao: permissoes){
			if(usuariologado.getTipo() == (permissao.charAt(0)))
				return true;
		}
		return false;
	}

}
