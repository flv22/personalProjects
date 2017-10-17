package org.application;

import org.application.Project;
import org.compiler.Compiler;
import org.datastructures.Queue;
import org.datastructures.Stack;
import org.dialogs.CompileDialog;
import org.dialogs.NewProjectDialog;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.parser.Parser;

public class CommandHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (event.getCommand().getId().equals("hellorcp.NewProjectCommandHandler")) {
			Display dsp = Display.getDefault();
			Shell shell = new Shell(dsp);
			NewProjectDialog newProjDlg = new NewProjectDialog(shell);
			newProjDlg.open();
		}else{			
			try {
				Project.updateSourceFile(MainView.text.getText());
				ICompilationUnit cu=Project.getCu();
				boolean result=Compiler.compile(cu);
				
				if(result==true){
					Parser.setQueue(MainView.queue);
					Parser.setStack(MainView.stk);
					Parser.parse(MainView.text.getText());
				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
			}
		}
		return null;
	}
}
