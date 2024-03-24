package Database_Script;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "oblig3_min")
public class Ansatt {

		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ansatt_id;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansettelsesdato;
	private String stilling;
	private double maanedslonn;
	private Integer avdeling_id;

	public Ansatt() {

	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelsesdato, String stilling,
			double maanedslonn, Integer avdeling_id) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesdato = ansettelsesdato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avdeling_id = avdeling_id;
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

	public LocalDate getAnsettelsesDato() {
		return ansettelsesdato;
	}

	public void setAnsettelsesDato(LocalDate ansettelsesdato) {
		this.ansettelsesdato = ansettelsesdato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public double getMaandeslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(Double maanedslonn) {
		this.maanedslonn = maanedslonn;
	}

	public Integer getAvdelingId() {
		return avdeling_id;
	}

	public void setAvdelingId(Integer avdeling_id) {
		this.avdeling_id = avdeling_id;
	}

	@Override
	public String toString() {
		return "Ansatt ID: " + ansatt_id + "\n" + "Brukernavn: " + brukernavn + "\n" + "Etternavn: " + etternavn + "\n"
				+ "Ansettelsesdato: " + ansettelsesdato + "\n" + "Stilling: " + stilling + "\n" + "Månedslønn: "
				+ maanedslonn + "\n" + "Avdeling ID: " + avdeling_id;
	}
}
