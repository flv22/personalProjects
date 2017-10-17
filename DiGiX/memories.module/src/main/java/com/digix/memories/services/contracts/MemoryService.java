package com.digix.memories.services.contracts;

import java.util.List;

import com.digix.memories.entities.Memory;

public interface MemoryService {
	
	//returns a list of memories
	public List<Memory> findMemoriesByTagList(long id, List<String> tags);
	
	//returns a list of memories by tag
	public List<Memory> findMemoriesByTag(long id, String tag);
	
	//saves a memory
	public boolean saveMemory(Memory mem);
	
	//delete a memory
	public boolean deleteMemory(Memory mem);
	
	//update a memory
	public Memory updateMemory(Memory mem);
	
}