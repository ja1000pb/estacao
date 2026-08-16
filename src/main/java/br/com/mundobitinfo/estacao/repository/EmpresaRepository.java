package br.com.mundobitinfo.estacao.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.mundobitinfo.estacao.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    public List<Empresa> findByNomeContaining(String nome);
	public List<Empresa> findByAtivoIsTrue();
	public List<Empresa> findTop10ByAtivoIsTrueOrderByIdDesc();
	@Query(nativeQuery = true, value =  "select logo from empresa where id = :p1 ")
	public byte[] getImagemEmpresa(@Param("p1") Long id);

}
