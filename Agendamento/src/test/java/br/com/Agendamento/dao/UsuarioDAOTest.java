package br.com.Agendamento.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.Agendamento.domain.Disponibilidade;
import br.com.Agendamento.domain.Usuario;

public class UsuarioDAOTest {
	Usuario usuario = new Usuario();
	UsuarioDAO usuariodao = new UsuarioDAO();

	@Ignore
	@Test
	public void salvar() {

		usuario.setNome("sidney");
		usuario.setSenhaNaoCriptografada("123");
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaNaoCriptografada());
		usuario.setSenha(hash.toHex());
		usuario.setCpf("070.915.384-80");
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

	@Test
	public void testar() {
		String datapesquisa = "Manha";
		DisponibilidadeDAO disponibilidadedao = new DisponibilidadeDAO();
		List<Disponibilidade> resultado = disponibilidadedao.buscarPorTurno(datapesquisa);

		for (Disponibilidade disp : resultado) {
			System.out.println("turno.: " + disp.getMt());
			System.out.println("data.: " + disp.getDate());
		}
	}

}
