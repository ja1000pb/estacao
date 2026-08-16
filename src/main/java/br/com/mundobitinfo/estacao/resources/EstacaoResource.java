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

import br.com.mundobitinfo.estacao.controller.EstacaoCtrl;
import br.com.mundobitinfo.estacao.model.Estacao;
import br.com.mundobitinfo.estacao.repository.EstacaoRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estacoes")
public class EstacaoResource {
  
    @Autowired
	private ApplicationEventPublisher publisher;
    @Autowired
	private EstacaoRepository estacaoRepository;

   	@Autowired
	private EstacaoCtrl ec;

        @PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTACAO')")
	public @ResponseBody ResponseEntity<List<Estacao>> pesquisar(@RequestBody String nome) {
		return ResponseEntity.ok(ec.pesquisar(nome));
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTACAO')")
	public @ResponseBody ResponseEntity<List<Estacao>> pesquisa() {
		return ResponseEntity.ok(ec.pesquisa());
	}

    @GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTACAO')")
	public ResponseEntity<Estacao> buscarPeloCodigo(@PathVariable Long id) {
		Estacao estacao = estacaoRepository.findById(id).get();
		return estacao != null ? ResponseEntity.ok(estacao) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTACAO')")
	public ResponseEntity<Estacao> salvar(@RequestBody Estacao estacao, HttpServletResponse response) {
		Estacao estacaoSalva = estacaoRepository.save(estacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(estacaoSalva);
	}

	@PostMapping("/deletar")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTACAO')")
	public @ResponseBody ResponseEntity<Estacao> remover(@RequestBody Estacao estacao) {
		estacaoRepository.deleteById(estacao.getId());
		return ResponseEntity.ok(estacao);
	}

	@PostMapping("/editarativo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTACAO')")
	public @ResponseBody ResponseEntity<Estacao> atualizaativo(@RequestBody Estacao estacao) {
		Estacao estacaoSalva = ec.atualizar(estacao.getId(), estacao);
		estacaoSalva.setAtivo(false);
		return ResponseEntity.ok(estacaoSalva);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTACAO')")
	public @ResponseBody ResponseEntity<Estacao> atualizar(@PathVariable Long id, @Valid @RequestBody Estacao estacao) {
		Estacao estacaoSalva = ec.atualizar(id, estacao);
		return ResponseEntity.ok(estacaoSalva);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTACAO')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		ec.atualizarPropriedadeAtivo(id, ativo);
	}

	@PostMapping("/gravarsessao")
	public @ResponseBody ResponseEntity<Estacao> gravarSessao(@RequestBody Estacao estacao, HttpSession session) {
		session.setAttribute("estacao", estacao);
		return ResponseEntity.ok(estacao);
	}

	@PostMapping("/pesqemp")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTACAO')")
	public ResponseEntity<Estacao> pesqitem(@RequestBody Long id) {
		return ResponseEntity.ok(estacaoRepository.findById(id).get());
	}
}
