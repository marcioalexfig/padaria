package br.com.padariadogerson.dto;

public class PadariaDTO {
	private String nome;
	private Integer id;
	private Double saldo;
	
	public PadariaDTO() {}
	public PadariaDTO(String nome, Integer id, Double saldo) {
		super();
		this.nome = nome;
		this.id = id;
		this.saldo = saldo;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	

}
