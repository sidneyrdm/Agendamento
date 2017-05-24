package br.com.Agendamento.dao;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.mail.EmailException;
import org.junit.Ignore;
import org.junit.Test;

import br.com.Agendamento.util.Email;

public class testes {

	@Ignore
	@Test
	public void teste() {
		Email email = new Email();
		try {
			email.sendEmail("sidneybritomal@gmail.com", "sidney", "confirmacão de agendamento",
					"Obrigado pelo agendamento");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void teste2() {
		long TEMPO = (1000 * 10); // chama o método a cada 3 segundos
		Timer timer = null;
		if (timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				public void run() {
					try {
						System.out.println("Se passaram 10 segundos");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
		}
	}

	@Test
	public void aaaa() {
		final long TEMPO = 2000; // executa a cada 5 min
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("executando...");
			}
		};
		timer.scheduleAtFixedRate(timerTask, TEMPO, TEMPO);
	}

}
