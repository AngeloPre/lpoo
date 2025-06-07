/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import javax.swing.ButtonGroup;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.LocacaoController;
import table.ClienteTableModel;
import table.VeiculoTableModel;

/**
 *
 * @author tads
 */
public class LocacaoPanel extends javax.swing.JPanel {

    
    private LocacaoController locacaoController;
    private ClienteTableModel clienteTableModel;
    
    public LocacaoPanel() {
        initComponents();
    }

    public LocacaoPanel(LocacaoController locacaoController, ClienteTableModel clienteTableModel, VeiculoTableModel veiculoTableModel) {
        this.locacaoController = locacaoController;
        this.clienteTableModel = clienteTableModel;
        this.locacaoController.listarClientes();
        initComponents();
        configurarRadioButtons();
        popularCombos();
        limparCampos();
        this.tblCliente.setModel(clienteTableModel);
        this.tblVeiculo.setModel(veiculoTableModel);
        locacaoController.listarVeiculos();
        btnBuscarCliente.addActionListener(this::botaoBuscarActionPerformed);

        tblCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tblCliente.getSelectedRow() != -1) {
                    preencherCamposClienteSelecionado();
                }
            }
        });
    }

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        switch (getCriterioSelecionado()) {
            case "CPF":
                buscarPorCpf();
                break;
            case "NOME":
                buscarPorNome();
                break;
            case "SOBRENOME":
                buscarPorSobrenome();
                break;
            default:
                break;
        }
    }

    

    private void buscarPorCpf() {
        String cpf = campoBuscaCliente.getText().trim();
        if (cpf.isBlank()) return;

        locacaoController.buscarPorCpf(cpf);      // delega ao controller
        limparCampos();
    }

    private void buscarPorNome() {
        String nome = campoBuscaCliente.getText().trim();
        if (nome.isBlank()) return;

        locacaoController.buscarPorNome(nome);
        limparCampos();
    }

    private void buscarPorSobrenome() {
        String sobrenome = campoBuscaCliente.getText().trim();
        if (sobrenome.isBlank()) return;

        locacaoController.buscarPorSobrenome(sobrenome);
        limparCampos();
    }

    private void preencherCamposClienteSelecionado() {
        int selectedRow = tblCliente.getSelectedRow();
        if (selectedRow >= 0) {

            String id        = clienteTableModel.getValueAt(selectedRow, 0).toString(); // id
            String cpf       = clienteTableModel.getValueAt(selectedRow, 4).toString(); // cpf
            String nome      = clienteTableModel.getValueAt(selectedRow, 1).toString(); // nome
            String sobrenome = clienteTableModel.getValueAt(selectedRow, 2).toString(); // sobrenome

            campoID.setText(id);
            campoCPF.setText(cpf);
            campoNomeSobrenome.setText(nome + " " + sobrenome);
        }
    }

    private void popularCombos() {
    /* tipo (automóvel / moto / van)  */
    comboTipo.setModel(
        new javax.swing.DefaultComboBoxModel<>(
            locacaoController.obterTiposVeiculo()
        )
    );

    /* marca (VW, GM, Fiat, …)  */
    comboMarca.setModel(
        new javax.swing.DefaultComboBoxModel<>(
            locacaoController.obterMarcas()
        )
    );

    /* categoria (POPULAR, INTERMEDIARIO, LUXO)  */
    comboCategoria.setModel(
        new javax.swing.DefaultComboBoxModel<>(
            locacaoController.obterCategorias()
        )
    );
}
    
    private void configurarRadioButtons() {
        clienteFiltro = new ButtonGroup();
        clienteFiltro.add(radioBuscaCPF);
        clienteFiltro.add(radioBuscaNome);
        clienteFiltro.add(radioBuscaSobrenome);
        veiculoFiltro.add(radioFiltroTipo);
        veiculoFiltro.add(radioFiltroMarca);
        veiculoFiltro.add(radioFiltroCategoria);

        radioBuscaCPF.setActionCommand("CPF");
        radioBuscaNome.setActionCommand("NOME");
        radioBuscaSobrenome.setActionCommand("SOBRENOME");
        radioBuscaCPF.setSelected(true);      // opção padrão
        radioFiltroTipo.setSelected(true);      // opção padrão
    }

    private String getCriterioSelecionado() {
        return clienteFiltro.getSelection().getActionCommand();
    }

    private void limparCampos() {
        campoBuscaCliente.setText("");
        campoCalculoPagamentoLocacao.setText("");
        campoDiasDeAluguel.setText("");
        campoCPF.setText("");
        campoID.setText("");
        campoNomeSobrenome.setText("");
        valorDiariaVeiculoSelecionado.setText("");
        placaVeiculoSelecionado.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clienteFiltro = new javax.swing.ButtonGroup();
        veiculoFiltro = new javax.swing.ButtonGroup();
        nomeDoMenuLocacao = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        radioBuscaCPF = new javax.swing.JRadioButton();
        radioBuscaNome = new javax.swing.JRadioButton();
        radioBuscaSobrenome = new javax.swing.JRadioButton();
        campoBuscaCliente = new javax.swing.JTextField();
        scrollVeiculo = new javax.swing.JScrollPane();
        tblVeiculo = new javax.swing.JTable();
        scrollCliente = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        campoID = new javax.swing.JTextField();
        campoCPF = new javax.swing.JTextField();
        campoNomeSobrenome = new javax.swing.JTextField();
        radioFiltroTipo = new javax.swing.JRadioButton();
        comboTipo = new javax.swing.JComboBox<>();
        radioFiltroMarca = new javax.swing.JRadioButton();
        comboMarca = new javax.swing.JComboBox<>();
        radioFiltroCategoria = new javax.swing.JRadioButton();
        comboCategoria = new javax.swing.JComboBox<>();
        placaVeiculoSelecionado = new javax.swing.JTextField();
        valorDiariaVeiculoSelecionado = new javax.swing.JTextField();
        campoDiasDeAluguel = new javax.swing.JTextField();
        campoCalculoPagamentoLocacao = new javax.swing.JTextField();
        botaoLocar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(453, 403));

        nomeDoMenuLocacao.setText("Locação de Veículos");

        btnBuscarCliente.setText("Buscar Cliente");

        radioBuscaCPF.setText("Buscar por CPF");

        radioBuscaNome.setText("Buscar por Nome");

        radioBuscaSobrenome.setText("Buscar por Sobrenome");

        campoBuscaCliente.setText("jTextField1");

        tblVeiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollVeiculo.setViewportView(tblVeiculo);

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollCliente.setViewportView(tblCliente);

        campoID.setText("jTextField1");
        campoID.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        campoID.setEnabled(false);

        campoCPF.setText("jTextField2");
        campoCPF.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        campoCPF.setEnabled(false);

        campoNomeSobrenome.setText("jTextField3");
        campoNomeSobrenome.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        campoNomeSobrenome.setEnabled(false);

        radioFiltroTipo.setText("Filtrar Veiculos por Tipo");
        radioFiltroTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFiltroTipoActionPerformed(evt);
            }
        });

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        radioFiltroMarca.setText("Filtrar Veiculos por Marca");

        comboMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        radioFiltroCategoria.setText("Filtrar Veiculos por Categoria");

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        placaVeiculoSelecionado.setText("jTextField1");
        placaVeiculoSelecionado.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        placaVeiculoSelecionado.setEnabled(false);

        valorDiariaVeiculoSelecionado.setText("jTextField2");
        valorDiariaVeiculoSelecionado.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        valorDiariaVeiculoSelecionado.setEnabled(false);

        campoDiasDeAluguel.setText("jTextField3");

        campoCalculoPagamentoLocacao.setText("jTextField4");
        campoCalculoPagamentoLocacao.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        campoCalculoPagamentoLocacao.setEnabled(false);

        botaoLocar.setText("Locar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollVeiculo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campoBuscaCliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeDoMenuLocacao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(radioBuscaCPF)
                                .addGap(18, 18, 18)
                                .addComponent(radioBuscaNome)
                                .addGap(18, 18, 18)
                                .addComponent(radioBuscaSobrenome))
                            .addComponent(btnBuscarCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoID, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(radioFiltroTipo))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(radioFiltroMarca))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNomeSobrenome)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioFiltroCategoria)
                                    .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(placaVeiculoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(valorDiariaVeiculoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoDiasDeAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoCalculoPagamentoLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoLocar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nomeDoMenuLocacao)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioBuscaCPF)
                            .addComponent(radioBuscaNome)
                            .addComponent(radioBuscaSobrenome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(btnBuscarCliente))
                    .addComponent(scrollCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoID, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoNomeSobrenome, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(campoCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioFiltroTipo)
                    .addComponent(radioFiltroMarca)
                    .addComponent(radioFiltroCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placaVeiculoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorDiariaVeiculoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDiasDeAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCalculoPagamentoLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLocar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radioFiltroTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFiltroTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioFiltroTipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoLocar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JTextField campoBuscaCliente;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JTextField campoCalculoPagamentoLocacao;
    private javax.swing.JTextField campoDiasDeAluguel;
    private javax.swing.JTextField campoID;
    private javax.swing.JTextField campoNomeSobrenome;
    private javax.swing.ButtonGroup clienteFiltro;
    private javax.swing.JComboBox<String> comboCategoria;
    private javax.swing.JComboBox<String> comboMarca;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel nomeDoMenuLocacao;
    private javax.swing.JTextField placaVeiculoSelecionado;
    private javax.swing.JRadioButton radioBuscaCPF;
    private javax.swing.JRadioButton radioBuscaNome;
    private javax.swing.JRadioButton radioBuscaSobrenome;
    private javax.swing.JRadioButton radioFiltroCategoria;
    private javax.swing.JRadioButton radioFiltroMarca;
    private javax.swing.JRadioButton radioFiltroTipo;
    private javax.swing.JScrollPane scrollCliente;
    private javax.swing.JScrollPane scrollVeiculo;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblVeiculo;
    private javax.swing.JTextField valorDiariaVeiculoSelecionado;
    private javax.swing.ButtonGroup veiculoFiltro;
    // End of variables declaration//GEN-END:variables
}
