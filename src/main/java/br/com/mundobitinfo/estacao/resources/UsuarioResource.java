package br.com.mundobitinfo.estacao.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mundobitinfo.estacao.controller.UsuarioCtrl;
import br.com.mundobitinfo.estacao.model.Usuario;
import br.com.mundobitinfo.estacao.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
    @Autowired
	private UsuarioRepository usuarioRepository;


	@Autowired
	private UsuarioCtrl usuarioCtrl;

	@PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('usuario:pesquisar')")
	public ResponseEntity<List<Usuario>>  pesquisar(@RequestBody String nome) {		
		if ( (nome == null) || (nome.trim().equals("")) || nome.trim().equals(" ") || nome.trim().equals("+=") ) {
			//return ResponseEntity.ok(empresaRepository.findAll());
			return ResponseEntity.ok(usuarioRepository.findByAtivoIsTrue());
		} else {
			return ResponseEntity.ok(usuarioRepository.findByNomeContaining(nome));
		}		
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('usuario:pesquisar')")
	public ResponseEntity<List<Usuario>>  pesquisa(@RequestBody String nome) {		
		if ( (nome == null) || (nome.trim().equals("")) || nome.trim().equals(" ") || nome.trim().equals("+=") ) {
			return ResponseEntity.ok(usuarioRepository.findTop10ByAtivoIsTrueOrderByIdDesc());
		} else {
			return ResponseEntity.ok(usuarioRepository.findByNomeContaining(nome));
		}		
	}
	
	@PostMapping("/byusuario")
	@PreAuthorize("hasAuthority('usuario:pesquisar')")
	public ResponseEntity<Optional<Usuario>>  byusuario(@RequestBody String nome) {	
			return ResponseEntity.ok(usuarioRepository.findByNome(nome));
				
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('usuario:pesquisar')")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('usuario:salvar')")
	public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
		usuarioCtrl.salvar(usuario);
		return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('usuario:salvar')")
	public void remover(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
	}
	
	/*          NUNCA COLOCAR PERMISSAO DE ACESSO AQUI POIS TODO USUARIO PODE ALTERAR E TER ACESSO A SUA SENHA */
	@PostMapping("/alterarsenha")
	public ResponseEntity<Usuario> alterarSenha(@RequestBody Usuario usuario) {
		usuarioCtrl.salvar(usuario);;
		return ResponseEntity.ok(usuario);
	}
	@PostMapping("/confirmasenha")
	public ResponseEntity<Boolean> confirmaSenha(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(Boolean.valueOf(usuarioCtrl.confirmarSenha(usuario))) ;
		
	}
	
	@PostMapping("/esqueceusenha")
	public ResponseEntity<Boolean> esqueceuSenha(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(Boolean.valueOf(usuarioCtrl.esqueceuSenha(usuario))) ;
		
	}
}
