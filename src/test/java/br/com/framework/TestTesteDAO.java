package br.com.framework;

import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.dao.BaseDAO;
import br.com.framework.dao.TesteDAO;
import br.com.framework.entidade.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/springbeans.xml")//Onde está o arquivo de contexto
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TestTesteDAO {

	@Autowired
	//@Qualifier("testeDAO")
	TesteDAO dao;
	
	@Test
	@Transactional
	public void testSalvar(){
		Usuario u = new Usuario();
		u.setLogin("login");
		u.setNome("nome");
		u.setSenha("senha");
		
		Usuario retorno = dao.salvar(u);

		Assert.assertEquals(u.getLogin(), retorno.getLogin());
		Assert.assertEquals(u.getNome(), retorno.getNome());
		Assert.assertEquals(u.getSenha(), retorno.getSenha());
	}
	
	@Transactional
	@Test
	public void testBuscarPorId(){
		Usuario usuario = new Usuario();
		usuario.setNome("TestUsuariodffff");
		usuario.setLogin("testusuario");
		usuario.setSenha("123");
		Usuario usuarioSalvo = dao.salvar(usuario);
		
		int id = usuarioSalvo.getId();
		Usuario usuarioBuscado = dao.buscaPorId(id);
		
		System.out.println("=============================="+usuarioBuscado);
		
		//Vai usar o equals
		Assert.assertEquals(usuarioSalvo, usuarioBuscado);
	}
	
}
