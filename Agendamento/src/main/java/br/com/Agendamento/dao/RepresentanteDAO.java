package br.com.Agendamento.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.Agendamento.domain.Representante;
import br.com.Agendamento.util.HibernateUtil;

public class RepresentanteDAO extends GenericDAO<Representante> {

	public Representante autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Representante.class);
			consulta.add(Restrictions.eq("cpf", cpf));
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));

			Representante resultado = (Representante) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		}
	}

}
