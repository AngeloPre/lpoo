/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author victor
 */

import dao.VeiculoDAO;
import java.util.List;
import java.util.stream.Collectors;
import model.Veiculo;
import model.enums.Estado;

public class DevolucaoController {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    public List<Veiculo> listarVeiculosLocados() {
        return veiculoDAO.listarTodos().stream()
                .filter(v -> v.getEstado() == Estado.LOCADO)
                .collect(Collectors.toList());
    }

    public void devolverVeiculo(Veiculo veiculo) {
        if (veiculo != null && veiculo.getEstado() == Estado.LOCADO) {
            veiculo.devolver(); // O método devolver já muda o estado para DISPONIVEL e zera a locacao.
        } else {
            throw new IllegalStateException("Este veículo não pode ser devolvido.");
        }
    }
}
