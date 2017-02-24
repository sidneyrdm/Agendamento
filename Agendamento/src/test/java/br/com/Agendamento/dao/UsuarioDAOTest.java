package br.com.Agendamento.dao;

import org.junit.Test;

import br.com.Agendamento.domain.Pessoa;
import br.com.Agendamento.domain.Usuario;

public class UsuarioDAOTest {
	Usuario usuario = new Usuario();
	UsuarioDAO usuariodao= new UsuarioDAO();
	Pessoa pessoa = new Pessoa();
	PessoaDAO pessoadao = new PessoaDAO();
	
	
	
	@Test
	public void salvar() {
		pessoa = pessoadao.pesquisarNome("Sidney");
		
		usuario.setAtivo(true);
		usuario.setSenha("12345");
		usuario.setTipo('A');
		usuariodao.salvar(usuario);
	}

}
