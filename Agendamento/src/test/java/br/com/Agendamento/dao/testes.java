package br.com.Agendamento.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Agendamento.bean.AgendamentoBean;

public class testes {

	@Ignore
	@Test
	public static void main(String[] args) {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"); // HH:mm:ss
		Date data = new Date();
		try {
			System.out.println("data " + dataFormatada.format(data));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	


}
