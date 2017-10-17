package com.digix.memories.factories;

public interface IMemoryFactory {
	
	public <T> T getMemory(String type);
	
}
