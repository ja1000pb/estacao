package br.com.mundobitinfo.estacao.business;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Pluviometro;
import br.com.mundobitinfo.estacao.repository.PluviometroRepository;

@Service
public class PluviometroBS {
   @Autowired
	private PluviometroRepository pluviometroRepository;
	
	public List<Pluviometro>  pesquisa() {		
		return pluviometroRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Pluviometro>  pesquisar(LocalDateTime dthinicio) {		
		if ( (dthinicio == null) ) {
			return pluviometroRepository.findByAtivoIsTrue();
		} else {
			return pluviometroRepository.findByDthinicio(dthinicio);
		}		
	}

    public Pluviometro buscarPeloCodigo( Long id) {
    	return pluviometroRepository.findById(id).get();
	}

        public List<Pluviometro> buscarPelaLeitura( Long id) {
    	return pluviometroRepository.findByLeitura_IdAndAtivoIsTrueOrderByDthinicioDesc(id);
	}

	public Pluviometro criar( Pluviometro pluviometro) {
		return pluviometroRepository.save(pluviometro);
	}

	public  Pluviometro remover( Pluviometro pluviometro) {
		 pluviometroRepository.deleteById(pluviometro.getId());
		 return pluviometro;
	}
	
	
	public Pluviometro atualizar(Long id, Pluviometro pluviometro) {
		Pluviometro pluviometroSalvo = pluviometroRepository.findById(id).get();		
		if (pluviometroSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(pluviometro, pluviometroSalvo, "id");
		return pluviometroRepository.save(pluviometroSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Pluviometro pluviometroSalvo = buscarPeloCodigo(id);
		pluviometroSalvo.setAtivo(ativo);
		pluviometroRepository.save(pluviometroSalvo);
	}

	private Pluviometro buscarPeloId(Long id) {
		Pluviometro pluviometroSalvo = pluviometroRepository.findById(id).get();
		if (pluviometroSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pluviometroSalvo;
	}
}

