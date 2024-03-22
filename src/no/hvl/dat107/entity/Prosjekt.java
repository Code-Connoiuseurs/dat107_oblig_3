package no.hvl.dat107.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prosjekt", schema = "oblig_3")

public class Prosjekt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String navn;
	private String beskrivelse;
	@OneToMany(mappedBy = "prosjekt")
	private List<Prosjektdeltagelse> deltagelser;
	
	public Prosjekt() {
	}
	
	public Prosjekt(String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
	}
	
    public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.add(prosjektdeltagelse);
    }

    public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.remove(prosjektdeltagelse);
    }

	public int getId() {
		return id;
	}

	public String getNavn() {
		return navn;
	}

	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}
	@Override
	public String toString() {
		return "Prosjekt [id=" + id + ", navn=" + navn + ", beskrivelse=" + beskrivelse + ", deltagelser=" + deltagelser
				+ "]";
	}
}
