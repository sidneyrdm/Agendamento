package br.com.Agendamento.dao;

import org.junit.Test;

import br.com.Agendamento.domain.Empresa;

public class EmpresaTest {
Empresa empresa = new Empresa();
EmpresaDAO empresadao = new EmpresaDAO();
	
	@Test
	public void salvar() {
		empresa.setNome("RendeMais");
		empresa.setCnpj("05678702000142");
		
		empresadao.salvar(empresa);
	}

}
