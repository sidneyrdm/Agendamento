package br.com.Agendamento.dao;

import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.Agendamento.domain.Fabricante;

public class FabricanteDAOTest {
	FabricanteDAO fdao = new FabricanteDAO();
	Fabricante f = new Fabricante();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void salvar() {
		f.setNome("MEDLEY");
		fdao.salvar(f);

		f.setNome("EMS");
		fdao.salvar(f);

		f.setNome("EuroFarma");
		fdao.salvar(f);
	}

	@Test
	@Ignore
	public void listar() {
		List<Fabricante> resultado = fdao.listar();
		JOptionPane.showMessageDialog(null, "Registros encontrados.: " + resultado.size());
	}

	@Test
	@Ignore
	public void merge() {
		f.setNome("Aché");
		fdao.merge(f);

	}

}
