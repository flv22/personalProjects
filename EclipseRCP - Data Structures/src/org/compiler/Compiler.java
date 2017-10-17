package org.compiler;

import org.dialogs.CompileDialog;
import org.dialogs.NoticeDialog;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IProblemRequestor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Compiler {
	
	static CompileDialog dlg;
	
	public Compiler(){
		
	}
	
	public static boolean compile(ICompilationUnit cu) throws JavaModelException{
		
		dlg=new CompileDialog(new Shell(Display.getDefault()));

		IProblemRequestor problemRequestor = new IProblemRequestor() {
		    public void acceptProblem(IProblem problem) {
		    	if(problem.isError())
		    		dlg.addProblem(problem.getMessage());
		    }
		    
		    public void beginReporting() {}
		    public void endReporting() {}
		    public boolean isActive() { return true; } // daca e activ,gaseste probleme de compilare
		  };
		    
		  try{
			ICompilationUnit workingCopy = cu.getWorkingCopy(new WorkingCopyOwner() {}, problemRequestor, null);
			if (dlg.problems.size() > 0) {
				dlg.create();
				dlg.showProblemsInConsole();
				dlg.open();
			}
		  }catch(NullPointerException e){
			  NoticeDialog notice=new NoticeDialog(new Shell(Display.getDefault()),"Notice","Run animation notice");
			  notice.create();
			  notice.addNotice("You must write code in order to run animation!");
			  notice.open();
		  }
		  
		  if(dlg.problems.size()>0)
			  return false;
		  return true;
	} 
}
