package com.digix.memories.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("note")
public class Note extends Memory{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2613669190819258230L;
	@Column(name = "content")
	private String content = "";
	
	public Note(){
		
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}