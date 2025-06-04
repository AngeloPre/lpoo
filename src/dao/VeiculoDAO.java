package dao;

import java.util.ArrayList;
import java.util.List;
import model.Veiculo;

public class VeiculoDAO {
    
    private static List<Veiculo> veiculos = new ArrayList<>();

    public void adicionarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }

}
