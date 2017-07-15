package br.com.supero.tasklist;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import br.com.supero.buffer.TaskDTO;
import br.com.supero.controler.TaskJpaController;
import br.com.supero.persistencia.Task;
import br.com.supero.provider.EntityManager;

/**
 * Regras de negócio envolvendo uma Task.
 * 
 * @author Hélinton P. Steffens
 *
 */
public final class BOTaskList {

	private BOTaskList(){
		
	}
	
	public static boolean create(TaskDTO taskDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		try{
			factory = EntityManager.getFactory();

			Task task = new Task();
			task.setTitle(taskDTO.getTitle());
			task.setStatus(taskDTO.getStatus());
			task.setContent(taskDTO.getContent());
			task.setDateCreation(new Date());
			
			if (taskDTO.getStatus()) {
				task.setDateConclusion(new Date());
			}
			
			TaskJpaController controller = new TaskJpaController(factory);
			return controller.create(task);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
	}
	
	public static List<TaskDTO> getTasks(){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		
		try{
			factory = EntityManager.getFactory();
			TaskJpaController controller = new TaskJpaController(factory);
			List<Task> tasks = controller.findTask();
			
			if (tasks != null && !tasks.isEmpty()) {
				List<TaskDTO> tasksDTO = new ArrayList<>();
				for (Task task : tasks) {
					TaskDTO taskDTO = new TaskDTO();
					taskDTO.setId(task.getId());
					taskDTO.setTitle(task.getTitle());
					taskDTO.setStatus(task.getStatus());
					taskDTO.setContent(task.getContent());
					
					if (task.getDateConclusion() != null) {
						taskDTO.setDateConclusion(converterDate(task.getDateConclusion()));
					}
					
					if (task.getDateCreation() != null) {
						taskDTO.setDateCreation(converterDate(task.getDateCreation()));
					}
					
					if (task.getDateEdition() != null) {
						taskDTO.setDateEdition(converterDate(task.getDateEdition()));
					}
					
					if (task.getDateRemove() != null) {
						taskDTO.setDateRemove(converterDate(task.getDateRemove()));
					}
					
					tasksDTO.add(taskDTO);
				}
				
				return tasksDTO;
			}
			
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
		
		return null;
	}
	
	public static boolean edit(Integer id, TaskDTO taskDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		
		try{
			factory = EntityManager.getFactory();
			TaskJpaController controller = new TaskJpaController(factory);
			
			Task task = new Task();
			task.setId(id);
			task.setTitle(taskDTO.getTitle());
			task.setStatus(taskDTO.getStatus());
			task.setContent(taskDTO.getContent());
			
			if (taskDTO.getDateCreation() != null) {
				task.setDateCreation(Date.from(taskDTO.getDateCreation().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			}
			
			task.setDateEdition(new Date());
			
			if (taskDTO.getDateRemove() != null) {
				task.setDateRemove(Date.from(taskDTO.getDateRemove().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			}
			
			if (taskDTO.getStatus()) {
				task.setDateConclusion(new Date());
			}
			
			return controller.edit(task);
			
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}
	
	public static boolean remove(Integer id){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		
		try{
			factory = EntityManager.getFactory();
			TaskJpaController controller = new TaskJpaController(factory);
			
			return controller.remove(id);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}
	
	private static LocalDate converterDate(Date date){
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		return instant.atZone(defaultZoneId).toLocalDate();
	}
}
