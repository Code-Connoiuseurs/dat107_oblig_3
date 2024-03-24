package Database_Script;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AnsattDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceAnsattUnit");

	public Ansatt finnAnsattMedId(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
	}

	public Ansatt finnAnsattMedBNavn(String brukernavn) {
		EntityManager em = emf.createEntityManager();

		try {
			String s = "SELECT a FROM Ansatt a WHERE lower(a.brukernavn) = :brukernavn";
			TypedQuery<Ansatt> query = em.createQuery(s, Ansatt.class);
			query.setParameter("brukernavn", brukernavn.toLowerCase());
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}

	public List<Ansatt> utlistingAvAlleAnsatt() {
		EntityManager em = emf.createEntityManager();

		try {

			String alle = "SELECT a FROM Ansatt as a";
			TypedQuery<Ansatt> query = em.createQuery(alle, Ansatt.class);
			return query.getResultList();

		} finally {
			em.close();
		}
	}

	public boolean oppdaterStilling(String nyStilling, Integer id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt managedAnsatt = em.find(Ansatt.class, id);
			if (managedAnsatt != null) {
				managedAnsatt.setStilling(nyStilling);
				tx.commit();
				return true;
			} else {
				return false;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
			return false;
		} finally {
			em.close();
		}
	}

	public boolean oppdaterLonn(Double nyLonn, Integer id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt managedAnsatt = em.find(Ansatt.class, id);
			if (managedAnsatt != null) {
				managedAnsatt.setMaanedslonn(nyLonn);
				tx.commit();
				return true;
			} else {
				return false;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
			return false;
		} finally {
			em.close();
		}
	}
	
	public boolean leggTilAnsatt(Ansatt nyAnsatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			em.persist(nyAnsatt);
			
			tx.commit();
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
			return false;
		} finally {
			em.close();
		}
	}
}
