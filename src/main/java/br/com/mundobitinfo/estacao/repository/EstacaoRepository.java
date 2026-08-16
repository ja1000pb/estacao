package br.com.mundobitinfo.estacao.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.mundobitinfo.estacao.model.Estacao;

public interface EstacaoRepository extends JpaRepository<Estacao, Long> {
    public List<Estacao> findByNomeContaining(String nome);
	public List<Estacao> findByAtivoIsTrue();
	public List<Estacao> findTop10ByAtivoIsTrueOrderByIdDesc();
}


