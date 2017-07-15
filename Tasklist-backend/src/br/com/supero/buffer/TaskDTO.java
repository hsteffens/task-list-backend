package br.com.supero.buffer;

import java.time.LocalDate;

/**
 * Buffer para representar uma Task.
 * 
 * @author Hélinton P. Steffens
 *
 */
public class TaskDTO {

	private Integer id;
	private String title;
	private Boolean status;
	private String content;
	private LocalDate dateCreation;
	private LocalDate dateEdition;
	private LocalDate dateRemove;
	private LocalDate dateConclusion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	public LocalDate getDateEdition() {
		return dateEdition;
	}
	public void setDateEdition(LocalDate dateEdition) {
		this.dateEdition = dateEdition;
	}
	public LocalDate getDateRemove() {
		return dateRemove;
	}
	public void setDateRemove(LocalDate dateRemove) {
		this.dateRemove = dateRemove;
	}
	public LocalDate getDateConclusion() {
		return dateConclusion;
	}
	public void setDateConclusion(LocalDate dateConclusion) {
		this.dateConclusion = dateConclusion;
	}
}
