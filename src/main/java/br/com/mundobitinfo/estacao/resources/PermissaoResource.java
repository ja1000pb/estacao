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
import br.com.mundobitinfo.estacao.controller.PermissaoCtrl;
import br.com.mundobitinfo.estacao.model.Permissao;
import br.com.mundobitinfo.estacao.repository.EstacaoRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissao")
public class PermissaoResource {
	
    @Autowired
	private PermissaoCtrl permissaoCtrl;

	
	@PostMapping(value = "/pesquisarpermissoes" ,produces = "application/json")
	@PreAuthorize("hasAuthority('permissoes:pesquisar')")
	public @ResponseBody ResponseEntity<List<Permissao>>  pesquisarPermissoes(@RequestBody String nome) {	
		return ResponseEntity.ok(permissaoCtrl.pesquisar(nome));
	}
	
	
	

}
