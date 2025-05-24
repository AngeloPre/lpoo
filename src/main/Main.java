package main;

import controller.ClienteController;
import javax.swing.SwingUtilities;
import table.ClienteTableModel;
import view.ClienteView;

public class Main {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Instanciação e conexão dos componentes para a tela de Clientes
                ClienteTableModel clienteTableModel = new ClienteTableModel();
                ClienteController clienteController = new ClienteController(clienteTableModel);
                ClienteView clienteView = new ClienteView(clienteController, clienteTableModel);
                clienteView.setVisible(true);

                // Inicializa a tabela de clientes
                clienteController.listarClientes();

                // Para outras telas, você faria algo similar:
                // VeiculoController veiculoController = new VeiculoController();
                // VeiculoCadastroView veiculoCadastroView = new VeiculoCadastroView(veiculoController);
                // veiculoCadastroView.setVisible(true);

                // E assim por diante para LocacaoView, DevolucaoView, VendaView
            }
        });

    }
}
