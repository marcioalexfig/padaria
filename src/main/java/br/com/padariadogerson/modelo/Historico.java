package br.com.padariadogerson.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="historico")
public class Historico  implements Serializable{

	private static final long serialVersionUID = -107105654979771299L;
	private Integer id;
	private Conta conta;
	private Date dataPagamento;
	private Double saldoTotalAntes;
	private Double saldoTotalDepois;
	
	public Historico() {}
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="conta_id")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((saldoTotalAntes == null) ? 0 : saldoTotalAntes.hashCode());
		result = prime * result + ((saldoTotalDepois == null) ? 0 : saldoTotalDepois.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historico other = (Historico) obj;
		if (dataPagamento == null) {
			if (other.dataPagamento != null)
				return false;
		} else if (!dataPagamento.equals(other.dataPagamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (saldoTotalAntes == null) {
			if (other.saldoTotalAntes != null)
				return false;
		} else if (!saldoTotalAntes.equals(other.saldoTotalAntes))
			return false;
		if (saldoTotalDepois == null) {
			if (other.saldoTotalDepois != null)
				return false;
		} else if (!saldoTotalDepois.equals(other.saldoTotalDepois))
			return false;
		return true;
	}
	
	

}
