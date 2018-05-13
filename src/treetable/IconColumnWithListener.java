package treetable;

import com.intellij.openapi.util.Iconable;
import com.intellij.ui.CheckboxTreeTable;
import com.intellij.ui.ClickListener;
import com.intellij.util.ui.table.IconTableCellRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;

public class IconColumnWithListener extends com.intellij.util.ui.ColumnInfo<MyTreeNode, Object> {

	private IconTableCellRenderer<MyTreeNode> myRenderer;

	public IconColumnWithListener(String title) {
		super(title);
		myRenderer = new IconTableCellRenderer<MyTreeNode>() {
			MyListener myListener;

			@Override
			public Component getTableCellRendererComponent(JTable table,
														   Object value,
														   boolean selected,
														   boolean focus,
														   int row,
														   int column) {
				if (myListener == null) {
					myListener = new MyListener(table, IconColumnWithListener.this.getName());
					myListener.installOn(table);
				}
				return super.getTableCellRendererComponent(table, value, selected, focus, row, column);
			}

			@Nullable
			@Override
			protected Icon getIcon(@NotNull MyTreeNode value, JTable table, int row) {
				return value.getIcon(Iconable.ICON_FLAG_VISIBILITY);
			}
		};
	}

	@Nullable
	@Override
	public MyTreeNode valueOf(MyTreeNode grepExpressionItem) {
		return grepExpressionItem;
	}

	@Override
	public boolean isCellEditable(MyTreeNode grepExpressionItem) {
		return false;
	}

	@Nullable
	@Override
	public TableCellRenderer getRenderer(MyTreeNode aVoid) {
		return myRenderer;
	}


	private class MyListener extends ClickListener {

		private final CheckboxTreeTable myTable;
		private final String myColumnName;

		public MyListener(JTable table, String columnName) {
			myTable = (CheckboxTreeTable) table;
			myColumnName = columnName;
		}

		@Override
		public boolean onClick(@NotNull MouseEvent e, int clickCount) {
			if (e.getButton() == 1 && !e.isPopupTrigger()) {
				if (myTable.getRowCount() > 0) {
					final int row = myTable.rowAtPoint(e.getPoint());
					final int col = myTable.columnAtPoint(e.getPoint());

					String columnName = myTable.getColumnName(col);
					if (columnName.equals(myColumnName)) {
						MyTreeNode node = (MyTreeNode) myTable.getModel().getValueAt(row, col);
						if (node != null) {

							node.setValue(!node.isValue());

							myTable.repaint(myTable.getCellRect(row, col, false));
							return true;
						}
					}
				}
			}
			return false;
		}
	}
}
