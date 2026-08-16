package br.com.mundobitinfo.estacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.EmpresaBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Empresa;

@Service
public class EmpresaCtrl {
    @Autowired
	private EmpresaBS eb;
	
	public List<Empresa>  pesquisar(String nome) {	
		try {
			return eb.pesquisar(nome);		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}
	
	public List<Empresa>  pesquisa() {	
		try {
			return eb.pesquisa();		
		} catch (Exception e) {
			// TODO: handle exception
			new EstacaoException("Falha ao consultar empresas", e.getMessage());
		}
		return null;
	}
	

	public Empresa atualizar(Long id, Empresa empresa) {
		return eb.atualizar(id, empresa);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {

		eb.atualizarPropriedadeAtivo(id, ativo);
	}

	public byte[] getImagemEmpresa(Long id) {
		// TODO Auto-generated method stub
		return eb.getImagemEmpresa(id);
	}

}
