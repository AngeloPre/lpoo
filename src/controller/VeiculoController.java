package controller;

import banco.VeiculoDaoSql;
import java.util.List;
import model.Veiculo;
import model.enums.Estado;
import table.VeiculoTableModel;

public class VeiculoController {

    private final VeiculoDaoSql dao = new VeiculoDaoSql();
    private final VeiculoTableModel veiculoTableDataModel;

    public VeiculoController(VeiculoTableModel veiculoTableDataModel) {
        this.veiculoTableDataModel = veiculoTableDataModel;
    }

    public void listarVeiculos() {
        try {
            List<Veiculo> veiculos = dao.getAll();
            veiculoTableDataModel.setVeiculos(veiculos);
        } catch (Exception e) {}
    }

    public void incluirVeiculo(Veiculo veiculo) {
        try {
            dao.add(veiculo);
        } catch (Exception e) {}
        listarVeiculos();
    }
    
    public Estado[] estadosVeiculoNovo(){
        Estado[] novo = {Estado.DISPONIVEL, Estado.NOVO};
        return novo;
    }

}
