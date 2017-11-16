package br.com.Agendamento.bean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.Agendamento.dao.UsuarioDAO;
import br.com.Agendamento.domain.Usuario;

public class ClasseInicial implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("null")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Usuario usuario = new Usuario();
		UsuarioDAO usuariodao = new UsuarioDAO();
		usuario = usuariodao.BuscaPorCpf("000.000.000-00");
		if (usuario == null) {
			usuario.setNome("administrador");
			usuario.setSenhaNaoCriptografada("00000");
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaNaoCriptografada());
			usuario.setSenha(hash.toHex());
			usuario.setCpf("000.000.000-00");
			usuario.setCelular("(81)3251-8453");
			usuario.setTipo('A');
			usuario.setConectado(false);
			usuariodao.salvar(usuario);
		} else
			return;
	}

}
