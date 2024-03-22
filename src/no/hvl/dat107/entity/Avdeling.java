package no.hvl.dat107.entity;

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
@Table(name = "avdeling", schema = "oblig_3")
public class Avdeling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String navn;

	@OneToOne
	@JoinColumn(name = "sjefsid")
	private Ansatt sjef;
	
	@OneToMany (mappedBy = "avdeling", fetch = FetchType.EAGER )
	private List<Ansatt> ansatte;

	public Avdeling() {

	}

	public Avdeling(String navn, Ansatt sjef) {
		this.navn = navn;
		this.sjef = sjef;
	}

	public Ansatt getSjef() {
		return sjef;
	}

	public void setSjef(Ansatt sjef) {
		this.sjef = sjef;
	}

	public List<Ansatt> getAnsatte() {
		return ansatte;
	}

	public void setAnsatte(List<Ansatt> ansatte) {
		this.ansatte = ansatte;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	@Override
	public String toString() {
		String s = "Avdeling [avdelingsid=" + id + ", navn=" + navn + ", sjefsId=" + sjef.getId() + "]\n";
		for(Ansatt a : ansatte) {
			s+= "\t" + a;
		}
		
		return s;
	}
}
