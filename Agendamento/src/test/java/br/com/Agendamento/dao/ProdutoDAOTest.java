package br.com.Agendamento.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.Agendamento.domain.Fabricante;
import br.com.Agendamento.domain.Produto;

public class ProdutoDAOTest {
	Produto produto = new Produto();
	ProdutoDAO produtodao = new ProdutoDAO();
	Fabricante f = new Fabricante();
	FabricanteDAO fdao = new FabricanteDAO();

	@Test
	public void salvar() {
		f.setNome("Ems");
		fdao.salvar(f);
		f = fdao.pesquisarNome("Ems");

		produto.setDescricao("cataflan 50mg c/10");
		produto.setFabricante(f);
		produto.setPreco(new BigDecimal("2.70"));
		produto.setQuantidade(new Short("10"));
		
		produtodao.salvar(produto);
	}

}
