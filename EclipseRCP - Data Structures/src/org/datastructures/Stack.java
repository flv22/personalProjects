package org.datastructures;

import java.util.ArrayList;

import org.application.Observer;
import org.contracts.StackContract;
import org.eclipse.draw2d.Panel;

public class Stack implements StackContract{
	ArrayList<Observer> observer=new ArrayList<Observer>();
	ArrayList<Integer> stack;
	
	public Stack(){
		stack=new ArrayList<Integer>();
	}
	
	public void newStack(){
		this.notifyObserversNew();
	}
	
	public void attachObserver(Observer obs){
		observer.add(obs);
	}
	
	@Override
	public void push(int element) {
		stack.add(element);
		this.notifyObserversPush();
	}
	
	public void notifyObserversPush(){
		for(Observer item: observer)
			item.updatePush();
	}
	
	public void notifyObserversNew(){
		for(Observer item: observer)
			item.updateNew();
	}
	
	public void showStack(){
		System.out.println("Stack:");
		for(Integer item: stack)
			System.out.println(item);
	}
	
	public void emptyStack(){
		stack.removeAll(stack);
		for(Observer item: observer)
			item.emptyGraphicalElementsStack();
	}
	
	public int getTop(){
		return this.stack.get(this.stack.size()-1);
	}

	@Override
	public void pop() {
		if(stack.size()>0){
			stack.remove(stack.size()-1);
			notifyObserversPop();
		}	
	}
	
	public void notifyObserversPop(){
		for(Observer item: observer)
			item.updatePop();
	}
	
	@Override
	public int top() {
		if(stack.size()>0){
			notifyObserversTop();
			return this.stack.get(this.stack.size()-1);
		}
		return -1;
	}
	
	public void notifyObserversTop(){
		for(Observer item: observer)
			item.updateTop();
	}
}
