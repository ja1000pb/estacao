package br.com.mundobitinfo.estacao.business;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Indicadores;
import br.com.mundobitinfo.estacao.repository.IndicadoresRepository;

@Service
public class IndicadoresBS {
    @Autowired
	private IndicadoresRepository indicadoresRepository;
	
	public List<Indicadores>  pesquisa() {		
		return indicadoresRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Indicadores>  pesquisar(LocalDateTime dtindicador) {		
		if ( (dtindicador == null) ) {
			return indicadoresRepository.findByAtivoIsTrue();
		} else {
			return indicadoresRepository.findByDtindicador(dtindicador);
		}		
	}

    public Indicadores buscarPeloCodigo( Long id) {
    	return indicadoresRepository.findById(id).get();
	}

        public List<Indicadores> buscarPelaEstacao( Long id) {
    	return indicadoresRepository.findByEstacao_IdAndAtivoIsTrueOrderByDtindicadorDesc(id);
	}

	public Indicadores criar( Indicadores indicadores) {
		return indicadoresRepository.save(indicadores);

	}

	public  Indicadores remover( Indicadores indicadores) {
		 indicadoresRepository.deleteById(indicadores.getId());
		 return indicadores;
	}
	
	
	public Indicadores atualizar(Long id, Indicadores indicadores) {
		Indicadores indicadoresSalvo = indicadoresRepository.findById(id).get();		
		if (indicadoresSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(indicadores, indicadoresSalvo, "id");
		return indicadoresRepository.save(indicadoresSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Indicadores indicadoresSalvo = buscarPeloCodigo(id);
		indicadoresSalvo.setAtivo(ativo);
		indicadoresRepository.save(indicadoresSalvo);
	}

	private Indicadores buscarPeloId(Long id) {
		Indicadores indicadoresSalvo = indicadoresRepository.findById(id).get();
		if (indicadoresSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return indicadoresSalvo;
	}
}

