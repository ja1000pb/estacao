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

import br.com.mundobitinfo.estacao.controller.EmpresaCtrl;
import br.com.mundobitinfo.estacao.model.Empresa;
import br.com.mundobitinfo.estacao.repository.EmpresaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {

    @Autowired
	private ApplicationEventPublisher publisher;
    @Autowired
	private EmpresaRepository empresaRepository;

   	@Autowired
	private EmpresaCtrl ec;

    @PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EMPRESA')")
	public @ResponseBody ResponseEntity<List<Empresa>> pesquisar(@RequestBody String nome) {
		return ResponseEntity.ok(ec.pesquisar(nome));
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EMPRESA')")
	public @ResponseBody ResponseEntity<List<Empresa>> pesquisa() {
		return ResponseEntity.ok(ec.pesquisa());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EMPRESA')")
	public ResponseEntity<Empresa> buscarPeloCodigo(@PathVariable Long id) {
		Empresa empresa = empresaRepository.findById(id).get();
		return empresa != null ? ResponseEntity.ok(empresa) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EMPRESA')")
	public ResponseEntity<Empresa> salvar(@RequestBody Empresa empresa, HttpServletResponse response) {
		Empresa empresaSalva = empresaRepository.save(empresa);		
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaSalva);

	}

	@PostMapping("/deletar")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EMPRESA')")
	public @ResponseBody ResponseEntity<Empresa> remover(@RequestBody Empresa empresa) {
		empresaRepository.deleteById(empresa.getId());
		return ResponseEntity.ok(empresa);
	}

	@PostMapping("/editarativo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EMPRESA')")
	public @ResponseBody ResponseEntity<Empresa> atualizaativo(@RequestBody Empresa empresa) {
		Empresa empresaSalva = ec.atualizar(empresa.getId(), empresa);
		empresaSalva.setAtivo(false);
		return ResponseEntity.ok(empresaSalva);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EMPRESA')")
	public @ResponseBody ResponseEntity<Empresa> atualizar(@PathVariable Long id, @Valid @RequestBody Empresa empresa) {
		Empresa empresaSalva = ec.atualizar(id, empresa);
		return ResponseEntity.ok(empresaSalva);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EMPRESA')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		ec.atualizarPropriedadeAtivo(id, ativo);
	}

	@PostMapping("/gravarsessao")
	public @ResponseBody ResponseEntity<Empresa> gravarSessao(@RequestBody Empresa empresa, HttpSession session) {
		session.setAttribute("empresa", empresa);
		return ResponseEntity.ok(empresa);
	}

	@PostMapping("/pesqemp")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EMPRESA')")
	public ResponseEntity<Empresa> pesqitem(@RequestBody Long id) {
		return ResponseEntity.ok(empresaRepository.findById(id).get());
	}

	/*                                               NÃO COLOCAR PERMISSOES AQUI*/
	@GetMapping("/imagemempresa/{id}")
	public ResponseEntity<Empresa> getImagemEmpresa(@PathVariable("id")  Long id) {
		Empresa e = new Empresa();
		e.setLogo(ec.getImagemEmpresa(id));
		return ResponseEntity.ok(e) ;
		
	}

}
