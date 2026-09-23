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

import br.com.mundobitinfo.estacao.controller.PluviometroCtrl;
import br.com.mundobitinfo.estacao.model.Pluviometro;
import br.com.mundobitinfo.estacao.repository.PluviometroRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Pluviometros")

public class PluviometroResource {
    @Autowired
	private ApplicationEventPublisher publisher;
    @Autowired
	private PluviometroRepository pluviometroRepository;
   	@Autowired
	private PluviometroCtrl ec;

    @PostMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Pluviometro>> pesquisar(@RequestBody LocalDateTime dthinicio) {
		return ResponseEntity.ok(ec.pesquisar(dthinicio));
	}

    @PostMapping("/pesquisarestacao")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Pluviometro>> pesquisarestacao(@RequestBody Long id) {
		return ResponseEntity.ok(ec.pesquisarporleitura(id));
	}
	
	@PostMapping("/pesquisa")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public @ResponseBody ResponseEntity<List<Pluviometro>> pesquisa() {
		return ResponseEntity.ok(ec.pesquisa());
	}
}