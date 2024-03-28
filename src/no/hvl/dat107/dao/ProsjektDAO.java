package no.hvl.dat107.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class ProsjektDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");

	public Prosjekt finnProsjektMedId(int id) {

		EntityManager em = emf.createEntityManager();

		Prosjekt prosjekt = null;
		try {
			prosjekt = em.find(Prosjekt.class, id);
		} finally {
			em.close();
		}
		return prosjekt;
	}

	public boolean lagreNyttProsjekt(Prosjekt nyttProsjekt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			em.persist(nyttProsjekt);

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

	public List<Prosjektdeltagelse> hentAlleProsjektdeltagelser() {
		EntityManager em = emf.createEntityManager();

		try {
			String q = "select p from Prosjektdeltagelse as p";
			TypedQuery<Prosjektdeltagelse> tq = em.createQuery(q, Prosjektdeltagelse.class);
			return tq.getResultList();
		} finally {
			em.close();
		}
	}

	public List<Prosjekt> hentAlleProsjekt() {
		EntityManager em = emf.createEntityManager();

		try {
			String q = "select p from Prosjekt as p";
			TypedQuery<Prosjekt> tq = em.createQuery(q, Prosjekt.class);
			return tq.getResultList();
		} finally {
			em.close();
		}
	}

	public String summerArbeidstimer(Integer prosjektid) {
		String s = "Antall arbeidstimer totalt for prosjekt med id " + prosjektid + ": ";
		EntityManager em = emf.createEntityManager();

		try {
			String q = "select SUM(p.arbeidstimer) from Prosjektdeltagelse p where p.prosjekt.id = :prosjektid";
			Query query = em.createQuery(q, Prosjekt.class);
			query.setParameter("prosjektid", prosjektid);
			s += query.getSingleResult();
		} finally {
			em.close();
		}
		return "\n" + s + "\n\n";
	}
}
