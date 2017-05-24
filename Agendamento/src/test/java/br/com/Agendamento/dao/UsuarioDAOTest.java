package br.com.Agendamento.dao;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.Agendamento.domain.Cidade;
import br.com.Agendamento.domain.Disponibilidade;
import br.com.Agendamento.domain.Usuario;

public class UsuarioDAOTest {
	Usuario usuario = new Usuario();
	UsuarioDAO usuariodao = new UsuarioDAO();

	@Test
	@Ignore
	public void salvar() {

		usuario.setNome("sidney");
		usuario.setSenhaNaoCriptografada("123");
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaNaoCriptografada());
		usuario.setSenha(hash.toHex());
		usuario.setCpf("070.915.384-80");
		usuario.setCelular("(81)98895-0121");
		usuario.setTipo('A');
		usuario.setConectado(false);
		usuariodao.salvar(usuario);
	}

	@Ignore
	@Test
	public void autenticar() {

		String cpf = "072.915.384-80";
		String senha = "1234";

		usuario = usuariodao.autenticar(cpf, senha);
		System.out.println(usuario.getNome() + " =nome");
	}

	@Ignore
	@Test
	public void aaa() {
		usuario = usuariodao.buscar(1L);
		usuario.setConectado(false);
		usuariodao.editar(usuario);
	}

	@Ignore
	@Test
	public void testar() {
		String datapesquisa = "Manha";
		DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
		List<Disponibilidade> resultado = disponibilidadedao.buscarPorTurno(datapesquisa);

		for (Disponibilidade disp : resultado) {
			System.out.println("turno.: " + disp.getTurno());
			System.out.println("data.: " + disp.getDataView());
		}
	}

	// @SuppressWarnings({ "null" })
	@Ignore
	@Test
	public void userMain() {
		Usuario user = new UsuarioDAO().BuscaPorCpf("070.915.384-80");
		if (user == null) {
			JOptionPane.showMessageDialog(null, "entrou");
			usuario.setNome("sidney");
			usuario.setSenhaNaoCriptografada("123");
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaNaoCriptografada());
			usuario.setSenha(hash.toHex());
			usuario.setCpf("070.915.384-80");
			usuario.setCelular("(81)98895-0121");
			usuario.setTipo('A');
			usuario.setConectado(false);
			usuariodao.salvar(usuario);

		} else
			JOptionPane.showMessageDialog(null, "nao entrou");
	}
	
	@Test
	public void cidade(){
		CidadeDAO cdao = new CidadeDAO();
		List<Cidade> estados = cdao.buscarPorEstado(1L);
		
		System.out.println("tamanho.: "+estados.size());
	}

}
