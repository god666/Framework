package br.com.framework;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.framework.dao.DAOException;
import br.com.framework.dao.UsuarioDAO;
import br.com.framework.entidade.Usuario;
import org.junit.Assert;

public class TestUsuarioDAO {
	
	EntityManager em;
	ClassPathXmlApplicationContext cxt;
	
	UsuarioDAO dao;
	
	@Before
	public void init(){
		cxt = new ClassPathXmlApplicationContext(
				"file:/Users/god/workspace/framework/src/main/resources/META-INF/springbeans.xml");

		EntityManagerFactory emf = (EntityManagerFactory) cxt.getBean("entityManagerFactory");

		em = emf.createEntityManager();
		
		dao = new UsuarioDAO(em);
	}
	
	@After
	public void finish(){
		cxt.close();
	}
	
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
