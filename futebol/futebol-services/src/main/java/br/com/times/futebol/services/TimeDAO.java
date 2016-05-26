/**
 * 
 */
package br.com.times.futebol.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.times.futebol.web.model.Time;



/**
 * @author Hugo A. Martinez
 * @since 25/05/2016
 *
 */
@Component
public class TimeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static String SELECT_QUERY = "SELECT t FROM Time t";

	@Transactional
	public void inserir(final Time time) {
		try {
			this.entityManager.persist(time);
		} catch (Exception e) {
			logger.error("Erro ao inserir time: " + time.toString(), e);
			this.entityManager.getTransaction().rollback();
		}		
	}

	@SuppressWarnings("unchecked")
	public List<Time> buscarTodos() {
		return entityManager.createQuery(SELECT_QUERY).getResultList();
	}
	
	@Transactional
	public List<Time> atualizar(final Time time) {
		try {
			this.entityManager.merge(time);
		} catch (Exception e) {
			logger.error("Erro ao atualizar time: " + time.toString(), e);
			this.entityManager.getTransaction().rollback();
		}
		
		return this.buscarTodos();
	}
	
	@Transactional
	public void remover(Time time) {
		try {
			this.entityManager.remove(time);
		} catch (Exception e) {
			logger.error("Erro ao deletar time: " + time.toString(), e);
			this.entityManager.getTransaction().rollback();
		}
	}

}
