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

import br.com.mundobitinfo.estacao.controller.Cultura_clienteCtrl;
import br.com.mundobitinfo.estacao.model.Cultura;
import br.com.mundobitinfo.estacao.model.Cultura_cliente;
import br.com.mundobitinfo.estacao.repository.Cultura_clienteRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/culturas_cliente")
public class Cultura_clienteResource {
    @Autowired
	private ApplicationEventPublisher publisher;
    @Autowired
	private Cultura_clienteRepository cultura_clienteRepository;
   	@Autowired
	private Cultura_clienteCtrl ec;

    @PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CULTURA_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Cultura_cliente>> pesquisar(@RequestBody LocalDateTime dtplantio) {
		return ResponseEntity.ok(ec.pesquisar(dtplantio));
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CULTURA_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Cultura_cliente>> pesquisa() {
		return ResponseEntity.ok(ec.pesquisa());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CULTURA_CLIENTE')")
	public ResponseEntity<Cultura_cliente> buscarPeloCodigo(@PathVariable Long id) {
		Cultura_cliente cultura_cliente = cultura_clienteRepository.findById(id).get();
		return cultura_cliente != null ? ResponseEntity.ok(cultura_cliente) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public ResponseEntity<Cultura_cliente> salvar(@RequestBody Cultura_cliente cultura_cliente, HttpServletResponse response) {
		Cultura_cliente cultura_clienteSalvo = cultura_clienteRepository.save(cultura_cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cultura_clienteSalvo);
	}

	@PostMapping("/deletar")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA_CLIENTE')")
	public @ResponseBody ResponseEntity<Cultura_cliente> remover(@RequestBody Cultura_cliente cultura_cliente) {
		cultura_clienteRepository.deleteById(cultura_cliente.getId());
		return ResponseEntity.ok(cultura_cliente);
	}

	@PostMapping("/editarativo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA_CLIENTE')")
	public @ResponseBody ResponseEntity<Cultura_cliente> atualizaativo(@RequestBody Cultura_cliente cultura_cliente) {
		Cultura_cliente cultura_clienteSalvo = ec.atualizar(cultura_cliente.getId(), cultura_cliente);
		cultura_clienteSalvo.setAtivo(false);
		return ResponseEntity.ok(cultura_clienteSalvo);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA_CLIENTE')")
	public @ResponseBody ResponseEntity<Cultura_cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cultura_cliente cultura_cliente) {
		Cultura_cliente cultura_clienteSalva = ec.atualizar(id, cultura_cliente);
		return ResponseEntity.ok(cultura_clienteSalva);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CULTURA_CLIENTE')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		ec.atualizarPropriedadeAtivo(id, ativo);
	}

	@PostMapping("/pesqcul_cli")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CULTURA_CLIENTE')")
	public ResponseEntity<Cultura_cliente> pesqitem(@RequestBody Long id) {
		return ResponseEntity.ok(cultura_clienteRepository.findById(id).get());
	}
}

