package br.com.padariadogerson.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.padariadogerson.modelo.Padaria;
import br.com.padariadogerson.response.Response;
import br.com.padariadogerson.service.PadariaService;


@RestController
@RequestMapping("/api")
public class PadariaController {
	
	@Autowired
	private PadariaService padariaService;
	
	/**
	 * Retorna Padaria
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping (value = "/padarias/{id}")
	public ResponseEntity retornarPadaria( @RequestBody @PathVariable("id") Integer id)	{
		
		Response<Optional<Padaria>> resposta = new Response<Optional<Padaria>>();
		List<String> erros = new ArrayList<String>();
		Optional<Padaria> padariaCadastrada = null;
		
		try {
			
			//verifica se existe na base de dados
			if (id!=null) {
				padariaCadastrada = padariaService.consultarPadariaPorId(id);
				if (padariaCadastrada==null) {
					resposta.setErr_code("002");
					resposta.setErr_description("Padaria NAO ENCONTRADA");
					return ResponseEntity.badRequest().body(resposta);
				}
			}

			//caso exista
			resposta.setDados(padariaCadastrada);

			
		} catch (Exception e) {
			erros.add(e.getLocalizedMessage());
			erros.forEach(erro -> resposta.getErros().add(erro));
			//para tudo e retorna os erros
			return ResponseEntity.badRequest().body(resposta);
		}
		
		return ResponseEntity.ok().body(resposta);

	}
	/**
	 * Retorna Padaria
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping (value = "/padarias")
	public ResponseEntity retornarPadarias( )	{
		
		Response<ArrayList<Padaria>> resposta = new Response<ArrayList<Padaria>>();
		List<String> erros = new ArrayList<String>();
		ArrayList<Padaria> padariaCadastrada = null;
		
		try {
			
			padariaCadastrada = padariaService.consultarPadarias();
			if (padariaCadastrada==null) {
				resposta.setErr_code("002");
				resposta.setErr_description("PadariaS NAO ENCONTRADAS");
				return ResponseEntity.badRequest().body(resposta);
			}

			resposta.setDados(padariaCadastrada);

			
		} catch (Exception e) {
			erros.add(e.getLocalizedMessage());
			erros.forEach(erro -> resposta.getErros().add(erro));
			//para tudo e retorna os erros
			return ResponseEntity.badRequest().body(resposta);
		}
		
		return ResponseEntity.ok().body(resposta);

	}
	
}
