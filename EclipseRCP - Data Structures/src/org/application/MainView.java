package org.application;

import java.util.ArrayList;

import org.datastructures.Queue;
import org.datastructures.Graph;
import org.datastructures.Stack;
import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.parser.Parser;

public class MainView extends ViewPart implements MouseListener,PaintListener{
	
	Canvas canvas;
	ArrayList<StackElement> stack=new ArrayList<StackElement>();
	static Stack stk=new Stack();
	static Queue queue=new Queue();
	Graph graph=new Graph();
	Observer observer;
	public static Text text;
	Parser p=new Parser();
	
	public static final String ID="HelloRCP.mainView";
	
	public MainView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		observer=Observer.getInstance(parent,stk);
			observer.addStackSubject(stk);
			observer.addQueueSubject(queue);
		canvas=observer.getContents();
		
		parent.setLayout(new FillLayout());
		canvas.addMouseListener(this);
		canvas.addPaintListener(this);
		
		Parser.setObserver(observer);
		Parser.setStack(stk);
		Parser.setQueue(queue);
		Parser.setGraph(graph);
		
		text=new Text(parent, SWT.BORDER|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
		text.setEnabled(false);
		
		char[] activation = new char[] {'.'};

		ContentProposalAdapter prediction = new ContentProposalAdapter(text,new TextContentAdapter(),
		    new SimpleContentProposalProvider(new String[] {"push(valoare);", "pop();", "top();" }),null, activation);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintControl(PaintEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*public void parseText(String s) {
		String[] line = s.split(";");
		
		for(String item: line){
			parseLine(item);
		}
	}
	
	public void parseLine(String line){
		if(line.contains("push")){
			String parsed[]=line.split("\\(");
			stk.push(Character.getNumericValue(parsed[1].charAt(0)));
		}else
		if(line.contains("pop")){
			stk.pop();
		}else
		if(line.contains("top")){
			stk.top();
		}else
		if(line.contains("new") && line.contains("stack")){
			stk.newStack();
		}else
		if(line.contains("new") && line.contains("array")){
				array.newArray();
		}else
		if(line.contains("add")){
			String parsed[]=line.split("\\(");
			array.add(Character.getNumericValue(parsed[1].charAt(0)));
		}
	}*/
	
}
