package br.com.Agendamento.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.UsuarioDAO;
import br.com.Agendamento.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private int rows = 20;
	private String nome;
	private String cpf;
	private String senha;
	private String empresa;
	private boolean permissaoAdm;
	private boolean permissaoRepresentante;
	private boolean logar;

	public static Usuario usuario = new Usuario();
	private Usuario usuariologado;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosConectados;

	public boolean isPermissaoAdm() {
		return permissaoAdm;
	}

	public boolean isLogar() {
		return logar;
	}

	public void setLogar(boolean logar) {
		this.logar = logar;
	}

	public void setPermissaoAdm(boolean permissaoAdm) {
		this.permissaoAdm = permissaoAdm;
	}

	public boolean isPermissaoRepresentante() {
		return permissaoRepresentante;
	}

	public void setPermissaoRepresentante(boolean permissaoRepresentante) {
		this.permissaoRepresentante = permissaoRepresentante;
	}

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

	public Usuario getUsusario() {
		return usuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static void setUsuario(Usuario usuario) {
		AutenticacaoBean.usuario = usuario;
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

	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setUsuariologado(Usuario usuariologado) {
		this.usuariologado = usuariologado;
	}

	@PostConstruct
	public void iniciar() {
		usuarios = new ArrayList<Usuario>();
		usuariosConectados = new ArrayList<Usuario>();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuariologado = usuariodao.autenticar(cpf, senha);
			usuariosConectados = new UsuarioDAO().buscarConectado(true);

			if (usuariologado == null) {
				Messages.addGlobalError("Usuário e/ou senha incorretos");
				return;
			} else {
				usuariologado.setConectado(true);
				usuariodao.editar(usuariologado);
				//if (usuariosConectados.contains(usuariologado)) {
					//Messages.addGlobalError("Usuário " + usuariologado.getNome() + " já Conectado!");
					//return;
			//	}
			}

			usuario = usuariologado;
			if (usuario.getTipo() == 'A') {
				permissaoAdm = true;
				permissaoRepresentante = false;
			} else {
				permissaoAdm = false;
				permissaoRepresentante = true;
			}
			nome = usuariologado.getNome();
			usuarios.add(usuariologado);
			desabilitabotaoLogar();
			Faces.redirect("./Pages/principalPrivado.xhtml");

		} catch (IOException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void logOff() throws IOException {
		usuariologado.setConectado(false);
		UsuarioDAO usuariodao = new UsuarioDAO();
		usuariodao.editar(usuariologado);

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();

		Faces.redirect("./Pages/principal.xhtml");
	}

	public void desabilitabotaoLogar() throws IOException {
	}

	public List<Usuario> getUsuariosConectados() {
		return usuariosConectados;
	}

	public void setUsuariosConectados(List<Usuario> usuariosConectados) {
		this.usuariosConectados = usuariosConectados;
	}

	public void mostrarUsuariosConectados() {
		usuariosConectados = new UsuarioDAO().buscarConectado(true);
		if (usuariosConectados.contains(usuariologado))
			System.out.println("usuário já conectado!");
		for (Usuario user : usuariosConectados) {
			System.out.println("nome.: " + user.getNome());
		}

	}

}
