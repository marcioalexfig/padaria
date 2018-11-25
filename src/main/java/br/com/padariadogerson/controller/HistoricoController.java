package br.com.padariadogerson.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.padariadogerson.modelo.Historico;
import br.com.padariadogerson.response.Response;
import br.com.padariadogerson.service.HistoricoService;



@RestController
@RequestMapping("/api")
public class HistoricoController {
	
	@Autowired
	private HistoricoService historicoService;
	
	
	/**
	 * Retorna historico
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping (value = "/historicos")
	public ResponseEntity retornarhistoricos( )	{
		
		Response<ArrayList<Historico>> resposta = new Response<ArrayList<Historico>>();
		List<String> erros = new ArrayList<String>();
		ArrayList<Historico> historicoCadastrada = null;
		
		try {
			
			historicoCadastrada = historicoService.consultarHistoricos();
			if (historicoCadastrada==null) {
				resposta.setErr_code("002");
				resposta.setErr_description("historicoS NAO ENCONTRADAS");
				return ResponseEntity.badRequest().body(resposta);
			}

			resposta.setDados(historicoCadastrada);

			
		} catch (Exception e) {
			erros.add(e.getLocalizedMessage());
			erros.forEach(erro -> resposta.getErros().add(erro));
			//para tudo e retorna os erros
			return ResponseEntity.badRequest().body(resposta);
		}
		
		return ResponseEntity.ok().body(resposta);

	}
	
}
