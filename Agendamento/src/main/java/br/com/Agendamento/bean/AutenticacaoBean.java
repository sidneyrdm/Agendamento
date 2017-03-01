package br.com.Agendamento.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.Agendamento.dao.RepresentanteDAO;
import br.com.Agendamento.dao.UsuarioDAO;
import br.com.Agendamento.domain.Representante;
import br.com.Agendamento.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private String nome;
	private String cpf;
	private String senha;
	private String empresa;
	
	private Usuario usuario;
	private Representante representante;
	private Usuario usuariologado;
	private Representante representantelogado;

	public String getNome() {
		return nome;
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



	public Representante getRepresentante() {
		return representante;
	}



	public void setRepresentante(Representante representante) {
		this.representante = representante;
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

	public Representante getRepresentantelogado() {
		return representantelogado;
	}

	public void setRepresentantelogado(Representante representantelogado) {
		this.representantelogado = representantelogado;
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
        representante = new Representante();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuariologado = usuariodao.autenticar(cpf, senha);
			RepresentanteDAO representantedao = new RepresentanteDAO();
			representantelogado = representantedao.autenticar(cpf, senha);

			if (usuariologado == null && representantelogado == null) {
				Messages.addGlobalError("Usuário e/ou senha incorretos");
				return;
			}
			if (representantelogado != null) {
				nome = representantelogado.getNome()+" "+representantelogado.getSobrenome();
				empresa = representantelogado.getEmpresa().getNome();
				Faces.redirect("./Pages/agendamento.xhtml");
				return;
			} else
				nome = usuariologado.getNome();
			Faces.redirect("./Pages/principal.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public boolean temPermissoes(List<String> permissoes) {
		for (String permissao : permissoes) {
			if (usuariologado.getTipo() == (permissao.charAt(0)))
				return true;
			if (representantelogado.getTipo() == (permissao.charAt(0)))
				return true;
		}
		return false;
	}

}
