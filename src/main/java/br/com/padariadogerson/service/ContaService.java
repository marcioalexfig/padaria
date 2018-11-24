package br.com.padariadogerson.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.padariadogerson.dto.ContaDTO;
import br.com.padariadogerson.modelo.Conta;
import br.com.padariadogerson.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	/**
	 * Cadastrar Conta e retornar DTO para o Json/Rest
	 * @param 
	 * @return
	 */
	public Conta cadastrarConta(Conta conta) {
		//retorno do cadastro
		Conta contaCadastrada = contaRepository.save(conta);
			
		return contaCadastrada;
		
	}
	
	public Conta atualizarConta(Conta contaAtualizada) {
	
		//retorno do cadastro
		Conta contaCadastrada = contaRepository.save(contaAtualizada);
			
		return contaCadastrada;
		
	}
	/**
	 * Consutar todas as contas
	 * @return
	 */
	public ArrayList<Conta> consultarContas() {
		return (ArrayList<Conta>)contaRepository.findAll();
	}
	
	/**
	 * Consultar conta por ID
	 * @param id
	 * @return
	 */
	public Optional<Conta> consultarContaPorId(Integer id) {
		return contaRepository.findById(id);
	}
	
}
