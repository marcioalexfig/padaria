package br.com.padariadogerson.dto;

import java.util.Date;

import br.com.padariadogerson.modelo.Padaria;

public class ContaDTO {
	private Integer id;
	private String titulo;
	private Double valor;
	private Padaria padaria;
	private Date dataPagamento;
	private String status;
	
	public ContaDTO() {}
	
	
	public ContaDTO(String titulo, Double valor, Date dataPagamento, String status, Padaria padaria) {
		super();
		this.titulo = titulo;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.status = status;
		this.padaria = padaria;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Padaria getPadaria() {
		return padaria;
	}


	public void setPadaria(Padaria padaria) {
		this.padaria = padaria;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
