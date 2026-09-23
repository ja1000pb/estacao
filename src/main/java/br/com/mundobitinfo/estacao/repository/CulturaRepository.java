package br.com.mundobitinfo.estacao.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mundobitinfo.estacao.model.Cultura;

public interface CulturaRepository extends JpaRepository<Cultura, Long> {
    public List<Cultura> findByNomeContaining(String nome);
	public List<Cultura> findByAtivoIsTrue();
	public List<Cultura> findTop10ByAtivoIsTrueOrderByIdDesc();

}



