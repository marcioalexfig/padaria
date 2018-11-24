package br.com.padariadogerson.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.padariadogerson.modelo.Padaria;
import br.com.padariadogerson.repository.PadariaRepository;


@Service
public class PadariaService {

	@Autowired
	private  PadariaRepository  padariaRepository;
	
	/**
	 * Consutar todas as Padarias
	 * @return
	 */
	public ArrayList<Padaria> consultarPadarias() {
		return (ArrayList<Padaria>)padariaRepository.findAll();
	}
	
	public Padaria atualizarPadaria(Padaria padaria) {
		return padariaRepository.save(padaria);
	}
	
	/**
	 * Consultar Empres por ID
	 * @param id
	 * @return
	 */
	public Optional<Padaria> consultarPadariaPorId(Integer id) {
		return padariaRepository.findById(id);
	}
	
}


