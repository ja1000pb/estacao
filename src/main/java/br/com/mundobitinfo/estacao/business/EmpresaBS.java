package br.com.mundobitinfo.estacao.business;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Empresa;
import br.com.mundobitinfo.estacao.repository.EmpresaRepository;

@Service
public class EmpresaBS {
    @Autowired
	private EmpresaRepository empresaRepository;
	
	public List<Empresa>  pesquisa() {		
		return empresaRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Empresa>  pesquisar(String nome) {		
		if ( (nome == null) || (nome.trim().equals("")) || nome.trim().equals(" ") || nome.trim().equals("+=") ) {
			return empresaRepository.findByAtivoIsTrue();
		} else {
			return empresaRepository.findByNomeContaining(nome);
		}		
	}

    public Empresa buscarPeloCodigo( Long id) {
    	return empresaRepository.findById(id).get();
	}

	public Empresa criar( Empresa empresa) {
		return empresaRepository.save(empresa);

	}

	public  Empresa remover( Empresa empresa) {
		 empresaRepository.deleteById(empresa.getId());
		 return empresa;
	}
	
	
	public Empresa atualizar(Long id, Empresa empresa) {
		Empresa empresaSalva = empresaRepository.findById(id).get();		
		if (empresaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(empresa, empresaSalva, "id");
		return empresaRepository.save(empresaSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Empresa empresaSalva = buscarEmpresaPeloId(id);
		empresaSalva.setAtivo(ativo);
		empresaRepository.save(empresaSalva);
	}

	private Empresa buscarEmpresaPeloId(Long id) {
		Empresa empresaSalva = empresaRepository.findById(id).get();
		if (empresaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return empresaSalva;
	}

	public byte[] getImagemEmpresa(Long id) {
		// TODO Auto-generated method stub
		return empresaRepository.getImagemEmpresa(id);
	}

}
