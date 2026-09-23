package br.com.mundobitinfo.estacao.controller;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.IndicadoresBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Indicadores;

@Service
public class IndicadoresCtrl {
@Autowired
 private IndicadoresBS cb;
	
	public List<Indicadores>  pesquisar(LocalDateTime dtindicador) {	
		try {
			return cb.pesquisar(dtindicador);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}

    	public List<Indicadores>  pesquisarporestacao(Long id) {	
		try {
			return cb.buscarPelaEstacao(id);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}
	

	public List<Indicadores>  pesquisa() {	
		try {
			return cb.pesquisa();		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar clientes", e.getMessage());
		}
		return null;
	}   
	

	public Indicadores atualizar(Long id, Indicadores indicadores) {
		return cb.atualizar(id, indicadores);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {

		cb.atualizarPropriedadeAtivo(id, ativo);
	}
}

