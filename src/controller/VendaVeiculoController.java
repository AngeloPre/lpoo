/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import banco.BancoDadosVeiculo;
import java.util.List;
import java.util.stream.Collectors;
import model.Veiculo;
import model.enums.Estado;
import table.VendaVeiculoTableModel;
/**
 *
 * @author mrVictor
 */
public class VendaVeiculoController {
    private BancoDadosVeiculo veiculoDAO = new BancoDadosVeiculo();
    private final VendaVeiculoTableModel vendaVeiculoTableModel;

    public VendaVeiculoController(VendaVeiculoTableModel tableModel) {
        this.veiculoDAO = new BancoDadosVeiculo();
        this.vendaVeiculoTableModel = tableModel;
    }
    
    public List<Veiculo> listarVeiculosParaVenda() {
        return veiculoDAO.listarTodos().stream()
                .filter(v -> v.getEstado() == Estado.DISPONIVEL)
                .collect(Collectors.toList());
    }

    public void venderVeiculo(Veiculo veiculo) { 
    if (veiculo != null && veiculo.getEstado() == Estado.DISPONIVEL) {
        veiculo.vender();
       
    } else {
        throw new IllegalStateException("Veículo nulo ou não está disponível para venda.");
    }
}

    public void atualizarTabela() {
        List<Veiculo> veiculosParaVenda = veiculoDAO.listarTodos().stream()
                .filter(v -> v.getEstado() == model.enums.Estado.DISPONIVEL)
                .collect(java.util.stream.Collectors.toList());
        
        // atualiza a lista de veículos no TableModel
        vendaVeiculoTableModel.setVeiculos(veiculosParaVenda);
    }

}
