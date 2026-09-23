package br.com.mundobitinfo.estacao.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mundobitinfo.estacao.model.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByNomeContaining(String nome);
	public List<Cliente> findByAtivoIsTrue();
	public List<Cliente> findTop10ByAtivoIsTrueOrderByIdDesc();

}
