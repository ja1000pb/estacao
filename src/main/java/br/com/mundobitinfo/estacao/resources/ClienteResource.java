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

import br.com.mundobitinfo.estacao.controller.ClienteCtrl;
import br.com.mundobitinfo.estacao.model.Cliente;
import br.com.mundobitinfo.estacao.repository.ClienteRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    @Autowired
	private ApplicationEventPublisher publisher;
    @Autowired
	private ClienteRepository clienteRepository;
   	@Autowired
	private ClienteCtrl ec;

    @PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Cliente>> pesquisar(@RequestBody String nome) {
		return ResponseEntity.ok(ec.pesquisar(nome));
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Cliente>> pesquisa() {
		return ResponseEntity.ok(ec.pesquisa());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findById(id).get();
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

	@PostMapping("/deletar")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public @ResponseBody ResponseEntity<Cliente> remover(@RequestBody Cliente cliente) {
		clienteRepository.deleteById(cliente.getId());
		return ResponseEntity.ok(cliente);
	}

	@PostMapping("/editarativo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public @ResponseBody ResponseEntity<Cliente> atualizaativo(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = ec.atualizar(cliente.getId(), cliente);
		clienteSalvo.setAtivo(false);
		return ResponseEntity.ok(clienteSalvo);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public @ResponseBody ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente clienteSalvo = ec.atualizar(id, cliente);
		return ResponseEntity.ok(clienteSalvo);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		ec.atualizarPropriedadeAtivo(id, ativo);
	}

	@PostMapping("/gravarsessao")
	public @ResponseBody ResponseEntity<Cliente> gravarSessao(@RequestBody Cliente cliente, HttpSession session) {
		session.setAttribute("cliente", cliente);
		return ResponseEntity.ok(cliente);
	}

	@PostMapping("/pesqcli")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public ResponseEntity<Cliente> pesqitem(@RequestBody Long id) {
		return ResponseEntity.ok(clienteRepository.findById(id).get());
	}


}
