package com.digix.memories.persistence.contracts;

import java.util.List;

import com.digix.memories.entities.Memory;

public interface IMemoryDAO {
	
	public List<Memory> getAllMemories(long uid);
	
	public List<Memory> getMemories(long uid, String tag);
	
	public List<Memory> getMemories(long uid, List<String> tags);
	
	public boolean addMemory(Memory m);
	
	public boolean deleteMemory(Memory m);
	
	public boolean updateMemory(Memory m);
	
}