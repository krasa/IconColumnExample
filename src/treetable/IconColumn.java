package treetable;

import com.intellij.util.ui.AbstractTableCellEditor;
import com.intellij.util.ui.table.IconTableCellRenderer;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class IconColumn extends com.intellij.util.ui.ColumnInfo<MyTreeNode, Object> {

	public IconColumn(String title) {
		super(title);
	}

	@Nullable
	@Override
	public MyTreeNode valueOf(MyTreeNode grepExpressionItem) {
		return grepExpressionItem;
	}

	@Override
	public boolean isCellEditable(MyTreeNode grepExpressionItem) {
		return true;
	}

	@Override
	public void setValue(MyTreeNode item, Object value) {
		item.setValue(!item.isValue());
	}

	@Nullable
	@Override
	public TableCellEditor getEditor(MyTreeNode o) {
		return new AbstractTableCellEditor() {

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				return IconTableCellRenderer.ICONABLE.getTableCellRendererComponent(table, value, isSelected, true, row, column);
			}

			@Override
			public Object getCellEditorValue() {
				return true;
			}
		};
	}

	@Nullable
	@Override
	public TableCellRenderer getRenderer(MyTreeNode aVoid) {
		return IconTableCellRenderer.ICONABLE;
	}
}
