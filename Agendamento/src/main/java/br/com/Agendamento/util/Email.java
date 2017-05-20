package br.com.Agendamento.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {

	public void sendEmail(String endemail, String nome, String assunto, String mensagem) throws EmailException {

		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthentication("sbritoc.rendemais@gmail.com", "RendeMais01");
		email.setSSLOnConnect(true);
		email.setFrom("teste@gmail.com");
		email.setSubject(assunto);
		email.setMsg(mensagem);
		email.addTo(endemail, nome);
		email.send();
	}

}
