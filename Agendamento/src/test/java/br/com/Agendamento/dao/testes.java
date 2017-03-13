package br.com.Agendamento.dao;

import java.util.List;

import org.junit.Test;

import br.com.Agendamento.domain.Disponibilidade;

public class testes {

	@Test
	public void test() {
		Long codigoTurno = 2L;

		DisponibilidadeDAO ddao = new DisponibilidadeDAO();
		List<Disponibilidade> resultado = ddao.buscarPorTurno(codigoTurno);

		for (Disponibilidade disponibilidade : resultado) {
			System.out.println("código .: " + disponibilidade.getCodigo());
			System.out.println("nome do turno.: " + disponibilidade.getTurno().getNome());
			System.out.println("quantidade do turno.: " + disponibilidade.getTurno().getQuantidade());

		}

	}

}
