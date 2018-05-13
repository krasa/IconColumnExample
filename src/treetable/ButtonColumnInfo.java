package treetable;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumnInfo extends ColumnInfo<MyTreeNode, Boolean> {
	public static final Icon DISABLED = AllIcons.Actions.GC;
	public static final Icon ENABLED = IconLoader.getIcon("/enabledIcon.png");

	private MyButtonRenderer myButtonRenderer = new MyButtonRenderer();
	private MyButtonEditor myButtonEditor = new MyButtonEditor(myButtonRenderer);

	public ButtonColumnInfo(String name) {
		super(name);
	}

	@Nullable
	@Override
	public Boolean valueOf(MyTreeNode grepExpressionItem) {
		return grepExpressionItem.isValue();
	}


	@Override
	public boolean isCellEditable(MyTreeNode grepExpressionItem) {
		return true;
	}

	@Nullable
	@Override
	public TableCellEditor getEditor(MyTreeNode o) {
		return myButtonEditor;
	}

	@Override
	public void setValue(MyTreeNode myTreeNode, Boolean value) {
		myTreeNode.setValue(!myTreeNode.isValue());
	}

	@Nullable
	@Override
	public TableCellRenderer getRenderer(MyTreeNode aVoid) {
		return myButtonRenderer;
	}

	private static class MyButtonRenderer extends JButton implements TableCellRenderer {
		public MyButtonRenderer() {
			super.setBorder(null);
			super.setOpaque(false);
			super.setBorderPainted(false);
		}


		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
													   int row, int column) {
			Boolean grepExpressionItem = (Boolean) value;
			if (grepExpressionItem) {
				setIcon(ENABLED);
			} else {
				setIcon(DISABLED);
			}
			return this;
		}
	}

	private class MyButtonEditor extends DefaultCellEditor {
		private final MyButtonRenderer myButtonRenderer;

		public MyButtonEditor(MyButtonRenderer myButtonRenderer) {
			super(new JCheckBox());
			this.myButtonRenderer = myButtonRenderer;
			this.myButtonRenderer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MyButtonEditor.this.stopCellEditing();
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			if ((Boolean) value) {
				this.myButtonRenderer.setIcon(ENABLED);
			} else {
				this.myButtonRenderer.setIcon(DISABLED);
			}
			return this.myButtonRenderer;
		}

	}
}
