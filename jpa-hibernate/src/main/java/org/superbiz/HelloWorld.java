package org.superbiz;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@WebService
public interface HelloWorld {

	public abstract String helloWorld();

}
