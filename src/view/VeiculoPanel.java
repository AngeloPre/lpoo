/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.VeiculoController;
import javax.swing.JOptionPane;
import model.Veiculo;
import model.Automovel;
import model.Motocicleta;
import model.Van;
import model.enums.Marca;
import model.enums.Estado;
import model.enums.Categoria;
import table.VeiculoTableModel;

/**
 *
 * @author mrblue
 */
public class VeiculoPanel extends javax.swing.JPanel {

    private VeiculoController veiculoController;
    private VeiculoTableModel veiculoTableModel;
    
    public VeiculoPanel(VeiculoController veiculoController, VeiculoTableModel veiculoTableModel) {
        this.veiculoController = veiculoController;
        this.veiculoTableModel = veiculoTableModel;
        initComponents();
        
    }
    
    public VeiculoPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        MarcaVeiculo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        EstadoVeiculo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        CategoriaVeiculo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        modeloVeiculo = new javax.swing.JComboBox<>();
        SalvarVeiculoBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TipoVeiculo = new javax.swing.JComboBox<>();
        valorVeiculo = new javax.swing.JFormattedTextField();
        placaVeiculo = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setPreferredSize(new java.awt.Dimension(1104, 575));

        MarcaVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(Marca.values()));

        jLabel1.setText("Marca");

        jLabel2.setText("Estado");

        EstadoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(Estado.values()));

        jLabel3.setText("Categoria");

        CategoriaVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(Categoria.values()));

        jLabel4.setText("Valor");

        jLabel5.setText("Placa");

        jLabel6.setText("Modelo");

        modeloVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(model.enums.ModeloAutomovel.values()));

        SalvarVeiculoBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SalvarVeiculoBtn.setText("Salvar");
        SalvarVeiculoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarVeiculoBtnActionPerformed(evt);
            }
        });

        jLabel7.setText("Tipo");

        TipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Automóvel", "Motocicleta", "Van" }));
        TipoVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoVeiculoActionPerformed(evt);
            }
        });

        valorVeiculo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        valorVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorVeiculoActionPerformed(evt);
            }
        });

        try {
            placaVeiculo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        placaVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placaVeiculoActionPerformed(evt);
            }
        });

        jLabel8.setText("R$");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(0, 6, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(MarcaVeiculo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addGap(98, 98, 98)
                                            .addComponent(jLabel1)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(104, 104, 104)
                                            .addComponent(EstadoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(195, 195, 195)
                                            .addComponent(jLabel2))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(valorVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(placaVeiculo)
                                        .addComponent(modeloVeiculo, 0, 230, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(258, 258, 258)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(CategoriaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(157, 157, 157)))
                .addComponent(SalvarVeiculoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel4)
                .addGap(322, 322, 322)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(SalvarVeiculoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EstadoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MarcaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(CategoriaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modeloVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(valorVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(placaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1160, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SalvarVeiculoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarVeiculoBtnActionPerformed
        Marca marca = (Marca) MarcaVeiculo.getSelectedItem();
        Estado estado = (Estado) EstadoVeiculo.getSelectedItem();
        Categoria categoria = (Categoria) CategoriaVeiculo.getSelectedItem();
        double valor = ((Number) valorVeiculo.getValue()).doubleValue();
        String placa = (String) placaVeiculo.getValue();
        String tipoSelecionado = (String) TipoVeiculo.getSelectedItem();
        Object modelo = modeloVeiculo.getSelectedItem();

        Veiculo veiculo = null;
        switch (tipoSelecionado){
            case ("Automóvel"):
            veiculo = new Automovel(marca, estado, null, categoria, valor, placa, 2025, (model.enums.ModeloAutomovel) modelo);
            break;
            case ("Motocicleta"):
            veiculo = new Motocicleta(marca, estado, null, categoria, valor, placa, 2025, (model.enums.ModeloMotocicleta) modelo);
            break;
            case ("Van"):
            veiculo = new Van(marca, estado, null, categoria, valor, placa, 2025, (model.enums.ModeloVan) modelo);
            break;
            default:
                JOptionPane.showMessageDialog(null, "Ainda não implementado...");
        }

        if (veiculo != null){
            veiculoController.incluirVeiculo(veiculo);
            JOptionPane.showMessageDialog(this, "Veiculo Salvo!");
        }
    }//GEN-LAST:event_SalvarVeiculoBtnActionPerformed

    private void TipoVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoVeiculoActionPerformed
        String tipoSelecionado = (String) TipoVeiculo.getSelectedItem();

        if (tipoSelecionado.equals("Automóvel"))
        modeloVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(model.enums.ModeloAutomovel.values()));
        else if (tipoSelecionado.equals("Motocicleta"))
        modeloVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(model.enums.ModeloMotocicleta.values()));
        else
        modeloVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(model.enums.ModeloVan.values()));

    }//GEN-LAST:event_TipoVeiculoActionPerformed

    private void valorVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorVeiculoActionPerformed

    }//GEN-LAST:event_valorVeiculoActionPerformed

    private void placaVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placaVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placaVeiculoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Categoria> CategoriaVeiculo;
    private javax.swing.JComboBox<Estado> EstadoVeiculo;
    private javax.swing.JComboBox<Marca> MarcaVeiculo;
    private javax.swing.JButton SalvarVeiculoBtn;
    private javax.swing.JComboBox<String> TipoVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<Object> modeloVeiculo;
    private javax.swing.JFormattedTextField placaVeiculo;
    private javax.swing.JFormattedTextField valorVeiculo;
    // End of variables declaration//GEN-END:variables
}
