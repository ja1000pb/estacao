package br.com.mundobitinfo.estacao.resources;

import java.util.List;

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

import br.com.mundobitinfo.estacao.controller.CulturaCtrl;
import br.com.mundobitinfo.estacao.model.Cultura;
import br.com.mundobitinfo.estacao.repository.CulturaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/culturas")
public class CulturaResource {
    @Autowired
	private ApplicationEventPublisher publisher;
    @Autowired
	private CulturaRepository culturaRepository;
   	@Autowired
	private CulturaCtrl ec;

    @PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CULTURA')")
	public @ResponseBody ResponseEntity<List<Cultura>> pesquisar(@RequestBody String nome) {
		return ResponseEntity.ok(ec.pesquisar(nome));
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CULTURA')")
	public @ResponseBody ResponseEntity<List<Cultura>> pesquisa() {
		return ResponseEntity.ok(ec.pesquisa());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CULTURA')")
	public ResponseEntity<Cultura> buscarPeloCodigo(@PathVariable Long id) {
		Cultura cultura = culturaRepository.findById(id).get();
		return cultura != null ? ResponseEntity.ok(cultura) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA')")
	public ResponseEntity<Cultura> salvar(@RequestBody Cultura cultura, HttpServletResponse response) {
		Cultura culturaSalva = culturaRepository.save(cultura);
		return ResponseEntity.status(HttpStatus.CREATED).body(culturaSalva);
	}

	@PostMapping("/deletar")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA')")
	public @ResponseBody ResponseEntity<Cultura> remover(@RequestBody Cultura cultura) {
		culturaRepository.deleteById(cultura.getId());
		return ResponseEntity.ok(cultura);
	}

	@PostMapping("/editarativo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA')")
	public @ResponseBody ResponseEntity<Cultura> atualizaativo(@RequestBody Cultura cultura) {
		Cultura culturaSalva = ec.atualizar(cultura.getId(), cultura);
		culturaSalva.setAtivo(false);
		return ResponseEntity.ok(culturaSalva);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA')")
	public @ResponseBody ResponseEntity<Cultura> atualizar(@PathVariable Long id, @Valid @RequestBody Cultura cultura) {
		Cultura culturaSalva = ec.atualizar(id, cultura);
		return ResponseEntity.ok(culturaSalva);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		ec.atualizarPropriedadeAtivo(id, ativo);
	}

	@PostMapping("/pesqcul")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CULTURA')")
	public ResponseEntity<Cultura> pesqitem(@RequestBody Long id) {
		return ResponseEntity.ok(culturaRepository.findById(id).get());
	}

}
