package org.application;

import java.util.ArrayList;

import org.datastructures.Queue;
import org.datastructures.Graph;
import org.datastructures.Stack;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class Observer{
	private static Observer instance=null;
	
	protected Stack Subject;
	protected Queue QueueSubject;
	protected Graph graph;
	static Canvas canvas;
	
	static int X=0;
	static int Y=0;
	
	static int YMax=400;
	static int XMax=0;
	int PopMaxX=200,PopMaxY=0,noPop;
	
	ArrayList<GraphicalElement> graphics=new ArrayList<GraphicalElement>();
	
	static int index=0;
	MainAnimator ma=new MainAnimator();
	
	static ArrayList<String> jobs = new ArrayList<String>();
	
	static ArrayList<Thread> threads=new ArrayList<Thread>();
	
	protected Observer(Composite parent,Stack subject){
		canvas=new Canvas(parent,SWT.DOUBLE_BUFFERED);
		
		canvas.addPaintListener(new PaintListener(){

			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				e.gc.setBackground(ColorConstants.buttonLightest);
				e.gc.setForeground(ColorConstants.buttonLightest);
				e.gc.fillRectangle(0,0,Display.getDefault().getClientArea().width,Display.getDefault().getClientArea().height);
			}	
		});
	}
	
	public static Observer getInstance(Composite parent,Stack subject){
		if(instance==null)
			instance = new Observer(parent,subject);
		return instance;
	}
	
	public void addStackSubject(Stack subject){
		this.Subject=subject;
		this.Subject.attachObserver(this);
	}
	
	public void addQueueSubject(Queue subject){
		this.QueueSubject=subject;
		this.QueueSubject.attachObserver(this);
	}
	
	public void updatePush(){
		GraphicalElement ge=new GraphicalElement(Integer.toString(this.Subject.getTop()));
		ge.setX(X);
		ge.setY(0);
		graphics.add(ge);
		Y=Y-45;
		
		canvas.addPaintListener(new PaintListener(){
			@Override
			public void paintControl(PaintEvent e) {
				for(GraphicalElement item: graphics){
					GC gc=e.gc;
					gc.setAntialias(SWT.ON);
					gc.setForeground(ColorConstants.blue);
					gc.setBackground(ColorConstants.blue);
					gc.fillOval(item.getX(),item.getY(),100,40);
					gc.setForeground(ColorConstants.white);
					gc.drawString(item.getValue(), item.getX()+45, item.getY()+10);
				}
			}
		});
		
		jobs.add("stack.push("+Subject.getTop()+")");
		
		//at.go();
	}
	
	public void updatePop(){
		//at.end();
		//pop.go();
	}
	
	public void updateTop(){
		
		//top.go();
	}
	
	public void updateNew(){
		canvas.addPaintListener(new PaintListener(){

			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				GC gc=e.gc;
				gc.setAntialias(SWT.ON);
				gc.setBackground(ColorConstants.black);
				gc.setForeground(ColorConstants.black);
				gc.setLineWidth(3);
				gc.drawLine(100, 50, 100, 440);
				gc.drawLine(200,50,200,440);
				gc.drawLine(100, 440, 200, 440);
			}
		});
		canvas.redraw();
		MainView.text.setEnabled(true);
	}
	
	public void emptyGraphicalElementsStack(){
		graphics.removeAll(graphics);
	}
	
	public Canvas getContents(){
		return canvas;
	}
	
	public static void resetPosition(){
		
		Y=0;
		X=0;
		YMax=400;
		XMax=0;
		index=0;
		//at=new AnimatorThreadPush();
		//jobs.removeAll(jobs);
		canvas.addPaintListener(new PaintListener(){

			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				e.gc.setBackground(ColorConstants.buttonLightest);
				e.gc.setForeground(ColorConstants.buttonLightest);
				e.gc.fillRectangle(0,0,Display.getDefault().getClientArea().width,Display.getDefault().getClientArea().height);
			}
		});
	}
	
	/*public void animatePush(){
		
			Display.getDefault().asyncExec(new Runnable(){
			@Override
			public void run() {
					if(graphics.get(index).getX()<100)
						graphics.get(index).setX(graphics.get(index).getX()+1);
				
					if(graphics.get(index).getX()==100)
						if(graphics.get(index).getY()<YMax)
							graphics.get(index).setY(graphics.get(index).getY()+1);
				
					if(graphics.get(index).getX()==100 && graphics.get(index).getY()==YMax)
						if(index<graphics.size()-1){
							index++;
							YMax-=45;
						}
					
					if(graphics.get(graphics.size()-1).getX()==100 && graphics.get(graphics.size()-1).getY()==YMax){
						at.end();
					}
					
					canvas.redraw();
			}
			});
	}
	
	public void animatePop(){

		Display.getDefault().asyncExec(new Runnable(){
			@Override
			public void run() {
					
					if(graphics.get(graphics.size()-1).getY()>PopMaxY)
						graphics.get(graphics.size()-1).setY(graphics.get(graphics.size()-1).getY()-1);
					
					if(graphics.get(graphics.size()-1).getY()==PopMaxY)
						if(graphics.get(graphics.size()-1).getX()<PopMaxX)
							graphics.get(graphics.size()-1).setX(graphics.get(graphics.size()-1).getX()+1);

					if (graphics.get(graphics.size()-1).getY() == 0 && graphics.get(graphics.size()-1).getX() == PopMaxX) {
						graphics.remove(graphics.size()-1);
						index--;
						pop.end();
					}
					canvas.redraw();
			}
		});
	}

	class AnimatorThreadPush implements Runnable {
		private static final int TIMER_INTERVAL = 4;
		private boolean running = false;		
		public void go() {
			running = true;
			Thread push=new Thread(this);
			push.start();
		}
		
		public void end() {
			running = false;	
		}

		public boolean getRunning() {
			return running;
		}

		@Override
		public void run() {
			synchronized(graphics){
					try {
						while (running == true) {
								animatePush();
							Thread.sleep(TIMER_INTERVAL);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	class AnimatorThreadPop implements Runnable{
		
		private static final int TIMER_INTERVAL = 4;
		boolean running=false;
		Thread pop;
		
		public void end(){
			running=false;
		}
		
		public void go() {
			running=true;
			pop = new Thread(this);
			pop.start();
		}
		
		public Thread getThread(){
			return pop;
		}
		
		public boolean getRunning(){
			return running;
		}
		
		@Override
		public void run() {
			synchronized (graphics) {
				try {
					while (running == true) {
						// if(at.getThread().isAlive()==false)
						animatePop();
						Thread.sleep(TIMER_INTERVAL);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class AnimatorThreadTop implements Runnable{
		
		private static final int TIMER_INTERVAL = 4;
		boolean running=false;
		Thread top;
		
		public void end(){
			running=false;
		}
		
		public void go() {
			running=true;
			top = new Thread(this);
			top.start();
		}
		
		public Thread getThread(){
			return top;
		}
		
		public boolean getRunning(){
			return running;
		}
		
		@Override
		public void run() {
			synchronized (graphics) {
				try {
					while (running == true) {
						// if(at.getThread().isAlive()==false)
						animateTop();
						Thread.sleep(TIMER_INTERVAL);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void animateTop(){
		
		
		Display.getDefault().asyncExec(new Runnable(){
			
			
		@Override
		public void run() {
			int direction=1;
			
			if(direction==1){
				if(graphics.get(graphics.size()-1).getY()>0)
					graphics.get(graphics.size()-1).setY(graphics.get(graphics.size()-1).getY()-direction);
				else
				if(graphics.get(graphics.size()-1).getY()==0)
					direction=-1;
			}
			
			if(direction==-1){
				if(graphics.get(graphics.size()-1).getY()<YMax)
					graphics.get(graphics.size()-1).setY(graphics.get(graphics.size()-1).getY()+1);
				
				if(graphics.get(graphics.size()-1).getY()==YMax)
					direction=0;
			}
			
			canvas.redraw();
		}
		});
}
	
	///////////***********Queue*********///////
	
	////////*************GRAPH***********/////////
	
	public void addGraphSubject(Graph g){
		this.graph=g;
		this.graph.attachObserver(this);
	}
	
	public void updateNewGraph() {
		graphics.removeAll(graphics);
		
		double u=360/graph.getSize();
		double r=145,cx=190,cy=200;
		double Pi180=3.14/180;
		
		for(int i=0;i<graph.getSize();i++){
			GraphicalElement ge=new GraphicalElement("1");
				ge.setX((int)(Math.cos(i*u*Pi180)*r+cx));
				ge.setY((int)(Math.sin(i*u*Pi180)*r+cy));
				ge.setValue(Integer.toString(i));
			graphics.add(ge);
		}		
		showGraphPoints();
	}
	
	public void updateNewEdge(){
		
		canvas.addPaintListener(new PaintListener(){
			
			@Override
			public void paintControl(PaintEvent e) {
				GC gc=e.gc;
				gc.setForeground(ColorConstants.black);
				gc.setBackground(ColorConstants.black);
				gc.setLineWidth(2);
				
				for(int i=0;i<graph.getSize();i++){
					for(int j=0;j<graph.getSize();j++)
						if(graph.getAdiac()[i][j]==1)
							gc.drawLine(graphics.get(i).getX()+10, graphics.get(i).getY()+10, graphics.get(j).getX()+10, graphics.get(j).getY()+10);
				}
			}
			
		});
		
		showGraphPoints();
	}
	
	public void showGraphPoints(){
		canvas.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				GC gc=e.gc;
				gc.setAntialias(SWT.ON);
				
				
				for(GraphicalElement item: graphics){
					gc.setForeground(ColorConstants.blue);
					gc.setBackground(ColorConstants.blue);
					gc.fillOval(item.getX(), item.getY(), 20, 20);
					
					gc.setForeground(ColorConstants.white);
					gc.drawString(item.getValue().toString(), item.getX()+6, item.getY()+1);
				}
			}
		});
		canvas.redraw();
	}
	
	public void addJob(String s){
		jobs.add(s);
		showJobs();
	}
	public void showJobs(){
		System.out.println(jobs);
	}
	
	public void animateMainThread(){
		
		Display.getDefault().asyncExec(new Runnable(){
		@Override
		public void run() {
				if(jobs.get(0).contains("stack.push")){
					System.out.println("Sunt aici2");
						if (graphics.get(index).getX() < 100)
							graphics.get(index).setX(graphics.get(index).getX() + 1);

						if (graphics.get(index).getX() == 100)
							if (graphics.get(index).getY() < YMax)
								graphics.get(index).setY(graphics.get(index).getY() + 1);

						if (graphics.get(index).getX() == 100 && graphics.get(index).getY() == YMax)
							if (index < graphics.size() - 1) {
								index++;
								YMax -= 45;
								jobs.remove(0);
								System.out.println("Scos");
							}

						canvas.redraw();
				}else
				if(jobs.get(0).contains("stack.pop")){
					System.out.println("Sunt aici");
					if(graphics.get(graphics.size()-1).getY()>PopMaxY)
						graphics.get(graphics.size()-1).setY(graphics.get(graphics.size()-1).getY()-1);
					
					if(graphics.get(graphics.size()-1).getY()==PopMaxY)
						if(graphics.get(graphics.size()-1).getX()<PopMaxX)
							graphics.get(graphics.size()-1).setX(graphics.get(graphics.size()-1).getX()+1);

					if (graphics.get(graphics.size()-1).getY() == 0 && graphics.get(graphics.size()-1).getX() == PopMaxX) {
						graphics.remove(graphics.size()-1);
						index--;
						jobs.remove(0);
					}
					canvas.redraw();
				}

			if(jobs.size()==0)
				ma.end();
		}
		});
}
	
	public void goAction(){
		ma.go();
	}
	
	class MainAnimator implements Runnable{
		private static final int TIMER_INTERVAL = 4;
		private boolean running = false;
		
		public void go(){
			running=true;
			Thread t=new Thread(this);
			t.start();
		}
		
		public void end() {
			running = false;	
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (running == true) {
						animateMainThread();
					Thread.sleep(TIMER_INTERVAL);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	///////////////////////////////////////////////////
}
