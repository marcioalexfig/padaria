package br.com.padariadogerson.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.padariadogerson.dto.ContaDTO;
import br.com.padariadogerson.modelo.Conta;
import br.com.padariadogerson.modelo.Empresa;
import br.com.padariadogerson.modelo.Historico;
import br.com.padariadogerson.modelo.Padaria;
import br.com.padariadogerson.response.Response;
import br.com.padariadogerson.service.ContaService;
import br.com.padariadogerson.service.EmpresaService;
import br.com.padariadogerson.service.HistoricoService;
import br.com.padariadogerson.service.PadariaService;


@RestController
@RequestMapping("/api")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private PadariaService padariaService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private HistoricoService historicoService;
	
	
	/**
	 * Cadastro
	 * @Valid habilita as validações declaradas no DTO
	 * @param 
	 * @param resultadoValidacao  é o retorno com as mensagens de erro das validação de dentro do DTO
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@PostMapping  (value = "/contas")
	public ResponseEntity<Response<ContaDTO>> cadastrarConta(@Valid @RequestBody ContaDTO contaDto, BindingResult resultadoValidacao)	{
		
		System.out.println(contaDto);
		Response<ContaDTO> resposta = new Response<ContaDTO>();
		List<String> erros = new ArrayList<String>();
		Conta newConta = new Conta();
		newConta.setTitulo(contaDto.getTitulo());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(contaDto.getDataPagamento()!=null) {
			Date dataFormatada;
			try {
				dataFormatada = dateFormat.parse(contaDto.getDataPagamento().toString());
				newConta.setDataPagamento(dataFormatada); 
			} catch (ParseException e) {
				//TODO -tratar erro convenientemente;
				e.printStackTrace();
			}
			
		}
		
		newConta.setDataPagamento(contaDto.getDataPagamento());
		newConta.setStatus(contaDto.getStatus());
		newConta.setValor(contaDto.getValor());
		newConta.setPadaria(contaDto.getPadaria());

		
		try {
			//verifica erros de validação (bean validation) e adiciona o que estiver no response
			if (!resultadoValidacao.getAllErrors().isEmpty()) {
				
				resultadoValidacao.getAllErrors().forEach(erro -> resposta.getErros().add(erro.getDefaultMessage()));
				
				//para o processamento e retorna os erros
				return ResponseEntity.badRequest().body(resposta);
			} 
			
			//se não tem erro, chama o serviço e grava na base de dados
			Conta contaCadastrada = contaService.cadastrarConta(newConta);
			contaDto.setDataPagamento(contaCadastrada.getDataPagamento());
			contaDto.setPadaria(contaCadastrada.getPadaria());
			contaDto.setStatus(contaCadastrada.getStatus());
			contaDto.setTitulo(contaCadastrada.getStatus());
			contaDto.setValor(contaCadastrada.getValor());
			resposta.setDados(contaDto);
			
		} catch (Exception e) {
			erros.add(e.getLocalizedMessage());
			erros.forEach(erro -> resposta.getErros().add(erro));
			//para tudo e retorna os erros
			return ResponseEntity.badRequest().body(resposta);
		}

		return ResponseEntity.ok(resposta);
		
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping  (value = "/contas/{id}")
	public ResponseEntity<Response<ContaDTO>> atualizarConta(@Valid @RequestBody ContaDTO contaDto, @PathVariable("id") Integer id, BindingResult resultadoValidacao)	{
		
		//Instant horaChamada = Instant.now(); 
		Response<ContaDTO> resposta = new Response<ContaDTO>();
		List<String> erros = new ArrayList<String>();
		
		try {
			//verifica erros de validação (bean validation) e adiciona o que estiver no response
			if (!resultadoValidacao.getAllErrors().isEmpty()) {
				
				resultadoValidacao.getAllErrors().forEach(erro -> resposta.getErros().add(erro.getDefaultMessage()));
				
				//para o processamento e retorna os erros
				return ResponseEntity.badRequest().body(resposta);
			} 
			
			//se não tem erro, chama o serviço e grava na base de dados
			Optional<Conta> contaCadastrada = contaService.consultarContaPorId(id);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(contaDto.getDataPagamento()!=null) {
				Date dataFormatada = dateFormat.parse(contaDto.getDataPagamento().toString());
				contaCadastrada.get().setDataPagamento(dataFormatada); 
			}
			contaCadastrada.get().setPadaria(contaDto.getPadaria());
			contaCadastrada.get().setStatus(contaDto.getStatus());
			contaCadastrada.get().setTitulo(contaDto.getStatus());
			contaCadastrada.get().setValor(contaDto.getValor());
			Conta contaAtualizada = contaService.atualizarConta(contaCadastrada.get());

			//TODO - atualizar saldo da padaria
			Padaria padariaAtual = (padariaService.consultarPadariaPorId(contaCadastrada.get().getPadaria().getId())).get();
			Double novoSaldo = padariaAtual.getSaldo() - contaAtualizada.getValor();
			padariaAtual.setSaldo(novoSaldo);
			padariaService.atualizarPadaria(padariaAtual);
			
			//TODO - atualizar saldo da empresa
			List<Padaria> padarias = padariaService.consultarPadarias();
			Empresa empresa = (empresaService.consultarEmpresaPorId(padariaAtual.getEmpresa().getId())).get();
			Double saldoAntes = empresa.getSaldoTotal();
			empresa.setSaldoTotal(0d);
			padarias.forEach(padaria -> {
				empresa.setSaldoTotal(empresa.getSaldoTotal() + padaria.getSaldo());
			});
			
			Empresa empresaAtualizada = empresaService.atualizarEmpresa(empresa);
			
			Double saldoDepois = empresa.getSaldoTotal();
			
			//TODO - gravar historico
			Historico historico = new Historico();
			historico.setConta(contaAtualizada);
			historico.setDataPagamento(contaAtualizada.getDataPagamento());
			historico.setSaldoTotalAntes(saldoAntes);
			historico.setSaldoTotalDepois(saldoDepois);
			
			Historico historicoAtualizado = historicoService.cadastrarHistorico(historico);
			
			contaDto.setDataPagamento(contaAtualizada.getDataPagamento());
			contaDto.setId(contaAtualizada.getId()); 
			contaDto.setPadaria(contaAtualizada.getPadaria());
			contaDto.setStatus(contaAtualizada.getStatus());
			contaDto.setTitulo(contaAtualizada.getStatus());
			contaDto.setValor(contaAtualizada.getValor());
			resposta.setDados(contaDto);
			
		} catch (Exception e) {
			erros.add(e.getLocalizedMessage());
			erros.forEach(erro -> resposta.getErros().add(erro));
			//para tudo e retorna os erros
			return ResponseEntity.badRequest().body(resposta);
		}

		return ResponseEntity.ok(resposta);
		
	}
	
	/**
	 * Retorna Conta
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping (value = "/contas/{id}")
	public ResponseEntity retornarConta( @RequestBody @PathVariable("id") Integer id)	{
		
		Response<Optional<Conta>> resposta = new Response<Optional<Conta>>();
		List<String> erros = new ArrayList<String>();
		Optional<Conta> contaCadastrada = null;
		
		try {
			
			//verifica se existe na base de dados
			if (id!=null) {
				contaCadastrada = contaService.consultarContaPorId(id);
				if (contaCadastrada==null) {
					resposta.setErr_code("002");
					resposta.setErr_description("CONTA NAO ENCONTRADA");
					return ResponseEntity.badRequest().body(resposta);
				}
			}

			//caso exista
			resposta.setDados(contaCadastrada);

			
		} catch (Exception e) {
			erros.add(e.getLocalizedMessage());
			erros.forEach(erro -> resposta.getErros().add(erro));
			//para tudo e retorna os erros
			return ResponseEntity.badRequest().body(resposta);
		}
		
		return ResponseEntity.ok().body(resposta);

	}
	/**
	 * Retorna Conta
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping (value = "/contas")
	public ResponseEntity retornarContas( )	{
		
		Response<ArrayList<Conta>> resposta = new Response<ArrayList<Conta>>();
		List<String> erros = new ArrayList<String>();
		ArrayList<Conta> contaCadastrada = null;
		
		try {
			
			contaCadastrada = contaService.consultarContas();
			if (contaCadastrada==null) {
				resposta.setErr_code("002");
				resposta.setErr_description("CONTAS NAO ENCONTRADAS");
				return ResponseEntity.badRequest().body(resposta);
			}

			resposta.setDados(contaCadastrada);

			
		} catch (Exception e) {
			erros.add(e.getLocalizedMessage());
			erros.forEach(erro -> resposta.getErros().add(erro));
			//para tudo e retorna os erros
			return ResponseEntity.badRequest().body(resposta);
		}
		
		return ResponseEntity.ok().body(resposta);

	}
	
}
