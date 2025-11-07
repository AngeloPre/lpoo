package controller;

import banco.VeiculoDaoSql;
import model.Veiculo;
import view.VeiculoPanel;

public class VeiculoPanelController {

    public VeiculoPanelController(VeiculoDaoSql veiculoDaoSql) {
    }
    private VeiculoPanel view;
    private VeiculoDaoSql dao;

    public VeiculoPanelController(VeiculoPanel view, VeiculoDaoSql dao) {
        this.view = view;
        this.dao = dao;
        initController();
    }
    
    private void initController() {
        this.view.setController(this);
        //this.view.initView();
    }
    
    public void salvarVeiculo() {
        try {
            Veiculo veiculo = view.getVeiculoFormulario();
            dao.add(veiculo);
            view.apresentaInfo("Veiculo Salvo!");


        } catch (Exception ex) {
            view.apresentaErro("Erro ao salvar veículo: " + ex.getMessage());
        }
    }
}
