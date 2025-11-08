package main;

import banco.VeiculoDaoSql;
import controller.ClienteController;
import controller.DevolucaoController;
import controller.LocacaoController;
import controller.VeiculoController;
import controller.VeiculoPanelController;
import controller.VendaVeiculoController;
import javax.swing.SwingUtilities;
import table.ClienteTableModel;
import table.DevolucaoTableModel;
import table.VeiculoTableModel;
import table.VendaVeiculoTableModel;
import view.TelaPrincipal;

public class Main {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Instanciação e conexão dos componentes para a tela de Clientes
                
                //Table Model
                ClienteTableModel clienteTableModel = new ClienteTableModel();
                VeiculoTableModel veiculoTableModel = new VeiculoTableModel();
                DevolucaoTableModel devolucaoTableModel = new DevolucaoTableModel();
                VendaVeiculoTableModel vendaVeiculoTableModel = new VendaVeiculoTableModel();              
                //Controllers
                ClienteController clienteController = new ClienteController(clienteTableModel);
                LocacaoController locacaoController = new LocacaoController(clienteTableModel, veiculoTableModel);
                VeiculoController veiculoController = new VeiculoController(veiculoTableModel);
                DevolucaoController devolucaoController = new DevolucaoController();
                
                VendaVeiculoController vendaVeiculoController = new VendaVeiculoController(vendaVeiculoTableModel);
                
                //Daos
                VeiculoDaoSql veiculoDaoSql = new VeiculoDaoSql();
                
                TelaPrincipal tp = new TelaPrincipal(
                        clienteController, 
                        clienteTableModel,
                        locacaoController,
                        veiculoTableModel,
                        veiculoController,
                        devolucaoController,
                        devolucaoTableModel,
                        vendaVeiculoController,
                        vendaVeiculoTableModel);
                tp.setVisible(true);
                
                                //Controllers Novos
                VeiculoPanelController veiculoPanelController = new VeiculoPanelController(tp.getVeiculoPanel() ,veiculoDaoSql);

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
