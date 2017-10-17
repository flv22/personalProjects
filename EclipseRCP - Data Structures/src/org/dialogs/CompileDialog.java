package org.dialogs;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CompileDialog extends TitleAreaDialog {

	public ArrayList<String> problems;
	Text console;

	public CompileDialog(Shell parentShell) {
		super(parentShell);
		problems = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		super.create();
		setTitle("Compilation log");
		setMessage("See errors here", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginBottom = 0;
		container.setLayout(layout);

		console = new Text(area, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL  | SWT.H_SCROLL);
		GridData gridData = new GridData();

		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		console.setLayoutData(gridData);

		return area;
	}

	public void showProblemsInConsole() {
		for (String problem : problems)
			console.append(problem);
	}

	public void clearConsole() {
		console.setText("");
	}

	public void addProblem(String problem) {
		problems.add(problem);
		problems.add("\r\n");
	}
}
