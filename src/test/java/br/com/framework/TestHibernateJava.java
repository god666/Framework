package br.com.framework;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.framework.dao.UsuarioDAO;
import br.com.framework.entidade.Usuario;

public class TestHibernateJava {

	public static void main(String[] args) {
		/*
		 * //Fabrica de EntityManager EntityManagerFactory emf =
		 * Persistence.createEntityManagerFactory("framework");
		 * 
		 * //Gerenciador de Entidades EntityManager em =
		 * emf.createEntityManager();
		 */

		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext(
				"file:/Users/god/workspace/framework/src/main/resources/META-INF/springbeans.xml");

		EntityManagerFactory emf = (EntityManagerFactory) cxt.getBean("entityManagerFactory");

		EntityManager em = emf.createEntityManager();

		// Criando um usuário
		Usuario u = new Usuario();
		u.setNome("Maria");
		u.setLogin("maria");
		u.setSenha("123");

		UsuarioDAO dao = new UsuarioDAO(em);
		dao.salvar(u);

	}

}
