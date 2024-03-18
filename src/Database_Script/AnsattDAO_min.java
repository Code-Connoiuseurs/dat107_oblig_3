package Database_Script;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AnsattDAO_min {
	
	private static final EntityManagerFactory emf;
	static {
		emf = Persistence.createEntityManagerFactory("persistenceAnsattUnit");
	}
	public Ansatt_min finnAnsattMedId(int id) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			String s = "SELECT a FROM Ansatt_min a WHERE a.ansatt_id = :id ";
			TypedQuery<Ansatt_min> query = em.createQuery(s, Ansatt_min.class);
			query.setParameter("id", id);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			System.out.println("Ingen ansatt med id: " + id + " er funnet");
			return null;
		} finally {	
			em.close();
		}
	}
	public Ansatt_min finnAnsattMedInitial(String brukernavn) {
		
EntityManager em = emf.createEntityManager();
		
		try {
			String s = "SELECT a FROM Ansatt_min a WHERE lower(a.brukernavn) = :brukernavn ";
			TypedQuery<Ansatt_min> query = em.createQuery(s, Ansatt_min.class);
			query.setParameter("brukernavn", brukernavn.toLowerCase());
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			System.out.println("Ingen brukernavn med : " + brukernavn + " har blitt funnet");
			return null;
		} finally {	
			em.close();
		}
	}
}
