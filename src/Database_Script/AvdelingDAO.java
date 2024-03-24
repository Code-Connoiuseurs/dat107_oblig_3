package Database_Script;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AvdelingDAO {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
	
	public Avdeling finnAvdelingMedId(int id) {
		EntityManager em = emf.createEntityManager();
		
		try {
			return em.find(Avdeling.class, id);
		
		} catch (NoResultException e) {
			System.out.println("Fant ikke avdeling med id: " + id);
			return null;
		} finally {
			em.close();
		}
	}
	public List<Ansatt> utlistingAvAlleIAvdeling(int avdelingId) {
		EntityManager em = emf.createEntityManager();
		
		try {
			Avdeling avdeling = em.find(Avdeling.class, avdelingId);
			if(avdeling == null) {
				System.out.println("Fant ikke avdeling med id" + avdelingId);
				return Collections.emptyList();
			}
			
			return avdeling.getAnsatte();
		} catch (NoResultException e) {
			System.out.println("Fant ikke avdeling med id: " + avdelingId);
			return null;
		} finally {
			em.close();
		}
	}
	public void OppdaterAvdeling(Avdeling oppdaterAvdeling) {
		EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
		try {
			tx.begin();
			
			em.merge(oppdaterAvdeling);
			
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
