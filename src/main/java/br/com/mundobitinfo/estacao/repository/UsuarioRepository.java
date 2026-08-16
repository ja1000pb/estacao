package br.com.mundobitinfo.estacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mundobitinfo.estacao.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String Email);
   	public Optional<Usuario> findByNome(String Nome);
	public Optional<Usuario> findByNomeOrEmail(String Nome, String email);
	List<Usuario> findByNomeContainingOrEmailContaining(String nome, String email);
	public List<Usuario> findByNomeContaining(String nome);
	public List<Usuario> findByAtivoIsTrue();
	public List<Usuario> findTop10ByAtivoIsTrueOrderByIdDesc();
    @Query(nativeQuery = true, value =  "select senha from usuario where id = :p1 or login like :p2")
	public String getSenhaByNomeOrId(@Param("p1") Long id, @Param("p2") String login);

}
