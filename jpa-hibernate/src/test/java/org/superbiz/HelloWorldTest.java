package org.superbiz;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.apache.openejb.core.LocalInitialContextFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HelloWorldTest {
	
	@EJB
	private HelloWorldEjb bean;
	private EJBContainer container;

	@Before
	public void setUp() throws Exception {
		final Properties p = new Properties();
		p.setProperty(Context.INITIAL_CONTEXT_FACTORY, LocalInitialContextFactory.class.getName());
		p.setProperty("ora", "new://Resource?type=javax.sql.DataSource");
		p.setProperty("ora.jdbcDriver", "oracle.jdbc.driver.OracleDriver");
		p.setProperty("ora.jdbcUrl", "jdbc:oracle:thin:@localhost:1521/orcl");
		p.setProperty("ora.userName", "system");
		p.setProperty("ora.password", "oracle");
		
		container = EJBContainer.createEJBContainer(p);
		container.getContext().bind("inject", this);
	}
	
	@After
	public void tearDown() throws Exception {
		container.close();
	}
	
	@Test
	public void testShouldCallOracleStoredProc() throws Exception {
		Assert.assertEquals("Hello World OUT parameter", bean.helloWorld());
	}
	
}
