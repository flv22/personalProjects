package org.datastructures;

import org.application.Observer;
import org.contracts.GraphContract;

public class Graph implements GraphContract{
	
	Observer observer;
	int[][] adiac;
	int size;
	
	public Graph(){
		
	}
	
	@Override
	public void addEdge(int a, int b) {
		if (a < size && b < size) {
			adiac[a][b] = 1;
			this.notifyAddEdge();
		}
	}
	
	public void attachObserver(Observer obs){
		this.observer=obs;
	}
	
	public void notifyAddEdge(){
		this.observer.updateNewEdge();
	}
	
	public int[][] getAdiac(){
		return adiac;
	}
	
	@Override
	public void setSize(int noPoints) {
		this.size=noPoints;
		adiac=new int[noPoints][noPoints];
		notifyNewGraph();
	}
	
	public void notifyNewGraph(){
		this.observer.updateNewGraph();
	}
	
	public int getSize(){
		return this.size;
	}

	@Override
	public void BFS(int startNode) {
		notifyBFS(startNode);
	}
	
	public void notifyBFS(int startNode){
	}
}
