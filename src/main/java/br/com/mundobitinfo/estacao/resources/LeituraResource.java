package br.com.mundobitinfo.estacao.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mundobitinfo.estacao.controller.LeituraCtrl;
import br.com.mundobitinfo.estacao.model.Leitura;
import br.com.mundobitinfo.estacao.repository.LeituraRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leituras")
public class LeituraResource {
    
    @Autowired
	private ApplicationEventPublisher publisher;
    @Autowired
	private LeituraRepository leituraRepository;

   	@Autowired
	private LeituraCtrl ec;

    @PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LEITURA')")
	public @ResponseBody ResponseEntity<List<Leitura>> pesquisar(@RequestBody String nome) {
		return ResponseEntity.ok(ec.pesquisar(nome));
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LEITURA')")
	public @ResponseBody ResponseEntity<List<Leitura>> pesquisa() {
		return ResponseEntity.ok(ec.pesquisa());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LEITURA')")
	public ResponseEntity<Leitura> buscarPeloCodigo(@PathVariable Long id) {
		Leitura leitura = leituraRepository.findById(id).get();
		return leitura != null ? ResponseEntity.ok(leitura) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LEITURA')")
	public ResponseEntity<Leitura> salvar(@RequestBody Leitura leitura, HttpServletResponse response) {
		Leitura leituraSalva = leituraRepository.save(leitura);
		return ResponseEntity.status(HttpStatus.CREATED).body(leituraSalva);
	}

	@PostMapping("/deletar")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LEITURA')")
	public @ResponseBody ResponseEntity<Leitura> remover(@RequestBody Leitura leitura) {
		leituraRepository.deleteById(leitura.getId());
		return ResponseEntity.ok(leitura);
	}

	@PostMapping("/editarativo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LEITURA')")
	public @ResponseBody ResponseEntity<Leitura> atualizaativo(@RequestBody Leitura leitura) {
		Leitura leituraSalva = ec.atualizar(leitura.getId(), leitura);
		leituraSalva.setAtivo(false);
		return ResponseEntity.ok(leituraSalva);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LEITURA')")
	public @ResponseBody ResponseEntity<Leitura> atualizar(@PathVariable Long id, @Valid @RequestBody Leitura leitura) {
		Leitura leituraSalva = ec.atualizar(id, leitura);
		return ResponseEntity.ok(leituraSalva);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LEITURA')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		ec.atualizarPropriedadeAtivo(id, ativo);
	}

	@PostMapping("/gravarsessao")
	public @ResponseBody ResponseEntity<Leitura> gravarSessao(@RequestBody Leitura leitura, HttpSession session) {
		session.setAttribute("leitura", leitura);
		return ResponseEntity.ok(leitura);
	}

	@PostMapping("/pesqemp")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LEITURA')")
	public ResponseEntity<Leitura> pesqitem(@RequestBody Long id) {
		return ResponseEntity.ok(leituraRepository.findById(id).get());
	}

}
