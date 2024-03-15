package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AnsattDAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoPersistenceUnit");
	
	
}
