package br.com.padariadogerson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.padariadogerson.modelo.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{

	
}
