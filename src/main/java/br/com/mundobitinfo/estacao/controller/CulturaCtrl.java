package br.com.mundobitinfo.estacao.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.CulturaBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Cultura;

@Service
public class CulturaCtrl {
    @Autowired
	private CulturaBS cb;
	
	public List<Cultura>  pesquisar(String nome) {	
		try {
			return cb.pesquisar(nome);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}
	
	public List<Cultura>  pesquisa() {	
		try {
			return cb.pesquisa();		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar clientes", e.getMessage());
		}
		return null;
	}
	

	public Cultura atualizar(Long id, Cultura cultura) {
		return cb.atualizar(id, cultura);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {

		cb.atualizarPropriedadeAtivo(id, ativo);
	}




}
