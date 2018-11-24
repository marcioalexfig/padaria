package br.com.padariadogerson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.padariadogerson.modelo.Conta;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

	
}
