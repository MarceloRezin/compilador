package tela;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRender extends DefaultTableCellRenderer {
    public CellRender() {
        super();
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setHorizontalAlignment(CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}