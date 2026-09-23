package br.com.mundobitinfo.estacao.business;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Cliente;
import br.com.mundobitinfo.estacao.model.Cultura_cliente;
import br.com.mundobitinfo.estacao.repository.Cultura_clienteRepository;

@Service
public class Cultura_clienteBS {
   @Autowired
	private Cultura_clienteRepository cultura_clienteRepository;
	
	public List<Cultura_cliente>  pesquisa() {		
		return cultura_clienteRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Cultura_cliente>  pesquisar(LocalDateTime dtplantio) {		
		if (dtplantio == null) {
			return cultura_clienteRepository.findByAtivoIsTrue();
		} else {
			return cultura_clienteRepository.findByDtplantio(dtplantio);
		}		
	}

    public Cultura_cliente buscarPeloCodigo( Long id) {
    	return cultura_clienteRepository.findById(id).get();
	}

	public Cultura_cliente criar(Cultura_cliente cultura_cliente) {
		return cultura_clienteRepository.save(cultura_cliente);

	}

	public  Cultura_cliente remover(Cultura_cliente cultura_cliente) {
		 cultura_clienteRepository.deleteById(cultura_cliente.getId());
		 return cultura_cliente;
	}
	
	
	public Cultura_cliente atualizar(Long id, Cultura_cliente cultura_cliente) {
		Cultura_cliente cultura_clienteSalvo = cultura_clienteRepository.findById(id).get();		
		if (cultura_clienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(cultura_cliente, cultura_clienteSalvo, "id");
		return cultura_clienteRepository.save(cultura_clienteSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Cultura_cliente cultura_clienteSalvo = buscarPeloCodigo(id);
		cultura_clienteSalvo.setAtivo(ativo);
		cultura_clienteRepository.save(cultura_clienteSalvo);
	}

	private Cultura_cliente buscarPeloId(Long id) {
		Cultura_cliente cultura_clienteSalvo = cultura_clienteRepository.findById(id).get();
		if (cultura_clienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cultura_clienteSalvo;
	}
}


