package br.com.supero.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.supero.buffer.ResultDTO;
import br.com.supero.buffer.TaskDTO;
import br.com.supero.tasklist.FacadeTaskList;

/**
 * Classe contendo os serviços disponibilizados para manipulação de Tasks.
 * 
 * @author Hélinton P. Steffens
 *
 */
@Path("/task-list")
public class ServiceTask {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<TaskDTO> getTasks(){
		return FacadeTaskList.getTasks();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> insertTask(TaskDTO task){
		return FacadeTaskList.insertTask(task);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> alterarTask(@PathParam("id") Integer id, TaskDTO task){
		return FacadeTaskList.editTask(id, task);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> deleteTask(@PathParam("id") Integer id){
		return FacadeTaskList.removeTask(id);
	}
	
	
}
