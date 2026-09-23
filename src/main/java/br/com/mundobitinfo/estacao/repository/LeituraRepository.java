package br.com.mundobitinfo.estacao.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.mundobitinfo.estacao.model.Leitura;
import java.time.LocalDateTime;


public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    public List<Leitura> findByDthleitura(LocalDateTime dthleitura);
   	public List<Leitura> findByAtivoIsTrue();
	public List<Leitura> findTop10ByAtivoIsTrueOrderByIdDesc();
	public List<Leitura> findTop10ByEstacaoIdAndAtivoIsTrueOrderByDthleituraDesc(Long estacaoId);
    Optional<Leitura> findTopByEstacaoIdAndAtivoIsTrueAndDthleituraLessThanEqualOrderByDthleituraDesc(Long estacaoId,
                                                                                                    LocalDateTime dataHora);
}
