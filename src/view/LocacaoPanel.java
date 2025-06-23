/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import service.VeiculoService;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import controller.LocacaoController;
import java.awt.HeadlessException;
import model.Veiculo;
import table.ClienteTableModel;
import table.VeiculoTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JOptionPane;
import static service.VeiculoService.*;
import util.RegraNegocioException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author tads
 */
public class LocacaoPanel extends javax.swing.JPanel {

    private LocacaoController locacaoController;
    private ClienteTableModel clienteTableModel;
    private VeiculoTableModel veiculoTableModel;
    private TableRowSorter<ClienteTableModel> clienteSorter;
    private TableRowSorter<VeiculoTableModel> veiculoSorter;

    public LocacaoPanel() {
        initComponents();
    }

    public LocacaoPanel(LocacaoController locacaoController, ClienteTableModel clienteTableModel, VeiculoTableModel veiculoTableModel) {
        this.locacaoController = locacaoController;
        this.clienteTableModel = clienteTableModel;
        this.veiculoTableModel = veiculoTableModel;
        this.locacaoController.listarClientes();
        this.locacaoController.listarVeiculos();
        initComponents();
        configurarSorterCliente();
        configurarSorterVeiculos();
        configurarRadioButtons();
        popularCombos();
        limparCampos();
        this.tblCliente.setModel(clienteTableModel);
        this.tblVeiculo.setModel(veiculoTableModel);
        btnBuscarCliente.addActionListener(this::botaoBuscarActionPerformed);

        tblCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tblCliente.getSelectedRow() != -1) {
                    preencherCamposClienteSelecionado();
                }
            }
        });

        // quando o usuário digitar / apagar no campo de dias
        campoDiasDeAluguel.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculaPagamento();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculaPagamento();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculaPagamento();
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

    private void configurarSorterCliente() {
        clienteSorter = new TableRowSorter<>(clienteTableModel);
        tblCliente.setRowSorter(clienteSorter);
    }

    private void configurarSorterVeiculos() {
        veiculoSorter = new TableRowSorter<>(veiculoTableModel);
        tblVeiculo.setRowSorter(veiculoSorter);

        // quando o usuário clicar numa linha, preencher campos
        tblVeiculo.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblVeiculo.getSelectedRow() != -1) {
                preencherCamposVeiculoSelecionado();
            }
        });
    }

    private void aplicarFiltroCliente(int coluna, String texto) {
        if (texto == null || texto.isBlank()) {
            clienteSorter.setRowFilter(null);
            return;
        }

        // escapando caracteres especiais para evitar regex inválido
        String regex = Pattern.quote(texto.trim());
        clienteSorter.setRowFilter(RowFilter.regexFilter("(?i)" + regex, coluna));
    }

    private void filtrarPorCpf(String cpf) {
        aplicarFiltroCliente(4, cpf);
    }

    private void filtrarPorNome(String nome) {
        aplicarFiltroCliente(1, nome);
    }

    private void filtrarPorSobrenome(String sobrenome) {
        aplicarFiltroCliente(2, sobrenome);
    }

    private void buscarPorCpf() {
        filtrarPorCpf(campoBuscaCliente.getText());
        limparCamposFiltro();
    }

    private void buscarPorNome() {
        filtrarPorNome(campoBuscaCliente.getText());
        limparCamposFiltro();
    }

    private void buscarPorSobrenome() {
        filtrarPorSobrenome(campoBuscaCliente.getText());
        limparCamposFiltro();
    }

    private void limparCamposFiltro() {
        campoBuscaCliente.setText("");
    }

    private void preencherCamposClienteSelecionado() {
        int viewRow = tblCliente.getSelectedRow();          // linha que o usuário clicou
        if (viewRow == -1) {
            return;
        }

        int modelRow = tblCliente.convertRowIndexToModel(viewRow); // converte view para model

        String id = clienteTableModel.getValueAt(modelRow, 0).toString();
        String cpf = clienteTableModel.getValueAt(modelRow, 4).toString();
        String nome = clienteTableModel.getValueAt(modelRow, 1).toString();
        String sobrenome = clienteTableModel.getValueAt(modelRow, 2).toString();

        campoID.setText(id);
        campoCPF.setText(cpf);
        campoNomeSobrenome.setText(nome + " " + sobrenome);
    }

    private void preencherCamposVeiculoSelecionado() {
        int viewRow = tblVeiculo.getSelectedRow();
        if (viewRow == -1) {
            return;
        }

        int modelRow = tblVeiculo.convertRowIndexToModel(viewRow);

        String placa = veiculoTableModel.getValueAt(modelRow, 0).toString();
        String preco = veiculoTableModel.getValueAt(modelRow, 4).toString();

        placaVeiculoSelecionado.setText(placa);
        valorDiariaVeiculoSelecionado.setText(preco);

        calculaPagamento();   // ← recalcule já com a nova diária
    }

    private void popularCombos() {
        /* tipo (automóvel / moto / van)  */
        comboTipo.setModel(
                new javax.swing.DefaultComboBoxModel<>(
                        obterTiposVeiculo()
                )
        );

        /* marca (VW, GM, Fiat, …)  */
        comboMarca.setModel(
                new javax.swing.DefaultComboBoxModel<>(
                        obterMarcas()
                )
        );

        /* categoria (POPULAR, INTERMEDIARIO, LUXO)  */
        comboCategoria.setModel(
                new javax.swing.DefaultComboBoxModel<>(
                        obterCategorias()
                )
        );
    }

    private void configurarRadioButtons() {
        clienteFiltro = new ButtonGroup();
        clienteFiltro.add(radioBuscaCPF);
        clienteFiltro.add(radioBuscaNome);
        clienteFiltro.add(radioBuscaSobrenome);

        radioBuscaCPF.setActionCommand("CPF");
        radioBuscaNome.setActionCommand("NOME");
        radioBuscaSobrenome.setActionCommand("SOBRENOME");
        radioBuscaCPF.setSelected(true);      // opção padrão
        checkboxFiltroTipo.setSelected(true);      // opção padrão
    }

    private String getCriterioSelecionado() {
        return clienteFiltro.getSelection().getActionCommand();
    }

    private void calculaPagamento() {

        /* ---------- 1) dias de aluguel ---------- */
        String diasTxt = campoDiasDeAluguel.getText().trim();
        if (diasTxt.isBlank()) {                       // campo vazio
            campoCalculoPagamentoLocacao.setText("0");
            return;
        }

        int dias;
        try {
            dias = Integer.parseInt(diasTxt);
            if (dias <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            campoCalculoPagamentoLocacao.setText("Dias deve ser inteiro > 0");
            return;
        }

        /* ---------- 2) diária vinda do objeto selecionado ---------- */
        int viewRow = tblVeiculo.getSelectedRow();
        if (viewRow == -1) {
            campoCalculoPagamentoLocacao.setText("Selecione um veículo");
            return;
        }

        int modelRow   = tblVeiculo.convertRowIndexToModel(viewRow);
        Veiculo vSel   = veiculoTableModel.getVeiculo(modelRow); // objeto
        double diaria  = vSel.getValorDiariaLocacao();

        /* ---------- 3) cálculo ---------- */
        double total = dias * diaria;

        NumberFormat nfBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        campoCalculoPagamentoLocacao.setText(nfBR.format(total)); // ex.:
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
        campoDataLocacao.setText("");
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
        comboTipo = new javax.swing.JComboBox<>();
        comboMarca = new javax.swing.JComboBox<>();
        comboCategoria = new javax.swing.JComboBox<>();
        placaVeiculoSelecionado = new javax.swing.JTextField();
        valorDiariaVeiculoSelecionado = new javax.swing.JTextField();
        campoDiasDeAluguel = new javax.swing.JTextField();
        campoCalculoPagamentoLocacao = new javax.swing.JTextField();
        botaoLocar = new javax.swing.JButton();
        botaoPesquisarveiculos = new javax.swing.JButton();
        checkboxFiltroTipo = new javax.swing.JCheckBox();
        checkboxFiltroMarca = new javax.swing.JCheckBox();
        checkboxFiltroCategoria = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoDataLocacao = new javax.swing.JFormattedTextField();

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

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        botaoLocar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLocarActionPerformed(evt);
            }
        });

        botaoPesquisarveiculos.setText("Pesquisar Veículos");
        botaoPesquisarveiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarveiculosActionPerformed(evt);
            }
        });

        checkboxFiltroTipo.setText("Filtrar Veiculos por Tipo");

        checkboxFiltroMarca.setText("Filtrar Veiculos por Marca");
        checkboxFiltroMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxFiltroMarcaActionPerformed(evt);
            }
        });

        checkboxFiltroCategoria.setText("Filtrar Veiculos por Categoria");

        jLabel1.setText("Placa");

        jLabel2.setText("Valor Diária");

        jLabel4.setText("Dias de Aluguel");

        jLabel3.setText("Total da Locação");

        jLabel5.setText("Data da Locação");

        try {
            campoDataLocacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(checkboxFiltroTipo))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(checkboxFiltroMarca))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNomeSobrenome)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(botaoPesquisarveiculos))
                                    .addComponent(checkboxFiltroCategoria))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollVeiculo)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(placaVeiculoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valorDiariaVeiculoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoDiasDeAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(campoCalculoPagamentoLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoDataLocacao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoLocar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(scrollCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoID, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoNomeSobrenome, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(campoCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkboxFiltroTipo)
                    .addComponent(checkboxFiltroMarca)
                    .addComponent(checkboxFiltroCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisarveiculos))
                .addGap(18, 18, 18)
                .addComponent(scrollVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placaVeiculoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorDiariaVeiculoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDiasDeAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCalculoPagamentoLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLocar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoDataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void pesquisar() {
        locacaoController.listarVeiculos();
        VeiculoService.aplicarFiltrosVeiculo(veiculoSorter, checkboxFiltroTipo, checkboxFiltroMarca, checkboxFiltroCategoria, comboCategoria, comboMarca, comboTipo);
    }

    private void botaoPesquisarveiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarveiculosActionPerformed
        locacaoController.listarVeiculos();
        VeiculoService.aplicarFiltrosVeiculo(veiculoSorter, checkboxFiltroTipo, checkboxFiltroMarca, checkboxFiltroCategoria, comboCategoria, comboMarca, comboTipo);
    }//GEN-LAST:event_botaoPesquisarveiculosActionPerformed

    private void botaoLocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLocarActionPerformed
        // validando se um cliente foi selecionado
        if (campoID.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // validando se um veículo foi selecionado na tabela
        int selectedRow = tblVeiculo.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um veículo para locar.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //validando o número de dias
        int dias;
        try {
            dias = Integer.parseInt(campoDiasDeAluguel.getText());
            if (dias <= 0) {
                JOptionPane.showMessageDialog(this, "O número de dias deve ser maior que zero.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido de dias.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //validar e converter a data
        Calendar dataLocacao = Calendar.getInstance();
        String dataTexto = campoDataLocacao.getText();

        if (dataTexto != null && !dataTexto.trim().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false); // serve para impedir datas inválidas como 32/01/2025
                Date date = sdf.parse(dataTexto);
                dataLocacao.setTime(date);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Data Inválida.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "O campo de data é obrigatório.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // tentando obter dados para a locação
            int modelRow = tblVeiculo.convertRowIndexToModel(selectedRow);
            Veiculo veiculoSelecionado = veiculoTableModel.getVeiculo(modelRow);
            String cpfCliente = campoCPF.getText(); // O CPF já está no campo de texto

            // chamando o controller para efetuar a locação
            locacaoController.locar(veiculoSelecionado.getPlaca(), cpfCliente, dias, dataLocacao);

            JOptionPane.showMessageDialog(this, "Veículo locado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            //atualizando a lista de veículos e limpando os campos
            locacaoController.listarVeiculos(); // atualiza a tabela de veículos
            VeiculoService.aplicarFiltrosVeiculo(veiculoSorter, checkboxFiltroTipo, checkboxFiltroMarca, checkboxFiltroCategoria, comboCategoria, comboMarca, comboTipo); // reaplica os filtros para remover o veículo locado da lista
            limparCampos();

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Erro ao locar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(this, "Erro ao locar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoLocarActionPerformed

    private void checkboxFiltroMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxFiltroMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxFiltroMarcaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoLocar;
    private javax.swing.JButton botaoPesquisarveiculos;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JTextField campoBuscaCliente;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JTextField campoCalculoPagamentoLocacao;
    private javax.swing.JFormattedTextField campoDataLocacao;
    private javax.swing.JTextField campoDiasDeAluguel;
    private javax.swing.JTextField campoID;
    private javax.swing.JTextField campoNomeSobrenome;
    private javax.swing.JCheckBox checkboxFiltroCategoria;
    private javax.swing.JCheckBox checkboxFiltroMarca;
    private javax.swing.JCheckBox checkboxFiltroTipo;
    private javax.swing.ButtonGroup clienteFiltro;
    private javax.swing.JComboBox<String> comboCategoria;
    private javax.swing.JComboBox<String> comboMarca;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel nomeDoMenuLocacao;
    private javax.swing.JTextField placaVeiculoSelecionado;
    private javax.swing.JRadioButton radioBuscaCPF;
    private javax.swing.JRadioButton radioBuscaNome;
    private javax.swing.JRadioButton radioBuscaSobrenome;
    private javax.swing.JScrollPane scrollCliente;
    private javax.swing.JScrollPane scrollVeiculo;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblVeiculo;
    private javax.swing.JTextField valorDiariaVeiculoSelecionado;
    // End of variables declaration//GEN-END:variables
}
