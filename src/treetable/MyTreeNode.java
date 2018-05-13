package treetable;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.Iconable;
import com.intellij.ui.CheckedTreeNode;

import javax.swing.*;

public class MyTreeNode extends CheckedTreeNode implements Iconable {
	public static final Icon DISABLED = AllIcons.Actions.GC;
	public static final Icon ENABLED = IconLoader.getIcon("/enabledIcon.png");

	private boolean value;

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public Icon getIcon(int i) {
		if (isValue()) {
			return (ENABLED);
		} else {
			return (DISABLED);
		}

	}
}
