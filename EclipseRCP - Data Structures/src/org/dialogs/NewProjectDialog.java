package org.dialogs;

import org.application.MainView;
import org.application.Project;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewProjectDialog extends TitleAreaDialog {

	private Text txtProjectName;
	private Text txtPackageName;
	private Text txtClassName;

	private String projName;
	private String packageName;
	private String className;

	public NewProjectDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Name your project");
		setMessage("Your project name", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(2, false);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(layout);

		createFirstName(container);

		return area;
	}

	private void createFirstName(Composite container) {

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;
		
		Label lbtProjName = new Label(container, SWT.NONE);
		lbtProjName.setText("Project name: ");
		txtProjectName = new Text(container, SWT.BORDER);
		txtProjectName.setLayoutData(dataFirstName);
		
		Label lbtPackageName = new Label(container, SWT.NONE);
		lbtPackageName.setText("Package name: ");
		txtPackageName = new Text(container, SWT.BORDER);
		txtPackageName.setLayoutData(dataFirstName);

		Label lbtClassName = new Label(container, SWT.NONE);
		lbtClassName.setText("Class name: ");
		txtClassName = new Text(container, SWT.BORDER);
		txtClassName.setLayoutData(dataFirstName);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
		projName = txtProjectName.getText();
		packageName = txtPackageName.getText();
		className = txtClassName.getText();
	}

	@Override
	protected void okPressed() {
		saveInput();
		try {
			Project.buildProject(projName, packageName, className);
			MainView.text.setEnabled(true);
			MainView.text.setText(Project.getSourceCode());
		} catch (CoreException e) {
			NoticeDialog noticeDlg=new NoticeDialog(new Shell(Display.getDefault()),"Notice","Notice");
			noticeDlg.create();
			noticeDlg.addNotice(e.getMessage());
			noticeDlg.open();
		}
		this.close();
	}

	public String getProjectName() {
		return projName;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getClassName() {
		return className;
	}
}