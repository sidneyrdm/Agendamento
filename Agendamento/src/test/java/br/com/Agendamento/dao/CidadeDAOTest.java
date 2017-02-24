package br.com.Agendamento.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.Agendamento.domain.Cidade;
import br.com.Agendamento.domain.Estado;

public class CidadeDAOTest {

	Cidade cidade = new Cidade();
	CidadeDAO cidadedao = new CidadeDAO();

	@Before
	public void setUp() throws Exception {
	}

	@Test
		public void salvar() {
		Estado estado = new EstadoDAO().pesquisarNome("PERNAMBUCO");

		cidade.setNome("Canhotinho");
		cidade.setEstado(estado);

		cidadedao.salvar(cidade);
	}

	@Test
	@Ignore
	public void listar() {
		List<Cidade> resultado = cidadedao.listar();

		for (Cidade cidade : resultado) {
			System.out.println(cidade.getNome() + " - "
					+ cidade.getEstado().getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		cidade = cidadedao.buscar(1L);
		System.out.println(cidade.getNome() + " - "
				+ cidade.getEstado().getNome());
	}

	@Test
	@Ignore
	public void excluir() {
		cidade = cidadedao.buscar(3L);
		cidadedao.excluir(cidade);
	}
	
	@Test
	@Ignore
	public void editar(){
		Estado estado = new Estado();
		EstadoDAO estadodao = new EstadoDAO();
		estado = estadodao.buscar(1L);
		cidade = cidadedao.buscar(1L);
		System.out.println("cidade buscada= "+cidade.getNome());
		System.out.println("cidade buscada= "+cidade.getEstado().getNome());
		
		cidade.setNome("novacidade");
		cidade.setEstado(estado);
		cidadedao.editar(cidade);
		
		cidade = cidadedao.buscar(1L);
		
		System.out.println("cidade atualizada= "+cidade.getNome());
		System.out.println("cidade atualizada= "+cidade.getEstado().getNome());
	}

}
