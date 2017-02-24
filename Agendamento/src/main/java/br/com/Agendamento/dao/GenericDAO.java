package br.com.Agendamento.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.Agendamento.util.HibernateUtil;

public class GenericDAO<Entidade> {
	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
			//// JOptionPane.showMessageDialog(null, "CADASTRO EFETUADO COM
			//// SUCESSO");
		} catch (RuntimeException erro) {

			if (transacao != null) {
				transacao.rollback();
			}
			//// JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EFETUAR
			//// CADASTRO");
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			// JOptionPane.showMessageDialog(null, "NENHUM REGISTRO
			// ENCONTRADO");
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			// JOptionPane.showMessageDialog(null, "NENHUM REGISTRO
			// ENCONTRADO");
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Entidade pesquisarNome(String pesquisa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.eq("nome", pesquisa));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			// JOptionPane.showMessageDialog(null, "NENHUM REGISTRO
			// ENCONTRADO");
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
			// JOptionPane.showMessageDialog(null, "REGISTRO EXCLUIDO COM
			// SUCESSO");
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			// JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EXCLUIR
			// REGISTRO");
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
			// JOptionPane.showMessageDialog(null, "REGISTRO EDITADO COM
			// SUCESSO");
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			// JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EDITAR");
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void merge(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.merge(entidade);
			transacao.commit();
			// JOptionPane.showMessageDialog(null, "CADASTRO EFETUADO COM
			// SUCESSO");
		} catch (RuntimeException erro) {

			if (transacao != null) {
				transacao.rollback();
			}
			// JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EFETUAR
			// CADASTRO");
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
