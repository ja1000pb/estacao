package br.com.mundobitinfo.estacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.PermissaoBS;
import br.com.mundobitinfo.estacao.model.Permissao;

@Service
public class PermissaoCtrl {
    
    @Autowired
	private PermissaoBS pb;
	public List<Permissao> pesquisar(String nome){
		return pb.pesquisar(nome);
	}

}
