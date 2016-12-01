package br.com.framework;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.framework.entidade.Usuario;

public class TestSpringBeans {

	@Test
	public void testContextoSpring() {
		// TODO Auto-generated method stub

		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext(
				"file:/Users/god/workspace/framework/src/main/webapp/WEB-INF/springbeans.xml");
		//Usuario u = (Usuario) cxt.getBean("usuario");
		
		//System.out.println(u);
		
		BasicDataSource bds = (BasicDataSource) cxt.getBean("basicDataSource");
		System.out.println("===================?"+bds.getPassword());
		
		cxt.close();
	}

}
