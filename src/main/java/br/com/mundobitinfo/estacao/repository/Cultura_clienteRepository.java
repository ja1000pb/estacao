package br.com.mundobitinfo.estacao.repository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mundobitinfo.estacao.model.Cultura_cliente;


public interface Cultura_clienteRepository   extends JpaRepository<Cultura_cliente, Long> {
    public List<Cultura_cliente> findByDtplantio(LocalDateTime dtplantio);
	public List<Cultura_cliente> findByAtivoIsTrue();
	public List<Cultura_cliente> findTop10ByAtivoIsTrueOrderByIdDesc();

}
