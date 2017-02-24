package br.com.Agendamento.dao;

import org.junit.Test;

import br.com.Agendamento.domain.Empresa;
import br.com.Agendamento.domain.Representante;

public class RepresentanteDAOTest {
	Empresa empresa = new Empresa();
	EmpresaDAO empresadao = new EmpresaDAO();

	Representante representante = new Representante();
	RepresentanteDAO representantedao = new RepresentanteDAO();

	@Test
	public void salvar() {
		empresa.setNome("CastanhadoRei");
		empresa.setCnpj("19366747000196");
		empresadao.salvar(empresa);
		
		empresa = empresadao.pesquisarNome("CastanhadoRei");
		
		representante.setEmail("rep1@rep1.com");
		representante.setEmpresa(empresa);
		representante.setNome("Sidney");
		representante.setSobrenome("brito");
		representante.setTelefone("988950121");
		
		representantedao.salvar(representante);
	}

}
