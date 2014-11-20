package org.superbiz;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@LocalBean
@WebService(endpointInterface = "org.superbiz.HelloWorld")
public class HelloWorldEjb implements HelloWorld {

	@PersistenceContext(name = "test-unit")
	private EntityManager em;
	
	@Override
	@Path("/rest")
	@GET
	public String helloWorld() {
		EntityManager entityManager = em;
		
		try {
			Class<?> hemCls = Class.forName("org.hibernate.ejb.HibernateEntityManager");
			entityManager = (EntityManager) em.unwrap(hemCls);
		} catch (Exception e) {
			throw new RuntimeException("Unable to unwrap hibernate EM");
		}
		
		StoredProcedureQuery storedProcQuery = entityManager.createStoredProcedureQuery("TESTPKG.HelloWorld");
		storedProcQuery.registerStoredProcedureParameter("outParam1", String.class, ParameterMode.OUT);
		storedProcQuery.execute();
		
		String helloWorld = (String) storedProcQuery.getOutputParameterValue("outParam1");
		return helloWorld;
	}
	
}
