package main;

import controller.ClienteController;
import controller.LocacaoController;
import javax.swing.SwingUtilities;
import table.ClienteTableModel;
import table.VeiculoTableModel;
import view.TelaPrincipal;

public class Main {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Instanciação e conexão dos componentes para a tela de Clientes
                ClienteTableModel clienteTableModel = new ClienteTableModel();
                VeiculoTableModel veiculoTableModel = new VeiculoTableModel();
                ClienteController clienteController = new ClienteController(clienteTableModel);
                LocacaoController locacaoController = new LocacaoController(clienteTableModel, veiculoTableModel);
                TelaPrincipal tp = new TelaPrincipal(clienteController, clienteTableModel, locacaoController, veiculoTableModel);
                tp.setVisible(true);

                // Inicializa a tabela de clientes
                

                // Para outras telas, você faria algo similar:
                // VeiculoController veiculoController = new VeiculoController();
                // VeiculoCadastroView veiculoCadastroView = new VeiculoCadastroView(veiculoController);
                // veiculoCadastroView.setVisible(true);

                // E assim por diante para LocacaoView, DevolucaoView, VendaView
            }
        });

    }
}
