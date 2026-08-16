package br.com.mundobitinfo.estacao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.mundobitinfo.estacao.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	List<Permissao> findByDescricaoContaining(String descricao);
}
