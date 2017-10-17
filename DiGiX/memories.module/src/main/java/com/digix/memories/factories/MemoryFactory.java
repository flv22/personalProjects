package com.digix.memories.factories;

import com.digix.memories.constants.MemoryType;
import com.digix.memories.entities.Memory;
import com.digix.memories.entities.Note;
import com.digix.memories.entities.Photo;
import com.digix.memories.entities.Sound;
import com.digix.memories.entities.Video;

public class MemoryFactory implements IMemoryFactory{

	@SuppressWarnings("unchecked")
	public Memory getMemory(String type) {
		
		if(type.equalsIgnoreCase(MemoryType.PHOTO))
			return new Photo();
		if(type.equalsIgnoreCase(MemoryType.VIDEO))
			return new Video();
		if(type.equalsIgnoreCase(MemoryType.SOUND))
			return new Sound();
		if(type.equalsIgnoreCase(MemoryType.NOTE))
			return new Note();
		return null;
	}

}