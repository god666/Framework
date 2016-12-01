package br.com.framework.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.util.Base64;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class BaseDAO <Objeto>{

	@PersistenceContext  
	private EntityManager em;

	/*Este atributo é responsável em receber o Objeto.class (o que isso significa?)
	para os métodos excluir e find que utilizando como parâmetros o Objeto.class*/
	
	private Class<Objeto> parametro;
	public BaseDAO(){
		parametro = ((Class) ((ParameterizedType) getClass().
				getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	public static void main (String args[]){
		TesteDAO dao = new TesteDAO();
		System.out.println(dao.getClass());
		System.out.println(dao.getClass().getGenericSuperclass());
		ParameterizedType type = (ParameterizedType) dao.getClass().getGenericSuperclass();
		System.out.println(type);

		System.out.println((Class) type.getActualTypeArguments()[0]);
	}
	
	public BaseDAO(EntityManager em){
		this.em = em;
	}

	
	public Objeto buscaPorId(Integer id) {
		return em.find(parametro, id);

	}
	
	@Transactional 
	public Objeto salvar(Objeto obj) {
		Objeto retorno = em.merge(obj);
		return retorno;
	}

	@Transactional
	public void excluir(Integer id) throws DAOException {
		try{
			Objeto objManaged= em.getReference(parametro, id);
			em.remove(objManaged);
		}catch (Exception e) {
			throw new DAOException("Não excluiu", e);
		}
	}




	public List<Objeto> buscarTodos() {
		Query q = em.createQuery("select obj from Objeto obj");
		return q.getResultList();

	}
	
	public Objeto buscarPorLogin(String login){
		try{
			Query q = em.createQuery("select obj from Objeto obj where u.login=:loginParam");
			q.setParameter("loginParam", login);
			return (Objeto) q.getSingleResult();
			//return em.find(Objeto.class, login);
		} catch (NoResultException e) {
			return null;
			//throw DAOException("Usuário Não encontrado!", e);
		}
		
	}
}
