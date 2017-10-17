package com.digix.memories.persistence.impl;

import java.util.List;

import com.digix.memories.entities.Memory;
import com.digix.memories.persistence.contracts.IMemoryDAO;

public class MemoryDAO implements IMemoryDAO {

	@Override
	public List<Memory> getAllMemories(long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memory> getMemories(long uid, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memory> getMemories(long uid, List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMemory(Memory m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMemory(Memory m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMemory(Memory m) {
		// TODO Auto-generated method stub
		return false;
	}

}
