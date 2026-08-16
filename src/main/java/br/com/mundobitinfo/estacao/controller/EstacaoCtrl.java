package br.com.mundobitinfo.estacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.EstacaoBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Estacao;

@Service
public class EstacaoCtrl {
    
    @Autowired
	private EstacaoBS eb;
	
	public List<Estacao>  pesquisar(String nome) {	
		try {
			return eb.pesquisar(nome);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar estações", e.getMessage());
		}
		return null;
	}
	
	public List<Estacao>  pesquisa() {	
		try {
			return eb.pesquisa();		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar estações0", e.getMessage());
		}
		return null;
	}
	

	public Estacao atualizar(Long id, Estacao estacao) {
		return eb.atualizar(id, estacao);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {

		eb.atualizarPropriedadeAtivo(id, ativo);
	}
}
