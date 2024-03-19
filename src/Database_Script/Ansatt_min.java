package Database_Script;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "oblig3_min")
public class Ansatt_min {

	@Id
	private Integer ansatt_id;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansett_dato;
	private String stilling;
	private Double maanedslonn;
	private String avdeling_id;

	public Ansatt_min() {
	}

	public Ansatt_min(String brukernavn, String fornavn, String etternavn, LocalDate ansett_dato, String stilling,
			Double maanedslonn, String avdeling) {
		
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansett_dato = ansett_dato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avdeling_id = avdeling;
	}

	public Integer getAnsattId() {
		return ansatt_id;
	}

	public void setAnsattId(Integer ansattId) {
		this.ansatt_id = ansattId;
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

	public LocalDate getaAnsettDato() {
		return ansett_dato;
	}

	public void setAnsettDato(LocalDate ansettDato) {
		this.ansett_dato = ansettDato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public double getMndLonn() {
		return maanedslonn;
	}

	public void setMaanedsLonn(double maanedsLonn) {
		this.maanedslonn = maanedsLonn;
	}

	public String getAvdeling() {
		return avdeling_id;
	}

	public void setAvdeling(String avdeling) {
		this.avdeling_id = avdeling;
	}

	public void skrivUt() {
		System.out.println("Ansatt ID: " + ansatt_id);
		System.out.println("Brukernavn: " + brukernavn);
		System.out.println("Fornavn: " + fornavn);
		System.out.println("Etternavn: " + etternavn);
		System.out.println("Ansettelsesdato: " + ansett_dato);
		System.out.println("Stilling: " + stilling);
		System.out.println("Månedslønn: " + maanedslonn);
		System.out.println("Avdeling: " + avdeling_id);
	}
}
