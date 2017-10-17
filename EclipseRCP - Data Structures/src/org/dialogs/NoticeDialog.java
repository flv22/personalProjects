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

public class NoticeDialog extends TitleAreaDialog {

	public ArrayList<String> notice;
	Text console;
	String title;
	String message;
	
	public NoticeDialog(Shell parentShell,String title,String message) {
		super(parentShell);
		notice = new ArrayList<String>();
		this.title=title;
		this.message=message;
	}

	@Override
	public void create() {
		super.create();
		setTitle(title);
		setMessage(message, IMessageProvider.INFORMATION);
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
		console.setEnabled(false);
		GridData gridData = new GridData();

		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		console.setLayoutData(gridData);

		return area;
	}

	public void showProblemsInConsole() {
		for (String item : notice)
			console.append(item);
	}

	public void clearConsole() {
		console.setText("");
	}

	public void addNotice(String noticeItem) {
		console.append(noticeItem);
	}
}
