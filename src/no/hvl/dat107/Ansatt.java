package no.hvl.dat107;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	public LocalDate annsettelsesdato;
	public String stilling;
	public Double maanedslonn;

	public Ansatt() {
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate annsettelsesdato, String stilling,
			Double maanedslonn) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.annsettelsesdato = annsettelsesdato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
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

	public LocalDate getAnnsettelsesdato() {
		return annsettelsesdato;
	}

	public void setAnnsettelsesdato(LocalDate annsettelsesdato) {
		this.annsettelsesdato = annsettelsesdato;
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
				+ ", annsettelsesdato=" + annsettelsesdato + ", stilling=" + stilling + ", maanedslonn=" + maanedslonn
				+ "]";
	};

}
