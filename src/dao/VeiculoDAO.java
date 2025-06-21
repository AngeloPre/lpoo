package dao;

import java.util.ArrayList;
import java.util.List;

import model.Automovel;
import model.Cliente;
import model.Veiculo;
import model.enums.Categoria;
import model.enums.Estado;
import model.enums.Marca;
import model.enums.ModeloAutomovel;

public class VeiculoDAO {
    
    private static List<Veiculo> veiculos = new ArrayList<>();

    static {
       // veiculos.add(new Automovel(Marca.Fiat,  Estado.DISPONIVEL, null, Categoria.POPULAR,  45000, "ABC-1234", 2020));
       veiculos.add(new Automovel(Marca.Fiat, Estado.DISPONIVEL, null, Categoria.LUXO, 1500, "cap-6665", 1995, ModeloAutomovel.Palio));
    }

    public void adicionarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }

    public Veiculo buscarPorPlaca(String placa) {
        String placaNormalizada = normalizarPlaca(placa);

        return veiculos.stream()
                       .filter(v -> normalizarPlaca(v.getPlaca())
                                        .equals(placaNormalizada))
                       .findFirst()
                       .orElseThrow(() ->
                             new RuntimeException("Placa não encontrada: " + placa));
    }

    private String normalizarPlaca(String placa) {
        if (placa == null) return "";
        return placa.replace("-", "")
                    .replace(" ", "")
                    .toUpperCase();
    }

    public List<Veiculo> listarTodos() {
        return new ArrayList<>(veiculos);
    }
    
    public boolean clientePossuiVeiculoLocado(int idCliente) {
    for (Veiculo veiculo : veiculos) {
        //verificando o estado do veiculo
        if (veiculo.getEstado() == Estado.LOCADO) {
            //verifica se a locacao é do cliente
            if (veiculo.getLocacao() != null && veiculo.getLocacao().getCliente().getId() == idCliente) {
                return true; //encontrou veiculo para o cliente
            }
        }
    }
    return false; 
}
}
