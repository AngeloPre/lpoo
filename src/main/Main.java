package main;

import banco.ClienteDao;
import banco.ClienteDaoSql;
import banco.DatabaseInitializer;
import banco.LocacaoDao;
import banco.LocacaoDaoSql;
import banco.VeiculoDao;
import banco.VeiculoDaoSql;
import controller.ClienteController;
import controller.DevolucaoController;
import controller.LocacaoController;
import controller.VeiculoController;
import controller.VendaVeiculoController;
import javax.swing.SwingUtilities;
import view.TelaPrincipal;

public class Main {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseInitializer.initialize();
                } catch (Exception ex) {
                    System.out.println("Erro nos scripts de inicialização do banco");
                    ex.printStackTrace();
                }

                //Daos
                VeiculoDao veiculoDAO = new VeiculoDaoSql();
                ClienteDao clienteDAO = new ClienteDaoSql();
                LocacaoDao locacaoDAO = new LocacaoDaoSql();

                //View principal
                TelaPrincipal tp = new TelaPrincipal();
                tp.setVisible(true);

                //Controllers Novos
                new VeiculoController(tp.getVeiculoPanel(), veiculoDAO);
                new LocacaoController(tp.getLocacaoPanel(), veiculoDAO, clienteDAO, locacaoDAO);
                new ClienteController(tp.getClientePanel(), clienteDAO, veiculoDAO);
                new VendaVeiculoController(tp.getVendaPanel(), veiculoDAO);
                new DevolucaoController(tp.getDevolucaoPanel(), veiculoDAO, locacaoDAO);

            }
        });

    }
}
