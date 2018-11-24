package br.com.padariadogerson.service;

import java.util.ArrayList;

import br.com.padariadogerson.dto.HistoricoDTO;
import br.com.padariadogerson.modelo.Historico;
import br.com.padariadogerson.repository.HistoricoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

	@Autowired
	private  HistoricoRepository  historicoRepository;
	
	/**
	 * Cadastrar e retornar DTO para o Json/Rest
	 * @param url
	 * @return
	 */
	public  Historico cadastrarHistorico( Historico  historico) {

		//retorno do cadastro
		 Historico historicoCadastrado = historicoRepository.save(historico);
			
		return historicoCadastrado;
		
	}
	/**
	 * Consutar todos os Historicos
	 * @return
	 */
	public ArrayList<Historico> consultarHistoricos() {
		return (ArrayList<Historico>)historicoRepository.findAll();
	}
	
	
}


