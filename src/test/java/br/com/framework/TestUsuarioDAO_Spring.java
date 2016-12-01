package br.com.framework;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.dao.DAOException;
import br.com.framework.dao.InterfaceUsuarioDAO;
import br.com.framework.dao.UsuarioDAO;
import br.com.framework.entidade.Usuario;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/springbeans.xml")//Onde está o arquivo de contexto
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TestUsuarioDAO_Spring {
	
	//EntityManager em;
	//ClassPathXmlApplicationContext cxt;
	
	@Autowired
	/*Estou instanciando a classe UsuarioDAO. Sem o Qualifier, irá disparar uma exception
	 * porque existem duas classes que implementam InterfaceUsuarioDAO: UsuarioDAO e 
	 * UsuarioOpcao2DAO*/
	@Qualifier("usuarioDAO")
	InterfaceUsuarioDAO dao;
	
	@Before
	public void init(){
		/*cxt = new ClassPathXmlApplicationContext(
				"file:/Users/god/workspace/framework/src/main/resources/META-INF/springbeans.xml");

		EntityManagerFactory emf = (EntityManagerFactory) cxt.getBean("entityManagerFactory");

		em = emf.createEntityManager();
		
		dao = new UsuarioDAO(em);*/
	}
	
	@After
	public void finish(){
		//cxt.close();
	}
	
	@Transactional
	@Test
	public void testSalvar(){
		Usuario usuario = new Usuario();
		usuario.setNome("TestUsuario");
		usuario.setLogin("testusuario");
		usuario.setSenha("123");
				 
		Usuario retorno = dao.salvar(usuario);
		Assert.assertEquals(usuario.getLogin(), retorno.getLogin());
		Assert.assertEquals(usuario.getNome(), retorno.getNome());
		Assert.assertEquals(usuario.getSenha(), retorno.getSenha());
	}

	@Transactional
	@Test
	public void testBuscarPorId(){
		Usuario usuario = new Usuario();
		usuario.setNome("TestUsuario");
		usuario.setLogin("testusuario");
		usuario.setSenha("123");
		Usuario usuarioSalvo = dao.salvar(usuario);
		
		int id = usuarioSalvo.getId();
		Usuario usuarioBuscado = dao.buscaPorId(id);
		
		//Vai usar o equals
		Assert.assertEquals(usuarioSalvo, usuarioBuscado);
	}
	
	@Test
	@Transactional //Faz com que todo o método ficou fazendo parte de uma mesma transaction
	//Isso foi feito por causa do problema de ciclo de vida
	/*Quando se efetua um commit, o objeto fica detached (sai do contexto do entity manager
	 * A operação de salvar deixa o objeto despached. Impossibilitando que o Entity Manager
	 * trabalhe com o objeto para excluir (outro commit)*/
	public void testExcluir() throws DAOException{
		Usuario usuario = new Usuario();
		usuario.setNome("TestUsuario");
		usuario.setLogin("testusuario");
		usuario.setSenha("123");
		Usuario usuarioSalvo = dao.salvar(usuario);
		
		int id = usuarioSalvo.getId();
		
		dao.excluir(usuarioSalvo);
		
		Usuario usuarioExcluido = dao.buscaPorId(id);

		Assert.assertEquals(usuarioExcluido, null);
	}
	
	@Transactional
	@Test
	public void testBuscarTodos(){
		/*List<Usuario> listaUsuario = new ArrayList<Usuario>(); 
		
		for(Usuario u: dao.buscarTodos()){
			listaUsuario.add(u);
		}
		
		for (int i=0; i<10; i++){
			Usuario usuario = new Usuario();
			usuario.setNome("TestUsuario");
			usuario.setLogin("testusuario");
			usuario.setSenha("123");
			listaUsuario.add(usuario);
			Usuario usuarioSalvo = dao.salvar(usuario);
		}
		
		List<Usuario> listaSalvo = new ArrayList<Usuario>(); 
		listaSalvo = dao.buscarTodos();
		
		Assert.assertEquals(listaUsuario, listaSalvo);*/
		Usuario usuario = new Usuario();
		usuario.setNome("TestUsuario");
		usuario.setLogin("testusuario");
		usuario.setSenha("123");
		
		dao.salvar(usuario);
		List<Usuario> buscarTodos = dao.buscarTodos();
		
		Assert.assertTrue(buscarTodos.size()>0);
		
	}
}
