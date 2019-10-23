package com.amsolucoes.kapita.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amsolucoes.kapita.event.RecursoCriadoEvent;
import com.amsolucoes.kapita.model.Situacao;
import com.amsolucoes.kapita.repository.SituacaoRepository;


@RestController
@RequestMapping("/situacoes")
public class SituacaoResource {
	
	@Autowired
	private SituacaoRepository situacaoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Situacao> listar(){
		return situacaoRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Situacao> criar(@Valid @RequestBody Situacao situacao, HttpServletResponse response) {
		Situacao situacaoSalva = situacaoRepository.save(situacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, situacaoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(situacaoSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Situacao> buscarPeloCodigo(@PathVariable Long codigo) {
		Situacao situacao = situacaoRepository.findById(codigo).orElse(null);
		return situacao != null ? ResponseEntity.ok(situacao) : ResponseEntity.notFound().build();
	}
	
}
