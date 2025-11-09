/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author mrblue
 */
public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);//esse cara aqui faz com que a janela principal inicie centralizada na tela
    }

    public ClientePanel getClientePanel() {
        return clientePanel1;
    }

    public DevolucaoPanel getDevolucaoPanel() {
        return devolucaoPanel1;
    }

    public LocacaoPanelView getLocacaoPanel() {
        return locacaoPanelView1;
    }

    public VendaPanel getVendaPanel() {
        return vendaPanel1;
    }

    public VeiculoPanel getVeiculoPanel() {
        return veiculoPanel1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        clientePanel1 = new view.ClientePanel();
        veiculoPanel1 = new view.VeiculoPanel();
        locacaoPanelView1 = new view.LocacaoPanelView();
        vendaPanel1 = new view.VendaPanel();
        devolucaoPanel1 = new view.DevolucaoPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addTab("Clientes", clientePanel1);
        jTabbedPane1.addTab("Incluir Veiculos", veiculoPanel1);
        jTabbedPane1.addTab("Locação de Veículos", locacaoPanelView1);
        jTabbedPane1.addTab("Venda de Veículos", vendaPanel1);
        jTabbedPane1.addTab("Devolução de Veículos", devolucaoPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1196, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

        int index = jTabbedPane1.getSelectedIndex();
        switch (index) {
            case 2: // Aba de Locação
                this.locacaoPanelView1.pesquisarVeiculosEPessoasPublic();
                break;
            case 3: // Aba de Venda de Veículos 
                this.vendaPanel1.pesquisarVeiculosPublic();
                break;
            case 4: // Aba de Devolução
                this.devolucaoPanel1.carregarDadosPublic();
                break;
            default:
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.ClientePanel clientePanel1;
    private view.DevolucaoPanel devolucaoPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private view.LocacaoPanelView locacaoPanelView1;
    private view.VeiculoPanel veiculoPanel1;
    private view.VendaPanel vendaPanel1;
    // End of variables declaration//GEN-END:variables
}
