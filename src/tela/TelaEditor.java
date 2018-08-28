package tela;

import analise.AnaliseLexica;
import arquivo.Arquivo;
import exceptions.AnaliseLexicaException;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;

public class TelaEditor extends javax.swing.JFrame {

    private File arquivoSelecionado;
    private TokenTableModel modelo = new TokenTableModel(new Stack<>());

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
        jScrollPane = new javax.swing.JScrollPane();
        txtEditor = new JTextPane();
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

        btnRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/imagens/Run.png"))); // NOI18N
        btnRun.addActionListener( l -> {
            runAction();
        });

        jScrollPane.setMaximumSize(new java.awt.Dimension(32763217, 32321767));

        txtEditor.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jScrollPane.setViewportView(txtEditor);

        jScrollPane2.setMaximumSize(new java.awt.Dimension(32732167, 331232767));
        jScrollPane2.setViewportView(tabelaTokens);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                        .addComponent(btnAbrir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                                        .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

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

    private void runAction(){
        try {
            modelo = new TokenTableModel(AnaliseLexica.analisar(txtEditor.getText().toCharArray()));
            tabelaTokens.setModel(modelo);
        } catch (AnaliseLexicaException e) {
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
    private JTextPane txtEditor;
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

    private void lerArq(JTextPane txtEditor) {
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

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void editado(JTextPane txtEditor) {
        int op = JOptionPane.showConfirmDialog(null, "Deseja salvar o arquivo?");
        if (op == 0) {
            salvarArq(txtEditor.getText());
        } else if (op == 1) {
            txtEditor.setText("");
        }
    }
}
