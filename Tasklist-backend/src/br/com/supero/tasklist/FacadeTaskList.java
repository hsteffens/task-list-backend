package br.com.supero.tasklist;

import br.com.supero.buffer.ResultDTO;
import br.com.supero.buffer.TaskDTO;

/**
 * Classe contendo os facilitadores para a manipulação de Task.
 * 
 * @author Hélinton P. Steffens
 *
 */
public final class FacadeTaskList {

	private FacadeTaskList(){
		
	}
	
	public static ResultDTO<String> insertTask(TaskDTO task){
		ResultDTO<String> resultDTO = new ResultDTO<>();
		
		if (BOTaskList.create(task)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}
		
		return resultDTO;
	}
	
	public static ResultDTO<TaskDTO> getTasks(){
		ResultDTO<TaskDTO> resultDTO = new ResultDTO<>();
		resultDTO.setResult(BOTaskList.getTasks());
		
		resultDTO.setStatus("SUCCESS");
		
		return resultDTO;
	}
	
	public static ResultDTO<String> editTask(Integer id, TaskDTO task){
		ResultDTO<String> resultDTO = new ResultDTO<>();
		
		if (BOTaskList.edit(id, task)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}
		
		return resultDTO;
	}
	
	public static ResultDTO<String> removeTask(Integer id){
		ResultDTO<String> resultDTO = new ResultDTO<>();
		
		if (BOTaskList.remove(id)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}
		
		return resultDTO;
	}
}
