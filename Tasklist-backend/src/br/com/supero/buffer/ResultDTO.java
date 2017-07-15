package br.com.supero.buffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Buffer com a sa�da generica de todos os servi�os.
 * 
 * @author H�linton P. Steffens
 *
 * @param <C>
 */
public class ResultDTO<C> {

	private List<C> result;
	private List<String> messages;
	private String status;
	
	public List<C> getResult() {
		if (result == null) {
			result = new ArrayList<>();
		}
		
		return result;
	}
	public void setResult(List<C> result) {
		this.result = result;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
