package br.com.mundobitinfo.estacao.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.ClienteBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Cliente;

@Service
public class ClienteCtrl {
 @Autowired
 private ClienteBS cb;
	
	public List<Cliente>  pesquisar(String nome) {	
		try {
			return cb.pesquisar(nome);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}
	
	public List<Cliente>  pesquisa() {	
		try {
			return cb.pesquisa();		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar clientes", e.getMessage());
		}
		return null;
	}
	

	public Cliente atualizar(Long id, Cliente cliente) {
		return cb.atualizar(id, cliente);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {

		cb.atualizarPropriedadeAtivo(id, ativo);
	}



}

