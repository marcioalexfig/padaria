package br.com.padariadogerson.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table (name="empresa")
public class Empresa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6216198801342097819L;
	private String nome;
	private Double saldoTotal;
	private Integer id;
	private List<Padaria> padariaCollection;
	

	
	public Empresa() {
		// TODO Auto-generated constructor stub
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
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy="empresa")
	public List<Padaria> getPadariaCollection() {
		return padariaCollection;
	}
	public void setPadariaCollection(List<Padaria> padariaCollection) {
		this.padariaCollection = padariaCollection;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((saldoTotal == null) ? 0 : saldoTotal.hashCode());
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (saldoTotal == null) {
			if (other.saldoTotal != null)
				return false;
		} else if (!saldoTotal.equals(other.saldoTotal))
			return false;
		return true;
	}

}
