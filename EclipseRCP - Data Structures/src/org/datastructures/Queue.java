package org.datastructures;

import java.util.ArrayList;

import org.application.Observer;
import org.contracts.QueueContract;

public class Queue implements QueueContract{
	ArrayList<Integer> array;
	ArrayList<Observer> observer=new ArrayList<Observer>();
	
	public Queue(){
		array=new ArrayList<Integer>();
	}
	
	public void newQueue(){

	}
	
	public void attachObserver(Observer obs){
		observer.add(obs);
	}
	
	@Override
	public void add(int element) {
		array.add(element);
	}

	@Override
	public void remove() {
		if(array.size()>0)
			array.remove(0);
		
	}

	@Override
	public int get(int position) {
		return array.get(position);
	}
	@Override
	public int getSize() {
		return array.size();
	}
}
