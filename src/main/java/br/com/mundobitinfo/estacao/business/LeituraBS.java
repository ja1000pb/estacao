package br.com.mundobitinfo.estacao.business;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Leitura;
import br.com.mundobitinfo.estacao.repository.LeituraRepository;

@Service
public class LeituraBS {
    @Autowired
	private LeituraRepository leituraRepository;
	
	public List<Leitura>  pesquisa() {		
		return leituraRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Leitura>  pesquisar(String data) {		
		LocalDateTime dataHora = LocalDateTime.parse(data);
		return leituraRepository.findByDthleitura(dataHora);		
	}

    public Leitura buscarPeloCodigo( Long id) {
    	return leituraRepository.findById(id).get();
	}

	public Leitura criar( Leitura leitura) {
		return leituraRepository.save(leitura);

	}

	public  Leitura remover( Leitura leitura) {
		 leituraRepository.deleteById(leitura.getId());
		 return leitura;
	}
	
	
	public Leitura atualizar(Long id, Leitura leitura) {
		Leitura leituraSalva = leituraRepository.findById(id).get();		
		if (leituraSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(leitura, leituraSalva, "id");
		return leituraRepository.save(leituraSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Leitura leituraSalva = buscarLeituraPeloId(id);
		leituraSalva.setAtivo(ativo);
		leituraRepository.save(leituraSalva);
	}

	private Leitura buscarLeituraPeloId(Long id) {
		Leitura leituraSalva = leituraRepository.findById(id).get();
		if (leituraSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return leituraSalva;
	}

}
