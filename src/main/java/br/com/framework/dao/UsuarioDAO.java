package br.com.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.entidade.Usuario;;

@Repository//Mesma coisa que adicionar um bean no arquivo springbeans.xml
public class UsuarioDAO implements InterfaceUsuarioDAO {

	@PersistenceContext  //Framework entende que é para injetar o EntityManager para o DAO
	//Essa classe vai substituir o Connection
	private EntityManager em;
	// private Connection connection = ConexaoFactory.getConnection();

	
	public UsuarioDAO(EntityManager em){
		this.em = em;
	}
	
	public UsuarioDAO(){
		
	}
	//Não precisa
	public void cadastrar(Usuario usuario) {

	}

	public void alterar(Usuario usuario) {

	}
	
	@Transactional  //O spring vai controlar as transactions. Por isso, o getTransaction é
	//substituído pelo @Transactional
	//Faz o cadastro e alteração
	public Usuario salvar(Usuario usuario) {
		//em.getTransaction().begin();
		Usuario retorno = em.merge(usuario);
		//em.getTransaction().commit();
		return retorno;
	}

	@Transactional
	public void excluir(Usuario usuario) throws DAOException {
		//em.getTransaction().begin();
	/*	if(buscaPorId(usuario.getId())!=null){ //EU ADICIONEI ISSO
			Usuario usuarioManaged = em.merge(usuario);  
		}*/
		
		try{
			/*Usuario u = buscaPorId(usuario.getId());
			em.remove(u);*/
			Usuario usuarioManaged = buscaPorId(usuario.getId());
			em.remove(usuarioManaged);
		}catch (Exception e) {
			throw new DAOException("Não excluiu", e);
		}
		
		//em.getTransaction().commit();
	}



	/**
	 * Busca de um registro no Banco de Dados por ID
	 * 
	 * @param id
	 *            é um inteiro que representa o ID do usuário a ser buscado
	 * @return Um objeto Usuario quando encontra, e Null caso contrário
	 */
	public Usuario buscaPorId(Integer id) {
		return em.find(Usuario.class, id);

	}

	public List<Usuario> buscarTodos() {
		Query q = em.createQuery("select u from Usuario u");
		return q.getResultList();

	}
	
	public Usuario buscarPorLogin(String login){
		try{
			Query q = em.createQuery("select u from Usuario u where u.login=:loginParam");
			q.setParameter("loginParam", login);
			return (Usuario) q.getSingleResult();
			//return em.find(Usuario.class, login);
		} catch (NoResultException e) {
			return null;
			//throw DAOException("Usuário Não encontrado!", e);
		}
		
	}

}
