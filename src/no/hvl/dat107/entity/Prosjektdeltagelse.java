package no.hvl.dat107.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prosjektdeltagelse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer arbeidstimer;
	
    @ManyToOne
    @JoinColumn(name="prosjektid")
    private Prosjekt prosjekt;
    
    @ManyToOne
    @JoinColumn(name="ansattid")
    private Ansatt ansatt;
    
    public Prosjektdeltagelse() {
    	
    }
    
    public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
        this.arbeidstimer = 0;
        //Hvis vi gjør dette her slipper vi å gjøre det i DAO
        ansatt.leggTilProsjektdeltagelse(this);
        prosjekt.leggTilProsjektdeltagelse(this);
    }
    
    public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, int arbeidstimer) {
    	this(ansatt, prosjekt);
        this.arbeidstimer = arbeidstimer;
    }
}
