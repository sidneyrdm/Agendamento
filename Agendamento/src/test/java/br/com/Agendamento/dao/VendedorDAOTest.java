package br.com.Agendamento.dao;

import org.junit.Test;

import br.com.Agendamento.domain.Empresa;
import br.com.Agendamento.domain.Representante;

public class VendedorDAOTest {
	Representante vendedor = new Representante();
	VendedorDAO vendedordao = new VendedorDAO();
	Empresa empresa = new Empresa();

	@Test
	public void salvar() {
		empresa.setCnpj("05678702000142");
		empresa.setNome("RendeMais");
		
		vendedor.setNome("Antonio");
		vendedor.setSobrenome("Carlos");
		vendedor.setEmail("AntonioCarlos@gmail.com");
		vendedor.setEmpresa(empresa);
		vendedor.setTelefone("81988488515");

		vendedordao.salvar(vendedor);
	}

}
