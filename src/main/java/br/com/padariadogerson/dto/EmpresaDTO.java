package br.com.padariadogerson.dto;

public class EmpresaDTO {

	private String nome;
	private Double saldoTotal;
	private Integer id;
	public EmpresaDTO() {}
	public EmpresaDTO(String nome, Double saldoTotal, Integer id) {
		this.nome = nome;
		this.saldoTotal = saldoTotal;
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getSaldoTotal() {
		return saldoTotal;
	}
	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
