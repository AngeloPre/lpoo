package controller;

import banco.VeiculoDao;
import banco.VeiculoDaoSql;
import model.Veiculo;
import view.VeiculoPanel;

public class VeiculoController {
    private VeiculoPanel view;
    private VeiculoDao dao;

    public VeiculoController(VeiculoDaoSql veiculoDaoSql) {
    }
    
    public VeiculoController(VeiculoPanel view, VeiculoDao dao) {
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
