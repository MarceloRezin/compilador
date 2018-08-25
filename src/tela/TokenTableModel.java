package tela;

import token.Token;

import javax.swing.table.AbstractTableModel;
import java.util.Stack;

public class TokenTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_PALAVRA = 1;
    private String[] colunas = new String[]{"Código", "Palavra"};

    private Stack<Token> linhas;

    public TokenTableModel(Stack<Token> linhas) {
        this.linhas = linhas;
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return Integer.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Token token = linhas.get(row);

        if(column == COL_CODIGO){
            return token.getCodigo().getCodigo();
        }
        return token.getPalavra();
    }

    public void addToken(Token token) {
        linhas.add(token);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }
}
