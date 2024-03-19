package no.hvl.dat107;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "oblig_3")
public class Ansatt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String brukernavn;
	public String fornavn;
	public String etternavn;
	public LocalDate ansettelsesdato;
	public String stilling;
	public Double maanedslonn;
	public int avdelingsid;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "avdelingsid")
	private Avdeling avdeling;

	public Ansatt() {
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelsesdato, String stilling,
			Double maanedslonn, int avdelingsid) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesdato = ansettelsesdato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avdelingsid = avdelingsid;
	}

	public Integer getId() {
		return id;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsettelsesdato() {
		return ansettelsesdato;
	}

	public void setAnsettelsesdato(LocalDate ansettelsesdato) {
		this.ansettelsesdato = ansettelsesdato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public Double getMaanedslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(Double maanedslonn) {
		this.maanedslonn = maanedslonn;
	}

	@Override
	public String toString() {
		return "Ansatt [id=" + id + ", brukernavn=" + brukernavn + ", fornavn=" + fornavn + ", etternavn=" + etternavn
				+ ", annsettelsesdato=" + ansettelsesdato + ", stilling=" + stilling + ", maanedslonn=" + maanedslonn
				+"]\n" ;
	};

}
