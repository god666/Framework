package br.com.framework.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.dao.DAOException;
import br.com.framework.dao.InterfaceUsuarioDAO;
import br.com.framework.dao.UsuarioDAO;
import br.com.framework.entidade.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	@Qualifier("usuarioDAO")
	InterfaceUsuarioDAO dao;
	
	public Usuario salvar(Usuario usuario) throws ServiceException{
		Usuario usuarioVerificado = dao.buscarPorLogin(usuario.getLogin());
		
		if(usuarioVerificado==null){
			return dao.salvar(usuario);
		} else{
			throw new ServiceException("Já existe este usuário!");
		}
	}

	public void excluir(Usuario usuario) throws ServiceException{
		try {
			dao.excluir(usuario);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


	public Usuario buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public List<Usuario> buscarTodos() {
		return dao.buscarTodos();

	}
	
	public Usuario buscarPorLogin(String login){
		return buscarPorLogin(login);
	}
	
}
