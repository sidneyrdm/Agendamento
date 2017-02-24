package br.com.Agendamento.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Agendamento.domain.Estado;

public class EstadoDAOTest {

	EstadoDAO estadodao = new EstadoDAO();
	Estado estado = new Estado();

	@Test
	public void salvar() {
		estado.setNome("RIO DE JANEIRO");
		estado.setSigla("RJ");
		estadodao.salvar(estado);
		
		estado.setNome("SAO PAULO");
		estado.setSigla("SP");
		estadodao.salvar(estado);

	}

	@Test
	@Ignore
	public void listar() {
		List<Estado> resultado = estadodao.listar();

		for (Estado estado : resultado) {
			System.out.println("nome.: " + estado.getNome() + " sigla.: "
					+ estado.getSigla());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		estado = estadodao.buscar(codigo);
		System.out.println(estado.getSigla() + " - " + estado.getNome());

	}

	@Test
	@Ignore
	public void excluir(){
		estado = estadodao.buscar(2L);
		estadodao.excluir(estado);
	}
	
	@Test
	@Ignore
	public void editar(){
		estado = estadodao.buscar(4L);
		estado.setSigla("ST");
		estadodao.editar(estado);
	}
}
