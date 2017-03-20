package br.com.Agendamento.bean;

import java.io.IOException;
import java.util.ArrayList;
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

	private String nome;
	private String cpf;
	private String senha;
	private String empresa;

	private Usuario usuario;
	private Usuario usuariologado;
	private List<Usuario> usuarios;

	public String getNome() {
		return nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsusario() {
		return usuario;
	}

	public void setUsusario(Usuario ususario) {
		this.usuario = ususario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		usuarios = new ArrayList<Usuario>();
	}

	public void livre() throws IOException {
		Faces.redirect("./Pages/principal.xhtml");
	}

	public void autenticar() {
		try {
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuariologado = usuariodao.autenticar(cpf, senha);
			AgendamentoBean agendamento = new AgendamentoBean();

			if (usuariologado == null) {
				Messages.addGlobalError("Usuário e/ou senha incorretos");
				return;
			}
			agendamento.setUsuario(usuariologado);
			nome = usuariologado.getNome();
			usuarios.add(usuariologado);
			Faces.redirect("./Pages/principal.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void logOff() throws IOException {
		usuariologado = null;
		Faces.redirect("./Pages/autenticacao.xhtml");
	}

	public boolean temPermissoes(List<String> permissoes) {
		for (String permissao : permissoes) {
			if (usuariologado.getTipo() == (permissao.charAt(0)))
				return true;
		}
		return false;
	}
}
