package controller;

import banco.LocacaoDao;
import banco.VeiculoDao;
import java.util.List;
import java.util.stream.Collectors;
import model.Locacao;
import model.Veiculo;
import model.enums.Estado;
import view.DevolucaoPanel;

public class DevolucaoController {

    private final DevolucaoPanel view;
    private final VeiculoDao veiculoDao;
    private final LocacaoDao locacaoDao;

    public DevolucaoController(DevolucaoPanel view, VeiculoDao veiculoDao, LocacaoDao locacaoDao) {
        this.view = view;
        this.veiculoDao = veiculoDao;
        this.locacaoDao = locacaoDao;
        initController();
    }

    private void initController() {
        view.setController(this);
        listarVeiculosLocados();
    }

    public void listarVeiculosLocados() {
        try {
            List<Veiculo> locados = veiculoDao.getAll().stream()
                .filter(v -> v.getEstado() == Estado.LOCADO)
                .collect(Collectors.toList());
            view.mostrarVeiculosLocados(locados);
        } catch (Exception e) {
            view.apresentaErro("Erro ao listar veículos locados.");
            e.printStackTrace();
        }
    }

    public void devolverVeiculo() {
        try {
            Veiculo veiculo = view.getVeiculoSelecionado();
//            System.out.println("Veiculo da tela" + veiculo);
            if (veiculo == null) {
                view.apresentaInfo("Selecione um veículo para devolver.");
                return;
            }
            if (veiculo.getEstado() != Estado.LOCADO) {
                view.apresentaErro("Este veículo não está locado.");
                return;
            }
            Veiculo veiculoBanco = veiculoDao.getById(veiculo.getId());
            Locacao deletarLocacao = veiculoBanco.getLocacao();
//            System.out.println("Veiculo do banco" + veiculo);
            veiculoBanco.devolver();
            veiculoDao.update(veiculoBanco);
            locacaoDao.delete(deletarLocacao);

            view.apresentaInfo("Veículo devolvido com sucesso!");
            listarVeiculosLocados();
            view.limparSelecao();
        } catch (Exception e) {
            view.apresentaErro("Erro ao devolver veículo.");
            e.printStackTrace();
        }
    }
}
