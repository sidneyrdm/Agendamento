package br.com.Agendamento.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.Agendamento.bean.UsuarioBean;

public class HibernateContexto implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes();
		UsuarioBean ubean = new UsuarioBean();
		ubean.userMain();

	}

}
