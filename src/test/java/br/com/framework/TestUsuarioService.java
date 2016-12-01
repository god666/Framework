package br.com.framework;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.dao.InterfaceUsuarioDAO;
import br.com.framework.dao.UsuarioDAO;
import br.com.framework.entidade.Usuario;
import br.com.framework.service.ServiceException;
import br.com.framework.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration(defaultRollback=true, transactionManager="transactionManager")
public class TestUsuarioService {

	@Autowired
	UsuarioService service;
	@Autowired
	@Qualifier("usuarioDAO")
	InterfaceUsuarioDAO dao;
	
	@Transactional
	@Test (expected=ServiceException.class)
	public void testNaoDeveSalvar() throws ServiceException{
		Usuario usuario = new Usuario();
		usuario.setNome("TesteService3");
		usuario.setLogin("testeService3111123");
		usuario.setSenha("1243");

		dao.salvar(usuario);
		
		service.salvar(usuario);
		/*try {
			usuarioVazio = service.salvar(usuarioExistente);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(true);
			e.printStackTrace();
		}*/
	
	}
	
	@Transactional
	@Test
	public void testDeveSalvar() throws ServiceException{
		Usuario usuario = new Usuario();
		usuario.setNome("TesteService3");
		usuario.setLogin("testeService31111231111");
		usuario.setSenha("1243");
		
		service.salvar(usuario);
		/*try {
			usuarioVazio = service.salvar(usuarioExistente);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(true);
			e.printStackTrace();
		}*/
	
	}
	
}
