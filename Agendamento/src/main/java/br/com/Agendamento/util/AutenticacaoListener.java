package br.com.Agendamento.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.Agendamento.bean.AutenticacaoBean;
import br.com.Agendamento.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {

		String paginaAtual = Faces.getViewId();
	//	System.out.println("página atual.: " + paginaAtual);

		boolean ePaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");

		if (!ePaginaDeAutenticacao) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			if (autenticacaoBean == null) {
				Faces.navigate("/Pages/autenticacao.xhtml");
				return;
			}

			Usuario usuario = autenticacaoBean.getUsuariologado();
			if (usuario == null) {
				Faces.navigate("/Pages/autenticacao.xhtml");
				return;
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
