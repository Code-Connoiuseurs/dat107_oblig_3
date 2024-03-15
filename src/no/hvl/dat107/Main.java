package no.hvl.dat107;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Main {
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Dette er midlertidig
		while(true) {
			
			System.out.print("Vennligst oppgi brukernavn du vil søke etter: ");
			String sok = scanner.nextLine();
			
			if (sok.equals("q")) {
				System.out.println("Program lukket");
				scanner.close();
				break;
			}
			
			EntityManager em = emf.createEntityManager();
			
			try {
				String q = "select a from Ansatt as a where a.brukernavn = :sok";
				TypedQuery<Ansatt> tq = em.createQuery(q, Ansatt.class);
				tq.setParameter("sok", sok);
				Ansatt res =  tq.getSingleResult();
				System.out.println(res); 
			} catch (NoResultException e) {
				System.out.println("Fant ikke Ansatt med brukernavn: " + sok);
			} finally {
				em.close();
			}
		}
	}
}