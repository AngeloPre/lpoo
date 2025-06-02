package controller;

import dao.VeiculoDAO;
import model.Veiculo;

public class VeiculoController {

    private VeiculoDAO dao = new VeiculoDAO();

    public void incluirVeiculo(Veiculo veiculo) {
        dao.adicionarVeiculo(veiculo);
    } 

}
