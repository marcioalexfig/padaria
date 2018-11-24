package br.com.padariadogerson.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.padariadogerson.dto.EmpresaDTO;
import br.com.padariadogerson.modelo.Empresa;
import br.com.padariadogerson.modelo.Padaria;
import br.com.padariadogerson.repository.EmpresaRepository;


@Service
public class EmpresaService {

	@Autowired
	private  EmpresaRepository  empresaRepository;
	/**
	 * Cadastrar e retornar DTO para o Json/Rest
	 * @param url
	 * @return
	 */
	public  Empresa cadastrarEmpresa( Empresa  empresa) {
		
		//retorno do cadastro
		 Empresa empresaCadastrada = empresaRepository.save(empresa);
			
		return empresaCadastrada;
		
	}
	/**
	 * Consutar todas as Empresas
	 * @return
	 */
	public ArrayList<Empresa> consultarEmpresas() {
		return (ArrayList<Empresa>)empresaRepository.findAll();
	}
	
	public Empresa atualizarEmpresa(Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	/**
	 * Consultar Empres por ID
	 * @param id
	 * @return
	 */
	public Optional<Empresa> consultarEmpresaPorId(Integer id) {
		return empresaRepository.findById(id);
	}
	
}

