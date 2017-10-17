package com.digix.memories.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("photo")
public class Photo extends Memory{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4061411746644005501L;

	public Photo(){
		
	}
	
}