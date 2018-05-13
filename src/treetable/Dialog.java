package treetable;

import com.intellij.ui.CheckboxTree;
import com.intellij.ui.CheckboxTreeTable;
import com.intellij.ui.CheckedTreeNode;
import com.intellij.ui.treeStructure.treetable.TreeColumnInfo;
import com.intellij.util.ui.ColumnInfo;

import javax.swing.*;


/* ctrl alt shift Q*/
public class Dialog {
	public JPanel root;
	private CheckboxTreeTable table;

	public Dialog() {
	}


	private void createUIComponents() {
		ColumnInfo[] columns =
			{
				new TreeColumnInfo(""),
				new ButtonColumnInfo("foo"),
				new IconColumnWithListener("foo"),
				new IconColumn("bar"),
			};


		MyTreeNode root = new MyTreeNode();
		root.add(new MyTreeNode());
		root.add(new MyTreeNode());
		root.add(new MyTreeNode());
		root.add(new MyTreeNode());

		table = new CheckboxTreeTable(root, new CheckboxTree.CheckboxTreeCellRenderer() {
			@Override
			public void customizeRenderer(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				if (!(value instanceof CheckedTreeNode)) return;

			}
		}, columns);
	}

}
