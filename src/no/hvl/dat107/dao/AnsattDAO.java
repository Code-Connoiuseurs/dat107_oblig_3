package no.hvl.dat107.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class AnsattDAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");

	/**
	 * Søker databasen for en ansatt basert på id
	 * 
	 * @return En Ansatt eller null
	 */
	public Ansatt finnAnsattMedId(Integer id) {
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
	 * 
	 * @return En Ansatt eller null
	 */
	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
		EntityManager em = emf.createEntityManager();

		try {
			String q = "select a from Ansatt as a where a.brukernavn = :brukernavn";
			TypedQuery<Ansatt> tq = em.createQuery(q, Ansatt.class);
			tq.setParameter("brukernavn", brukernavn.toLowerCase());
			return tq.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Fant ikke Ansatt med brukernavn: " + brukernavn);
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Søker databasen for alle ansatte
	 * 
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

	/**
	 * Registrerer prosjektdeltagelse
	 */
	public boolean registrerProsjektdeltagelse(int ansattId, int prosjektId, String rolle) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansattId);
			Prosjekt p = em.find(Prosjekt.class, prosjektId);
			if (p == null) {
				System.out.println("Prosjekt med id " + prosjektId + " finnes ikke.");
				return false;
			}
			Prosjektdeltagelse tidligerePd = finnProsjektdeltagelse(ansattId, prosjektId);
			if (tidligerePd != null) {
				System.out.println("Ansatt med id " + ansattId + " deltar allerede på dette prosjektet.");
				return false;
			}
			Prosjektdeltagelse pd = new Prosjektdeltagelse(a, p, 0, rolle);

			em.persist(pd);

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
	 * Finner prosjektdeltagelse
	 */
	private Prosjektdeltagelse finnProsjektdeltagelse(Integer ansattId, Integer prosjektId) {

		String queryString = "SELECT pd FROM Prosjektdeltagelse pd "
				+ "WHERE pd.ansatt.id = :ansattId AND pd.prosjekt.id = :prosjektId";

		EntityManager em = emf.createEntityManager();

		Prosjektdeltagelse pd = null;
		try {
			TypedQuery<Prosjektdeltagelse> query = em.createQuery(queryString, Prosjektdeltagelse.class);
			query.setParameter("ansattId", ansattId);
			query.setParameter("prosjektId", prosjektId);
			pd = query.getSingleResult();

		} catch (NoResultException e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return pd;
	}

	/**
	 * Oppdater hvilken avdeling en ansatt jobber ved. Lar deg ikke endre avdeling på ansatte som også er sjef.
	 */

	public boolean oppdaterAnsattSinAvdeling(Integer id, Integer avdelingsid) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Avdeling nyAvdeling = em.find(Avdeling.class, avdelingsid);

			Ansatt managedAnsatt = em.find(Ansatt.class, id);
			Avdeling tidligereAvdeling = em.find(Avdeling.class, managedAnsatt.getAvdeling().getId());

			if (managedAnsatt.getId() != tidligereAvdeling.getSjef().getId()) {
				managedAnsatt.setAvdeling(nyAvdeling);
			} else {
				System.out.println("Du kan ikke endre en sjef sin avdeling.");
				return false;
			}
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
	
	public boolean oppdaterTimer(Integer ansattid, Integer prosjektid, Integer timer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Prosjektdeltagelse pd = finnProsjektdeltagelse(ansattid, prosjektid);
			Prosjektdeltagelse managedPd = em.merge(pd);
			managedPd.setArbeidstimer(timer);

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
