package controller;

import banco.VeiculoDao;
import java.util.List;
import java.util.stream.Collectors;
import model.Veiculo;
import model.enums.Estado;
import view.VendaPanel;

public class VendaVeiculoController {

    private final VendaPanel view;
    private final VeiculoDao veiculoDAO;

    public VendaVeiculoController(VendaPanel view, VeiculoDao veiculoDAO) {
        this.view = view;
        this.veiculoDAO = veiculoDAO;
        initController();
    }

    private void initController() {
        view.setController(this);
        listarVeiculosParaVenda();
    }

    public void listarVeiculosParaVenda() {
        try {
            List<Veiculo> disponiveis = veiculoDAO.getAll().stream()
                .filter(v -> v.getEstado() == Estado.DISPONIVEL)
                .collect(Collectors.toList());
            view.mostrarVeiculos(disponiveis);
            view.aplicarFiltrosUI();
        } catch (Exception e) {
            view.apresentaErro("Erro ao listar veículos para venda.");
            e.printStackTrace();
        }
    }

    public void venderVeiculo() {
        try {
            Veiculo veiculo = view.getVeiculoSelecionado();
            if (veiculo == null) {
                view.apresentaInfo("Selecione um veículo para vender.");
                return;
            }
            if (veiculo.getEstado() != Estado.DISPONIVEL) {
                view.apresentaErro("Veículo não está disponível para venda.");
                return;
            }

            veiculo.vender();
            veiculoDAO.update(veiculo);

            view.apresentaInfo("Veículo vendido com sucesso!");
            listarVeiculosParaVenda();
            view.limparSelecao();

        } catch (Exception e) {
            view.apresentaErro("Erro ao vender veículo.");
            e.printStackTrace();
        }
    }
}
