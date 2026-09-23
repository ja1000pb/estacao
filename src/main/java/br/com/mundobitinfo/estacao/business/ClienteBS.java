package br.com.mundobitinfo.estacao.business;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Cliente;
import br.com.mundobitinfo.estacao.repository.ClienteRepository;

@Service
public class ClienteBS {
    @Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente>  pesquisa() {		
		return clienteRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Cliente>  pesquisar(String nome) {		
		if ( (nome == null) || (nome.trim().equals("")) || nome.trim().equals(" ") || nome.trim().equals("+=") ) {
			return clienteRepository.findByAtivoIsTrue();
		} else {
			return clienteRepository.findByNomeContaining(nome);
		}		
	}

    public Cliente buscarPeloCodigo( Long id) {
    	return clienteRepository.findById(id).get();
	}

	public Cliente criar( Cliente cliente) {
		return clienteRepository.save(cliente);

	}

	public  Cliente remover( Cliente cliente) {
		 clienteRepository.deleteById(cliente.getId());
		 return cliente;
	}
	
	
	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteSalvo = clienteRepository.findById(id).get();		
		if (clienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return clienteRepository.save(clienteSalvo);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Cliente clienteSalvo = buscarPeloCodigo(id);
		clienteSalvo.setAtivo(ativo);
		clienteRepository.save(clienteSalvo);
	}

	private Cliente buscarPeloId(Long id) {
		Cliente clienteSalvo = clienteRepository.findById(id).get();
		if (clienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return clienteSalvo;
	}
}

