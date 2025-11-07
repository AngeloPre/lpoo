package controller;

import banco.VeiculoDaoSql;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Veiculo;
import model.enums.Estado;
import table.VendaVeiculoTableModel;

public class VendaVeiculoController {
    private final VeiculoDaoSql veiculoDAO = new VeiculoDaoSql();
    private final VendaVeiculoTableModel vendaVeiculoTableModel;

    public VendaVeiculoController(VendaVeiculoTableModel tableModel) {
        this.vendaVeiculoTableModel = tableModel;
    }
    
    public List<Veiculo> listarVeiculosParaVenda() {
        try {
            return veiculoDAO.getAll().stream()
                .filter(v -> v.getEstado() == Estado.DISPONIVEL)
                .collect(Collectors.toList());
        } catch (Exception e) {return new ArrayList<>();}
    }

    public void venderVeiculo(Veiculo veiculo) { 
    if (veiculo != null && veiculo.getEstado() == Estado.DISPONIVEL) {
        veiculo.vender();
       try {
           veiculoDAO.update(veiculo);
       } catch (Exception e) {
       }
    } else {
        throw new IllegalStateException("Veículo nulo ou não está disponível para venda.");
    }
}

    public void atualizarTabela() {
        try {
            List<Veiculo> veiculosParaVenda = veiculoDAO.getAll().stream()
                .filter(v -> v.getEstado() == model.enums.Estado.DISPONIVEL)
                .collect(java.util.stream.Collectors.toList());
        
            // atualiza a lista de veículos no TableModel
            vendaVeiculoTableModel.setVeiculos(veiculosParaVenda);
        } catch (Exception e) {
        }
    }

}
