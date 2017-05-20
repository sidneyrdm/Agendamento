package br.com.Agendamento.dao;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

import br.com.Agendamento.util.Email;

public class testes {

	@Test
	public void teste() {
		Email email = new Email();
		try {
			email.sendEmail("sidneybritomal@gmail.com", "sidney", "confirmacão de agendamento", "Obrigado pelo agendamento");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
