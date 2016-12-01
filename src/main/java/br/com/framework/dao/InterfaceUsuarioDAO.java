package br.com.framework.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import br.com.framework.entidade.Usuario;

public interface InterfaceUsuarioDAO {

	//Não precisa
	public void cadastrar(Usuario usuario);
	public void alterar(Usuario usuario);
	public Usuario salvar(Usuario usuario);

	public void excluir(Usuario usuario) throws DAOException;
	public Usuario buscaPorId(Integer id);

	public List<Usuario> buscarTodos();
	public Usuario buscarPorLogin(String login);
	
}
