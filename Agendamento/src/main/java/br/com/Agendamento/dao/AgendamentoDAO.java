package br.com.Agendamento.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.Agendamento.domain.Agendamento;
import br.com.Agendamento.util.HibernateUtil;

public class AgendamentoDAO extends GenericDAO<Agendamento> {
	@SuppressWarnings("unchecked")
	public List<Agendamento> buscarPorUsuario(Long usuarioCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Agendamento.class);
			consulta.add(Restrictions.eq("usuario.codigo", usuarioCodigo));
			List<Agendamento> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
