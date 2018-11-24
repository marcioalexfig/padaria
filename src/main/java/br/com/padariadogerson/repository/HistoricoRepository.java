package br.com.padariadogerson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.padariadogerson.modelo.Historico;


@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Integer>{

	
}
