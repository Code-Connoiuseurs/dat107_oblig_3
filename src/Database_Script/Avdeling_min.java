package Database_Script;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avdeling", schema = "oblig3_min")
public class Avdeling_min {
	
	@Id
	private Integer avdeling_id;
	private String navn;
	
	@OneToMany(mappedBy = "avdeling")
	private List<Ansatt_min> ansattListe;
	
	@OneToOne
	@JoinColumn(name = "avdeling_sjef_id")
	private Ansatt_min avdeling_sjef_id;
	
	
	public Avdeling_min() {
	}
	
	public Avdeling_min(Integer avdeling_id, String navn, Ansatt_min avdeling_sjef_id) {
		this.avdeling_id = avdeling_id;
		this.navn = navn;
		this.avdeling_sjef_id = avdeling_sjef_id;
		
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
		return avdeling_sjef_id;
	}
	public void setAvdelingSjef(Ansatt_min avdeling_sjef_id) {
		this.avdeling_sjef_id = avdeling_sjef_id;
	}
	public void skrivUt() {
		
		String sjefInfo = avdeling_sjef_id != null ? "ID: " + avdeling_sjef_id.getAnsattId() + ", Navn: " + avdeling_sjef_id.getFornavn() 
		+ " " + avdeling_sjef_id.getEtternavn() : "Ingen avdeling sjef";
		System.out.println("Avdeling id: " + avdeling_id + "\n"
				+ "Avdeling navn: " + navn + "\n"
				+ "Avdelingssjef: " + sjefInfo);
	}
}
