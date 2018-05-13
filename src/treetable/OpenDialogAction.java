package treetable;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/* ctrl alt shift Q*/
public class OpenDialogAction extends DumbAwareAction {

	@Override
	public void actionPerformed(AnActionEvent e) {

		Dialog f = new Dialog();
		DialogWrapper dialogWrapper = new DialogWrapper(e.getProject()) {
			{
				init();
				setTitle("Foo");
			}

			@Nullable
			@Override
			protected JComponent createCenterPanel() {
				return f.root;
			}

			@Override
			protected void doOKAction() {
				super.doOKAction();
			}
		};

		boolean b = dialogWrapper.showAndGet();
	}
}
