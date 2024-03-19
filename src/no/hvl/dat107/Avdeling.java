package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "avdeling", schema = "oblig_3")
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer avdelingsid;
	public String navn;
	public int sjefsid;
	
	
	@OneToMany (mappedBy = "avdeling", fetch = FetchType.EAGER )
	private List<Ansatt> ansatte;

	public Avdeling() {

	}

	public Avdeling(Integer avdelingsid, String navn, int sjefsid) {
		this.avdelingsid = avdelingsid;
		this.navn = navn;
		this.sjefsid = sjefsid;
	}

	public Integer getAvdelingsid() {
		return avdelingsid;
	}

	public void setAvdelingsid(Integer avdelingsid) {
		this.avdelingsid = avdelingsid;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Integer getSjefsid() {
		return sjefsid;
	}

	public void setSjefsid(Integer sjefsid) {
		this.sjefsid = sjefsid;
	}
	
	@Override
	public String toString() {
		String s = "Avdeling [avdelingsid=" + avdelingsid + ", navn=" + navn + ", sjefsId=" + sjefsid + "]\n";
		for(Ansatt a : ansatte) {
			s+= "\t" + a;
		}
		
		return s;
	}
}
