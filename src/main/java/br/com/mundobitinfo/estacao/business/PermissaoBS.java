package br.com.mundobitinfo.estacao.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Permissao;
import br.com.mundobitinfo.estacao.repository.PermissaoRepository;

@Service
public class PermissaoBS {
    
    @Autowired
	private PermissaoRepository pr;
	
	public List<Permissao> pesquisar(String descricao){
		if (descricao == null || descricao.trim().equals("")) {
			return pr.findAll();
		} else {
			return pr.findByDescricaoContaining(descricao);
		}
	}

}
