package com.digix.memories.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("video")
public class Video extends Memory{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4163077413222981423L;

	public Video(){
		
	}
	
}
