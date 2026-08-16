package br.com.mundobitinfo.estacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.LeituraBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Leitura;

@Service
public class LeituraCtrl {
     
    @Autowired
	private LeituraBS eb;
	
	public List<Leitura>  pesquisar(String nome) {	
		try {
			return eb.pesquisar(nome);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar leituras", e.getMessage());
		}
		return null;
	}
	
	public List<Leitura>  pesquisa() {	
		try {
			return eb.pesquisa();		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar leituras", e.getMessage());
		}
		return null;
	}
	

	public Leitura atualizar(Long id, Leitura leitura) {
		return eb.atualizar(id, leitura);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {

		eb.atualizarPropriedadeAtivo(id, ativo);
	}

}
