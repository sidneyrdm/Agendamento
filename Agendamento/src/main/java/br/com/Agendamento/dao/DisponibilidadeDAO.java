package br.com.Agendamento.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.Agendamento.domain.Disponibilidade;
import br.com.Agendamento.util.HibernateUtil;

public class DisponibilidadeDAO extends GenericDAO<Disponibilidade> {
	@SuppressWarnings("unchecked")
	public List<Disponibilidade> buscarPorData(String date) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Disponibilidade.class);
			consulta.add(Restrictions.eq("Date", date));
			List<Disponibilidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Disponibilidade> buscarPorTurno(String mt) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Disponibilidade.class);
			consulta.add(Restrictions.eq("mt", mt));
			List<Disponibilidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	

}
