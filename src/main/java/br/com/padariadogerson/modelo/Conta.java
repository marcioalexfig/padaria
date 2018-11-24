package br.com.padariadogerson.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name="conta")
public class Conta  implements Serializable{


	private static final long serialVersionUID = 1964541749264752050L;
	private Integer id;
	private String titulo;
	private Double valor;
	private Padaria padaria;
	private Date dataPagamento;
	private String status;
	private List<Historico> historicoCollection;
	
	public Conta() {}
	
	@ManyToOne
	@JoinColumn(name="padaria_id")
	public Padaria getPadaria() {
		return padaria;
	}

	public void setPadaria(Padaria padaria) {
		this.padaria = padaria;
	}
	
	@Id
	@Column
	@GeneratedValue (strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Column
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	@Column
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	@Column
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@OneToMany(mappedBy="conta")
	public List<Historico> getHistoricoCollection() {
		return historicoCollection;
	}

	public void setHistoricoCollection(List<Historico> historicoCollection) {
		this.historicoCollection = historicoCollection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Conta other = (Conta) obj;
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	
	
	
	
	
	

}
