package Database_Script;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avdeling", schema = "oblig3_min")
public class Avdeling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer avdeling_id;
	private String navn;
	
	@OneToOne
	@JoinColumn(name = "avdeling_sjef_id")
	private Ansatt avdelingSjef;
	
	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
	private List<Ansatt> ansatte;
	
	public Avdeling() {
		
	}
	
	public Avdeling(String navn, Ansatt avdelingSjef) {
		this.navn = navn;
		this.avdelingSjef = avdelingSjef;
	}
	public Integer getAvdelingId() {
		return avdeling_id;
	}
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public Ansatt getAvdelingSjef() {
		return avdelingSjef;
	}
	public void setAvdelingSjef(Ansatt avdelingSjef) {
		this.avdelingSjef = avdelingSjef;
	} 
	
	public List<Ansatt> getAnsatte() {
		return ansatte;
	}
	public void leggTil(Ansatt ansatt) {
		ansatte.add(ansatt);
		ansatt.setAvdeling(this);
	}
	public void fjern(Ansatt ansatt) {
		ansatte.remove(ansatt);
		ansatt.setAvdeling(null);
	}
	
	@Override
	public String toString() {
		return "Avdeling ID: " + avdeling_id + "\n" + "Avdeling navn: " 
		+ navn + "\n" + "Avdeling sjef: " + "Brukernavn " + avdelingSjef.getBrukernavn() 
		+ ", Navn: " + avdelingSjef.getFornavn() + " " + avdelingSjef.getEtternavn() + ", ID: " + avdelingSjef.getAnsattId();
		
	}
}
