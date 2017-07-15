package br.com.supero.controler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.supero.persistencia.Task;

/**
 * Controller responsável por manipular um objeto de Task
 * 
 * @author Hélinton P. Steffens
 *
 */
public class TaskJpaController implements Serializable {

	private EntityManagerFactory emf = null;
	
    public TaskJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Task task){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(task);
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
                throw new RuntimeException("Task " + task.getTitle() + " already exists.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Task> findTask() {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("Task.findAll");
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	} finally {
    		em.close();
    	}
    }
    
    public boolean edit(Task task){
    	 EntityManager em = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             em.merge(task);
             em.getTransaction().commit();
             return true;
         } catch (Exception ex) {
             String msg = ex.getLocalizedMessage();
             if (msg == null || msg.length() == 0) {
                throw new RuntimeException("The task with id " + task.getId() + " no longer exists.");
             }
             throw ex;
         } finally {
             if (em != null) {
                 em.close();
             }
         }
    }
    
    public boolean remove(Integer id){
    	EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Task task = em.find(Task.class, id);
            task.setDateRemove(new Date());
            task.setDateEdition(new Date());
            
            em.merge(task);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
               throw new RuntimeException("The task with id " + id + " no longer exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
