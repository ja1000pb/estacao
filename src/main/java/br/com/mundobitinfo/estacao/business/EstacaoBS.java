package br.com.mundobitinfo.estacao.business;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Estacao;
import br.com.mundobitinfo.estacao.repository.EstacaoRepository;

@Service
public class EstacaoBS {
    @Autowired
	private EstacaoRepository estacaoRepository;
	
	public List<Estacao>  pesquisa() {		
		return estacaoRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Estacao>  pesquisar(String nome) {		
		if ( (nome == null) || (nome.trim().equals("")) || nome.trim().equals(" ") || nome.trim().equals("+=") ) {
			return estacaoRepository.findByAtivoIsTrue();
		} else {
			return estacaoRepository.findByNomeContaining(nome);
		}		
	}

    public Estacao buscarPeloCodigo( Long id) {
    	return estacaoRepository.findById(id).get();
	}

	public Estacao criar( Estacao estacao) {
		return estacaoRepository.save(estacao);

	}

	public  Estacao remover( Estacao estacao) {
		 estacaoRepository.deleteById(estacao.getId());
		 return estacao;
	}
	
	
	public Estacao atualizar(Long id, Estacao estacao) {
		Estacao estacaoSalva = estacaoRepository.findById(id).get();		
		if (estacaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(estacao, estacaoSalva, "id");
		return estacaoRepository.save(estacaoSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Estacao estacaoSalva = buscarEstacaoPeloId(id);
		estacaoSalva.setAtivo(ativo);
		estacaoRepository.save(estacaoSalva);
	}

	private Estacao buscarEstacaoPeloId(Long id) {
		Estacao estacaoSalva = estacaoRepository.findById(id).get();
		if (estacaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return estacaoSalva;
	}
}
