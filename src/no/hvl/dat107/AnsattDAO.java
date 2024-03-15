package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AnsattDAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");

	/**
	 * Søker databasen for en ansatt basert på id
	 * @return En Ansatt eller null
	 */
	public Ansatt finnAnsattMedId(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			String q = "select a from Ansatt as a where a.id = :id";
			TypedQuery<Ansatt> tq = em.createQuery(q, Ansatt.class);
			tq.setParameter("id", id);
			return tq.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Fant ikke Ansatt med id: " + id);
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Søker databasen for en ansatt basert på brukernavn
	 * @return En Ansatt eller null
	 */
	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
		EntityManager em = emf.createEntityManager();

		try {
			String q = "select a from Ansatt as a where a.brukernavn = :brukernavn";
			TypedQuery<Ansatt> tq = em.createQuery(q, Ansatt.class);
			tq.setParameter("brukernavn", brukernavn.toLowerCase());
			return tq.getSingleResult();
		} catch (EntityNotFoundException | NoResultException e) {
			System.out.println("Fant ikke Ansatt med brukernavn: " + brukernavn);
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Søker databasen for alle ansatte
	 * @return En liste med Ansatte eller en tom liste
	 */
	public List<Ansatt> hentAlleAnsatte() {
		EntityManager em = emf.createEntityManager();

		try {
			String q = "select a from Ansatt as a";
			TypedQuery<Ansatt> tq = em.createQuery(q, Ansatt.class);
			return tq.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Oppdaterer en ansatt sin stilling
	 */
	public boolean oppdaterStilling(Integer id, String nyStilling) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Ansatt managedAnsatt = em.find(Ansatt.class, id);
			managedAnsatt.setStilling(nyStilling);
			
			tx.commit();
			return true;
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
	
	/**
	 * Oppdaterer en ansatt sin lønn
	 */
	public boolean oppdaterLonn(Integer id, Double nylonn) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Ansatt managedAnsatt = em.find(Ansatt.class, id);
			managedAnsatt.setMaanedslonn(nylonn);
			
			tx.commit();
			return true;
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

	/**
	 * Lagrer en ny ansatt
	 */
	public boolean lagreNyAnsatt(Ansatt nyAnsatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			em.persist(nyAnsatt);
			
			tx.commit();
			return true;
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
	
}
