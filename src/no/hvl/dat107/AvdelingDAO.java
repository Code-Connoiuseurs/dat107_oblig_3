package no.hvl.dat107;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AvdelingDAO {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");

	public Avdeling finnAvdelingMedId(Integer avdelingsid) {
		EntityManager em = emf.createEntityManager();

		try {
			String q = "select a from Avdeling as a where a.avdelingsid = :id";
			TypedQuery<Avdeling> tq = em.createQuery(q, Avdeling.class);
			tq.setParameter("id", avdelingsid);
			return tq.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Fant ikke Avdeling med id: " + avdelingsid);
			return null;
		} finally {
			em.close();
		}
	}
}
