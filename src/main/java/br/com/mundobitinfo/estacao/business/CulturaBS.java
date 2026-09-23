package br.com.mundobitinfo.estacao.business;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Cultura;
import br.com.mundobitinfo.estacao.repository.CulturaRepository;

@Service
public class CulturaBS {
 @Autowired
	private CulturaRepository culturaRepository;
	
	public List<Cultura>  pesquisa() {		
		return culturaRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Cultura>  pesquisar(String nome) {		
		if ( (nome == null) || (nome.trim().equals("")) || nome.trim().equals(" ") || nome.trim().equals("+=") ) {
			return culturaRepository.findByAtivoIsTrue();
		} else {
			return culturaRepository.findByNomeContaining(nome);
		}		
	}

    public Cultura buscarPeloCodigo( Long id) {
    	return culturaRepository.findById(id).get();
	}

	public Cultura criar( Cultura cultura) {
		return culturaRepository.save(cultura);

	}

	public  Cultura remover( Cultura cultura) {
		 culturaRepository.deleteById(cultura.getId());
		 return cultura;
	}
	
	
	public Cultura atualizar(Long id, Cultura cultura) {
		Cultura culturaSalva = culturaRepository.findById(id).get();		
		if (culturaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(cultura, culturaSalva, "id");
		return culturaRepository.save(culturaSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Cultura culturaSalva = buscarPeloCodigo(id);
		culturaSalva.setAtivo(ativo);
		culturaRepository.save(culturaSalva);
	}

	private Cultura buscarPeloId(Long id) {
		Cultura culturaSalva = culturaRepository.findById(id).get();
		if (culturaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return culturaSalva;
	}
}
