package org.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.application.MainView;
import org.application.Observer;
import org.datastructures.Queue;
import org.datastructures.Graph;
import org.datastructures.Stack;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;

public class Parser {
	
	static Observer obs;
	static Stack stack;
	static Queue queue;
	static Graph graph;
	
	public Parser(){
		
	}
	
	public static boolean isNumber(String str)
	{	
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	public static void setObserver(Observer observer){
		obs=observer;
	}

	public static void setStack(Stack s){
		stack=s;
	}
	
	public static void setQueue(Queue a){
		queue=a;
	}
	
	public static void setGraph(Graph g){
		graph=g;
	}
	
	public static void parse(String str) {	
		stack.emptyStack();
		Observer.resetPosition();
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		cu.accept(new ASTVisitor() {
 
			Set names = new HashSet();
			HashMap<String,Integer> variables=new HashMap<String,Integer>();
			HashMap<String,Integer> forLoop=new HashMap<String,Integer>();
			HashMap<String,HashMap<Integer,Integer>> ifStatement=new HashMap<String,HashMap<Integer,Integer>>();
			
			public boolean visit(Assignment node) {
				
				String variableName=node.getLeftHandSide().toString();
				Integer value=Integer.parseInt(node.getRightHandSide().toString());
				variables.put(variableName, value);
				
				return true;
			}
			
			public boolean visit(ClassInstanceCreation node) {
				names.add(node.getType().toString());

				if(node.getType().toString().equals("Stack"))
					stack.newStack();
				else
				if(node.getType().toString().equals("Queue"))
					queue.newQueue();
				else
				if(node.getType().toString().equals("Graph")){}
				
				return true;
			}
			
			public boolean visit(ForStatement node){
				
				if (node.getBody().toString().contains(".push(")) {
					String[] val = node.initializers().iterator().next().toString().split("=");
					String[] varSplit=val[0].split(" ");
					String[] varName=varSplit[1].split("=");
					forLoop.put(varName[0], Integer.parseInt(val[1]));

					if (node.updaters().iterator().next().toString().contains("++"))
						forLoop.put("updater", 1);
	
					val = node.getExpression().toString().split(" ");
					if (node.getExpression().toString().contains("<"))
						forLoop.put("lower", Integer.parseInt(val[2]));
					else if (node.getExpression().toString().contains("<="))
						forLoop.put("lower-equal", Integer.parseInt(val[2]));
					else if (node.getExpression().toString().contains(">"))
						forLoop.put("greater", Integer.parseInt(val[2]));
					else if (node.getExpression().toString().contains(">="))
						forLoop.put("greater-equal", Integer.parseInt(val[2]));
					
					System.out.println(forLoop);
				}
				
				return true;
			}
			
			public boolean visit(IfStatement node){
				System.out.println(node);
				System.out.println(node.getThenStatement());
				System.out.println(node.getElseStatement());
				System.out.println(node.properties());
				System.out.println(node.getExpression());//c
				
				String[] parsedExpression=node.getExpression().toString().split(" ");
				HashMap<Integer,Integer> h=new HashMap<Integer,Integer>();
					h.put(Integer.parseInt(parsedExpression[0]), Integer.parseInt(parsedExpression[2]));
					
				switch(parsedExpression[1]){
				case "<":	
					ifStatement.put("lower", h); 
					break;
				case ">":
					ifStatement.put("greater", h);
					break;
				case "<=":
					ifStatement.put("lower-equal", h);
					break;
				case ">=":
					ifStatement.put("greater-equal", h);
					break;
				case "%":
					ifStatement.put("greater", h);
					break;
				}
				
				System.out.println(ifStatement);
				return true;
			}
			
			public boolean visit(MethodInvocation node) {
				//if(node.getParent().equals())
				if(names.contains("Stack")){
					if(node.getName().toString().equals("push")){
						if(!isNumber(node.arguments().get(0).toString())){
							if(forLoop.containsKey(node.arguments().get(0).toString())){
								if(forLoop.containsKey("lower"))
									if(forLoop.get("updater")==1)
										for(int x=forLoop.get(node.arguments().get(0).toString());x<forLoop.get("lower");x++){
											stack.push(x);
											obs.addJob("stack.push("+Integer.toString(x)+")");
										}
							}else
							if(variables.containsKey(node.arguments().get(0).toString()))
								stack.push(variables.get(node.arguments().get(0).toString()));
						}else{
							stack.push(Integer.parseInt(node.arguments().get(0).toString()));
							obs.addJob("stack.push("+node.arguments().get(0).toString()+")");
						}
					}else
					if(node.getName().toString().equals("pop")){
						stack.pop();
						obs.addJob("stack.pop()");
					}else
					if(node.getName().toString().equals("top")){
						stack.top();
					}
				}else
				if(names.contains("Queue")){
					if(node.getName().toString().equals("add"))
						queue.add(Integer.parseInt(node.arguments().get(0).toString()));
					if(node.getName().toString().equals("remove")){
						queue.remove();
					}
				}
				
				return true;
			}
		});
		
		obs.goAction();
	}
}
