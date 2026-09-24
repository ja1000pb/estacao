package br.com.mundobitinfo.estacao.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mundobitinfo.estacao.model.Pluviometro;

public interface PluviometroRepository  extends JpaRepository<Pluviometro, Long> {
    public List<Pluviometro> findByDthinicio(LocalDateTime dthinicio);
    public List<Pluviometro> findByLeitura_IdAndAtivoIsTrueOrderByDthinicioDesc(Long id);
	public List<Pluviometro> findByAtivoIsTrue();
	public List<Pluviometro> findTop10ByAtivoIsTrueOrderByIdDesc();

}
