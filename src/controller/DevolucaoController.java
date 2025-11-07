package controller;

import banco.VeiculoDaoSql;
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;
import model.enums.Estado;

public class DevolucaoController {
    private final VeiculoDaoSql veiculoDao = new VeiculoDaoSql();
    //private BancoDadosVeiculo veiculoDAO = new BancoDadosVeiculo();

    public List<Veiculo> listarVeiculosLocados() {
        try {
            return veiculoDao.getFreeVehicles();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void devolverVeiculo(Veiculo veiculo) {

        if (veiculo != null && veiculo.getEstado() == Estado.LOCADO) {
            veiculo.devolver(); // O método devolver já muda o estado para DISPONIVEL e zera a locacao.
            try {
                veiculoDao.update(veiculo);
            } catch (Exception e) {}
        } else {
            throw new IllegalStateException("Este veículo não pode ser devolvido.");
        }
    }
}
