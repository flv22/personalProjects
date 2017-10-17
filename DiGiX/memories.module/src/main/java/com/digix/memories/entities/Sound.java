package com.digix.memories.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("sound")
public class Sound extends Memory{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5574669824374046070L;

	public Sound(){
		
	}
	
}
