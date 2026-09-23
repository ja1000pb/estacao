package br.com.mundobitinfo.estacao.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mundobitinfo.estacao.model.Indicadores;

public interface IndicadoresRepository  extends JpaRepository<Indicadores, Long> {
    public List<Indicadores> findByDtindicador(LocalDateTime dtindicador);
    public List<Indicadores> findByEstacao_IdAndAtivoIsTrueOrderByDtindicadorDesc(Long id);
	public List<Indicadores> findByAtivoIsTrue();
	public List<Indicadores> findTop10ByAtivoIsTrueOrderByIdDesc();

}
