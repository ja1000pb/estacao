package br.com.mundobitinfo.estacao.resources;

import java.time.LocalDateTime;
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

import br.com.mundobitinfo.estacao.controller.IndicadoresCtrl;
import br.com.mundobitinfo.estacao.model.Cliente;
import br.com.mundobitinfo.estacao.model.Indicadores;
import br.com.mundobitinfo.estacao.repository.IndicadoresRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Indicadores")
public class IndicadoresRecource {
    @Autowired
	private ApplicationEventPublisher publisher;
    @Autowired
	private IndicadoresRepository indicadoresRepository;
   	@Autowired
	private IndicadoresCtrl ec;

    @PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Indicadores>> pesquisar(@RequestBody LocalDateTime dtindicadores) {
		return ResponseEntity.ok(ec.pesquisar(dtindicadores));
	}

    @PostMapping("/pesquisarestacao")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Indicadores>> pesquisarestacao(@RequestBody Long id) {
		return ResponseEntity.ok(ec.pesquisarporestacao(id));
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Indicadores>> pesquisa() {
		return ResponseEntity.ok(ec.pesquisa());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public ResponseEntity<Indicadores> buscarPeloCodigo(@PathVariable Long id) {
		Indicadores indicadores = indicadoresRepository.findById(id).get();
		return indicadores != null ? ResponseEntity.ok(indicadores) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public ResponseEntity<Indicadores> salvar(@RequestBody Indicadores indicadores, HttpServletResponse response) {
		Indicadores indicadoresSalvo = indicadoresRepository.save(indicadores);
		return ResponseEntity.status(HttpStatus.CREATED).body(indicadoresSalvo);
	}

	@PostMapping("/deletar")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public @ResponseBody ResponseEntity<Indicadores> remover(@RequestBody Indicadores indicadores) {
		indicadoresRepository.deleteById(indicadores.getId());
		return ResponseEntity.ok(indicadores);
	}

	@PostMapping("/editarativo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public @ResponseBody ResponseEntity<Indicadores> atualizaativo(@RequestBody Indicadores indicadores) {
		Indicadores indicadoresSalvo = ec.atualizar(indicadores.getId(), indicadores);
		indicadoresSalvo.setAtivo(false);
		return ResponseEntity.ok(indicadoresSalvo);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public @ResponseBody ResponseEntity<Indicadores> atualizar(@PathVariable Long id, @Valid @RequestBody Indicadores indicadores) {
		Indicadores indicadoresSalvo = ec.atualizar(id, indicadores);
		return ResponseEntity.ok(indicadoresSalvo);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		ec.atualizarPropriedadeAtivo(id, ativo);
	}


	@PostMapping("/pesqcli")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public ResponseEntity<Indicadores> pesqitem(@RequestBody Long id) {
		return ResponseEntity.ok(indicadoresRepository.findById(id).get());
	}


}

