/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ClienteController;
import controller.LocacaoController;
import controller.VeiculoController;
import javax.swing.JPanel;
import table.ClienteTableModel;
import table.VeiculoTableModel;

/**
 *
 * @author mrblue
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());

    private ClienteController clienteController;
    private ClienteTableModel clienteTableModel;
    
    private LocacaoController locacaoController;
    private VeiculoController veiculoController;
    private VeiculoTableModel veiculoTableModel;
    

    public TelaPrincipal(ClienteController clienteController, ClienteTableModel clienteTableModel,
            LocacaoController locacaoController, VeiculoTableModel veiculoTableModel, VeiculoController veiculoController) {
        this.clienteController = clienteController;
        this.clienteTableModel = clienteTableModel;
        this.locacaoController = locacaoController;
        this.veiculoTableModel = veiculoTableModel;
        this.veiculoController = veiculoController;
        initComponents();
        setLocationRelativeTo(null);//esse cara aqui faz com que a janela principal inicie centralizada na tela
     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        clientePanel1 = new view.ClientePanel(this.clienteController, this.clienteTableModel);
        veiculoPanel1 = new view.VeiculoPanel(this.veiculoController, this.veiculoTableModel);
        locacaoPanel1 = new view.LocacaoPanel(this.locacaoController, this.clienteTableModel, this.veiculoTableModel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addTab("Clientes", clientePanel1);
        jTabbedPane1.addTab("Incluir Veiculos", veiculoPanel1);
        jTabbedPane1.addTab("Locação de Veículos", locacaoPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.ClientePanel clientePanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private view.LocacaoPanel locacaoPanel1;
    private view.VeiculoPanel veiculoPanel1;
    // End of variables declaration//GEN-END:variables
}
