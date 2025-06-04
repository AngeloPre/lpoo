package main;

import controller.ClienteController;
import javax.swing.SwingUtilities;
import table.ClienteTableModel;
import view.TelaPrincipal;

public class Main {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Instanciação e conexão dos componentes para a tela de Clientes
                ClienteTableModel clienteTableModel = new ClienteTableModel();
                ClienteController clienteController = new ClienteController(clienteTableModel);
                TelaPrincipal tp = new TelaPrincipal(clienteController, clienteTableModel);
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
