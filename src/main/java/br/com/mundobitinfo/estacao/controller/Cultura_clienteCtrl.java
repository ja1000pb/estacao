package br.com.mundobitinfo.estacao.controller;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.Cultura_clienteBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Cliente;
import br.com.mundobitinfo.estacao.model.Cultura_cliente;


@Service
public class Cultura_clienteCtrl {
    @Autowired
	private Cultura_clienteBS cb;
	
	public List<Cultura_cliente>  pesquisar(LocalDateTime dtplantio) {	
		try {
			return cb.pesquisar(dtplantio);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}
	
	public List<Cultura_cliente>  pesquisa() {	
		try {
			return cb.pesquisa();		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar clientes", e.getMessage());
		}
		return null;
	}
	

	public Cultura_cliente atualizar(Long id, Cultura_cliente cultura_cliente) {
		return cb.atualizar(id, cultura_cliente);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {

		cb.atualizarPropriedadeAtivo(id, ativo);
	}



}

