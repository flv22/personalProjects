	package org.application;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;

public class Project {
	
	static IPackageFragment packageFragment;
	static ICompilationUnit cu;
	
	static String projectName;
	static String sourceFileName;
	static String packageName;
	static String code;
	
	public Project(){
	}
	
	public static void buildProject(String projName,String packageName,String className) throws CoreException{
		  
		  Project.projectName=projName;
		  Project.sourceFileName=className;
		  Project.packageName=packageName;
		  
		  IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		  IProject project = root.getProject(projName);
		  project.create(null);
		  project.open(null);
		  
		  //setare tip java
		  IProjectDescription description = project.getDescription();
		  description.setNatureIds(new String[] { JavaCore.NATURE_ID });
		  
		  //creare proiect
		  project.setDescription(description, null);
		  IJavaProject javaProject = JavaCore.create(project);
		  
		  //setare build path
		  IClasspathEntry[] buildPath = {
		  		JavaCore.newSourceEntry(project.getFullPath().append("src")),JavaRuntime.getDefaultJREContainerEntry() };
		  
		  javaProject.setRawClasspath(buildPath, project.getFullPath().append("bin"), null);
		  
		  //create folder by using resources package
		  IFolder folder = project.getFolder("src");
		  folder.create(true, true, null);
		  
		  //Add folder to Java element
		  IPackageFragmentRoot srcFolder = javaProject.getPackageFragmentRoot(folder);
		  
		  sourceFileName=className.concat(".java");
		  packageFragment = srcFolder.createPackageFragment(packageName, true, null);
		  
		  String eol=System.getProperty("line.separator");
		  code="package "+packageName+";"+eol+eol
				  	+"public class "+className+"{"+System.getProperty("line.separator")
				  	+"\t"+"public static void main(String[] argv){"+System.getProperty("line.separator")
				  	+"\t\t//Your code goes here"
				  	+eol+"\t}"+System.getProperty("line.separator")+"}";
				  	
		  cu = packageFragment.createCompilationUnit(sourceFileName, code, false, null);
		  
		  //////////***********//////////
		  String sourcecode="package "+packageName+";"+eol+eol
				  	+"public class Stack{"
				  	+"ArrayList<Integer> stack;"
		  			+"public Stack(){"
		  			+"		stack=new ArrayList<Integer>();"
		  			+"}"
		  			+"public void push(int n){}"+eol
		  			+"public void pop(){}"+eol
		  			+"public void top(){}"+eol
		  			+"}";
		  ////******************////
		  String sourcecodeGraph="package "+packageName+";"+eol+eol
				  	+"public class Graph{"
		  			+"public Graph(){"
		  			+"}"
		  			+"public void setSize(int noPoints){}"+eol
		  			+"public void addEdge(int a,int b){}"+eol
		  			+"public void BFS(int startNode){}"+eol
		  			+"}";
		  ////*********************////
		  String sourcecodeArray="package "+packageName+";"+eol+eol
				  	+"public class Queue{"
		  			+"public Queue(){"
		  			+"}"
		  			+"public void add(int element){}"+eol
		  			+"public void remove(){}"+eol
		  			+"public void get(int position){}"+eol
		  			+"}";
		  
		  ICompilationUnit cu2 = packageFragment.createCompilationUnit("Stack.java", sourcecode, false, null);
		  ICompilationUnit GraphCU=packageFragment.createCompilationUnit("Graph.java", sourcecodeGraph, false, null);
		  ICompilationUnit QueueCU=packageFragment.createCompilationUnit("Queue.java", sourcecodeArray, false, null);
	}
	
	public static String getSourceCode(){
		return code;
	}
	
	public static void updateSourceFile(String code){
		try {
			cu.delete(true, null);
			cu=packageFragment.createCompilationUnit(sourceFileName, code, false, null);
		} catch (JavaModelException | NullPointerException e) {
		}
	}
	
	public static ICompilationUnit getCu() {
		return cu;
	}

	public void setCu(ICompilationUnit cu) {
		Project.cu = cu;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public IPackageFragment getPackageFragment() {
		return packageFragment;
	}

	public void setPackageFragment(IPackageFragment packageFragment) {
		this.packageFragment = packageFragment;
	}
	
}
