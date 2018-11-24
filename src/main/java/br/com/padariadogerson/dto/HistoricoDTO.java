package br.com.padariadogerson.dto;

import java.util.Date;

import br.com.padariadogerson.modelo.Conta;

public class HistoricoDTO {
	private Integer id;
	private Conta conta;
	private Date dataPagamento;
	private Double saldoTotalAntes;
	private Double saldoTotalDepois;
	
	public HistoricoDTO() {}
	public HistoricoDTO(Integer id, Conta conta, Date dataPagamento, Double saldoTotalAntes, Double saldoTotalDepois) {
		this.id = id;
		this.conta = conta;
		this.dataPagamento = dataPagamento;
		this.saldoTotalAntes = saldoTotalAntes;
		this.saldoTotalDepois = saldoTotalDepois;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Double getSaldoTotalAntes() {
		return saldoTotalAntes;
	}
	public void setSaldoTotalAntes(Double saldoTotalAntes) {
		this.saldoTotalAntes = saldoTotalAntes;
	}
	public Double getSaldoTotalDepois() {
		return saldoTotalDepois;
	}
	public void setSaldoTotalDepois(Double saldoTotalDepois) {
		this.saldoTotalDepois = saldoTotalDepois;
	}
	
	
	

}
