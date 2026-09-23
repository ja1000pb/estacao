package br.com.mundobitinfo.estacao.controller;


import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.PluviometroBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Pluviometro;

@Service
public class PluviometroCtrl {
@Autowired
 private PluviometroBS cb;
	
	public List<Pluviometro>  pesquisar(LocalDateTime dtindicador) {	
		try {
			return cb.pesquisar(dtindicador);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}

    	public List<Pluviometro>  pesquisarporleitura(Long id) {	
		try {
			return cb.buscarPelaLeitura(id);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}
	

	public List<Pluviometro>  pesquisa() {	
		try {
			return cb.pesquisa();		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar clientes", e.getMessage());
		}   
		return null;
	}   
	

	public Pluviometro atualizar(Long id, Pluviometro pluviometro) {
		return cb.atualizar(id, pluviometro);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {

		cb.atualizarPropriedadeAtivo(id, ativo);
	}
}
