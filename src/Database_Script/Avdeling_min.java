package Database_Script;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avdeling", schema = "oblig3_min")
public class Avdeling_min {
	
	@Id
	private Integer avdeling_id;
	private String navn;
	
	@ManyToOne
	@JoinColumn(name = "avdeling_sjef_id")
	private Ansatt_min avdelingSjef;
	
	public Avdeling_min() {
	}	
	
	public Avdeling_min(Integer avdeling_id, String navn, Ansatt_min avdelingSjef) {
		this.avdeling_id = avdeling_id;
		this.navn = navn;
		this.avdelingSjef = avdelingSjef;
		
	}
	public Integer getAvdelingId() {
		return avdeling_id;
	}
	public void setAvdelingId(Integer avdeling_id) {
		this.avdeling_id = avdeling_id;
	}
	
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public Ansatt_min getAvdelingSjef() {
		return avdelingSjef;
	}
	public void setAvdelingSjef(Ansatt_min avdelingSjef) {
		this.avdelingSjef = avdelingSjef;
	}
	public void skrivUt() {
		
		String sjefInfo = avdelingSjef != null ? "ID: " + avdelingSjef.getAnsattId() + ", Navn: " + avdelingSjef.getFornavn() 
		+ " " + avdelingSjef.getEtternavn() : "Ingen avdeling sjef";
		System.out.println("Avdeling id: " + avdeling_id + "\n"
				+ "Avdeling navn: " + navn + "\n"
				+ "Avdelingssjef: " + sjefInfo);
	}
}
