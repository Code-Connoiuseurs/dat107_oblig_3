package Database_Script;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AvdelingDAO_min {

	private static final EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("persistenceAnsattUnit");
	}
	public Avdeling_min finnAvdelingMedId(int id) {
		EntityManager em = emf.createEntityManager();
		
		try {
			String s = "SELECT b FROM Avdeling_min b WHERE b.avdeling_id = :id";
			TypedQuery<Avdeling_min> query = em.createQuery(s, Avdeling_min.class);
			query.setParameter("id", id);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			System.out.println("Ingen avdeling med id: " + id);
				return null;
		} finally {
		 em.close();
		}
	}
}
