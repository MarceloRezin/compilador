package tela;

import analise.AnaliseLexica;
import analise.AnaliseSintatica;
import arquivo.Arquivo;
import exceptions.AnaliseLexicaException;
import token.Token;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

public class TelaEditor extends javax.swing.JFrame {

    private File arquivoSelecionado;
    private TokenTableModel modelo = new TokenTableModel(new Stack<>());
    Stack<Token> derivacoes = null;
    Stack<Token> parsing = null;

    public TelaEditor() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnRun = new javax.swing.JButton();
        btnPlay = new JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaTokens = new javax.swing.JTable(modelo);

        //Alinha os itens da tabela
        ((DefaultTableCellRenderer) tabelaTokens.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tabelaTokens.setDefaultRenderer(Object.class, new CellRender());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(238, 238, 238));
        setLocationByPlatform(true);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/imagens/new.png"))); // NOI18N
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/imagens/folder.png"))); // NOI18N
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/imagens/save.png"))); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/imagens/x-button.png"))); // NOI18N
        

        btnPlay.setIcon(new ImageIcon(TelaEditor.class.getResource("/tela/imagens/next.png"))); // NOI18N
        btnPlay.addActionListener( l -> {
            runAction(true);
        });

        btnRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/imagens/Run.png"))); // NOI18N
        btnRun.addActionListener( l -> {
            runAction(false);
        });

        jScrollPane.setMaximumSize(new java.awt.Dimension(32763217, 32321767));

        jScrollPane2.setMaximumSize(new java.awt.Dimension(32732167, 331232767));
        jScrollPane2.setViewportView(tabelaTokens);
        
        JLabel lblConsole = new JLabel("Console");
        
        scrollPane = new JScrollPane();
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setMaximumSize(new Dimension(32732167, 331232767));
        
      

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
        				.addComponent(lblConsole, Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnAbrir, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        						.addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
        						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        					.addComponent(btnNovo, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        					.addComponent(btnAbrir, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnRun, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
        				.addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblConsole)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        
        tabelaParse = new JTable(modelo);
        scrollPane_1.setViewportView(tabelaParse);
        
        txtCont = new JTextArea();
        txtCont.setForeground(Color.RED);
        txtCont.setText("1");
        txtCont.setEditable(false);
        jScrollPane.setRowHeaderView(txtCont);
        txtEditor = new JTextArea();
        txtEditor.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent arg0) {
        		if(arg0.getKeyCode() == arg0.VK_ENTER) {
					contaLinhaEnter(txtEditor,txtCont);
				}
				else if(arg0.getKeyCode() == arg0.VK_BACK_SPACE) {
					contaLinhaBack(txtEditor,txtCont);	
				}
			else if(arg0.getKeyCode() == arg0.VK_DELETE) {
					contaLinhaBack(txtEditor,txtCont);	
				}
        	}
        });
        jScrollPane.setViewportView(txtEditor);
        
        txtConsole = new JTextArea();
        txtConsole.setEditable(false);
        scrollPane.setViewportView(txtConsole);
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        arquivoSelecionado = null;
        if (!txtEditor.getText().equals("")) {
            editado(txtEditor);
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        if (!txtEditor.getText().equals("")) {
            editado(txtEditor);
            lerArq(txtEditor);
        } else {
            lerArq(txtEditor);
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        salvarArq(txtEditor.getText());
    }//GEN-LAST:event_btnSaveActionPerformed

    private void runAction(boolean stepByStep){
        try {

            if(stepByStep){
                if(derivacoes == null || parsing == null){
                    char[] texto = txtEditor.getText().toCharArray();
                    derivacoes = AnaliseLexica.analisar(texto);
                    parsing = AnaliseSintatica.getPilhaParsingInicial();
                }

                if(derivacoes.isEmpty()){
                    derivacoes = null;
                    parsing = null;
                }else{
                    tabelaTokens.setModel(new TokenTableModel(derivacoes));
                    tabelaParse.setModel(new TokenTableModel(parsing));
//                    System.err.println(derivacoes);
//                    System.out.println(parsing);
                    AnaliseSintatica.analisar(derivacoes, parsing);
                }


            }else{
                char[] texto = txtEditor.getText().toCharArray();
                if(texto.length > 0){
                    Stack<Token> derivacoes = AnaliseLexica.analisar(texto);
                    modelo = new TokenTableModel(derivacoes);

                    Stack<Token> parsing = AnaliseSintatica.getPilhaParsingInicial();

                    System.out.println(parsing);

                    while(!derivacoes.isEmpty()){
                        AnaliseSintatica.analisar(derivacoes, parsing);
                        System.out.println(parsing);
                    }

                    tabelaTokens.setModel(modelo);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(TelaEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
                new TelaEditor().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnRun;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaTokens;
    private JScrollPane scrollPane;
    private JTextArea txtConsole;
    private JTextArea txtCont;
    private JTextArea txtEditor;
    private JButton btnPlay;
    private JTable tabelaParse;
    // End of variables declaration//GEN-END:variables

    public void salvarArq(String txtEditor) {

        if(arquivoSelecionado != null){
            try {
                Arquivo.gravar(arquivoSelecionado.getAbsolutePath(), txtEditor.getBytes());
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int res = fc.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                try {
                    arquivoSelecionado = fc.getSelectedFile();
                    Arquivo.gravar(arquivoSelecionado.getAbsolutePath() + "/"+JOptionPane.showInputDialog("Nome do arquivo:") + ".lms",txtEditor.getBytes());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void lerArq(JTextArea txtEditor) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensão .lms", "lms");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int retorno = chooser.showOpenDialog(chooser);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            try {
                arquivoSelecionado = chooser.getSelectedFile();
                txtEditor.setText(Arquivo.convert(Arquivo.ler(arquivoSelecionado.getAbsolutePath()))); //seta o txtEditor com texto salvo do arquivo. Criar o metodo get que retorna a String na classe Arquivo
                contaLinhaBack(txtEditor,txtCont);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void editado(JTextArea txtEditor) {
        int op = JOptionPane.showConfirmDialog(null, "Deseja salvar o arquivo?");
        if (op == 0) {
            salvarArq(txtEditor.getText());
        } else if (op == 1) {
            txtEditor.setText("");
            contaLinhaBack(txtEditor,txtCont);
        }
    }
    public void contaLinhaEnter(JTextArea area,JTextArea cont) {
		String linhas ="";
		int i;
		for(i =0;i<area.getLineCount();i++) {
			linhas += i+1+"\n";
		}
		cont.setText(linhas);;
	}
	public void contaLinhaBack(JTextArea area,JTextArea cont) {
		String linhas ="";
		int i;
		for(i =1;i<area.getLineCount();i++) {
			linhas += i+"\n";
		}
		linhas += i;
		cont.setText(linhas);;
	}
}
