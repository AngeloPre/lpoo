package controller;

import dao.VeiculoDAO;
import java.util.List;
import model.Veiculo;
import table.VeiculoTableModel;

public class VeiculoController {

    private VeiculoDAO dao = new VeiculoDAO();
    private VeiculoTableModel veiculoTableDataModel;

    public VeiculoController(VeiculoTableModel veiculoTableDataModel) {
        this.veiculoTableDataModel = veiculoTableDataModel;
    }

    public void listarVeiculos() {
        List<Veiculo> veiculos = dao.listarTodos();
        veiculoTableDataModel.setVeiculos(veiculos);
    }

    public void incluirVeiculo(Veiculo veiculo) {
        dao.adicionarVeiculo(veiculo);
        listarVeiculos();
    }

}
