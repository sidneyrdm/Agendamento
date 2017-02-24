package br.com.Agendamento.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.Agendamento.domain.Cliente;
import br.com.Agendamento.domain.Pessoa;

public class ClienteDAOTest {
	Cliente cliente = new Cliente();
	ClienteDAO clientedao = new ClienteDAO();
	Pessoa pessoa = new Pessoa();
	PessoaDAO pessoadao = new PessoaDAO();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Ignore
	public void salvar() throws ParseException {

		pessoa.setBairro("jardim sao paulo");
		pessoa.setCelular("81988950121");
		pessoa.setCep("50781600");
		pessoa.setComplemento("casa");
		pessoa.setCpf("07001538480");
		pessoa.setEmail("sidneybritomal@gmail.com");
		pessoa.setNome("Sidneyy");
		pessoa.setNumero(new Short("238"));
		pessoa.setRg("7300683");
		pessoa.setRua("avenida sao paulo");
		pessoa.setTelefone("32514041");
		pessoadao.salvar(pessoa);
		
		pessoa = pessoadao.pesquisarNome("Sidneyy");
		
		//cliente.setDataCadastro(new Date());
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2017"));
		cliente.setLiberado(false);
		cliente.setPessoa(pessoa);
		clientedao.salvar(cliente);
		
	}
	
	@Test
	public void editar(){
		pessoa = pessoadao.pesquisarNome("Sidneyy");
		pessoa.setNome("Eduardo");
	
		pessoadao.editar(pessoa);
	}

}
