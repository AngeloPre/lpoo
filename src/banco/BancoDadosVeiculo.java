package banco;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Automovel;
import model.Motocicleta;
import model.Van;
import model.Veiculo;
import model.enums.Categoria;
import model.enums.Estado;
import model.enums.Marca;
import model.enums.ModeloAutomovel;
import model.enums.ModeloMotocicleta;
import model.enums.ModeloVan;

public class BancoDadosVeiculo {

    private static List<Veiculo> veiculos = new ArrayList<>();

    static {
        // veiculos.add(new Automovel(Marca.Fiat,  Estado.DISPONIVEL, null, Categoria.POPULAR,  45000, "ABC-1234", 2020));
        veiculos.add(new Automovel(Marca.Fiat, Estado.NOVO, null, Categoria.INTERMEDIARIO, 1500, "RSC-6885", 1995, ModeloAutomovel.Palio));
        veiculos.add(new Automovel(Marca.GM, Estado.NOVO, null, Categoria.POPULAR, 1500, "CAP-6775", 2005, ModeloAutomovel.Celta));
        veiculos.add(new Automovel(Marca.Fiat, Estado.NOVO, null, Categoria.POPULAR, 48000, "BRA-2E19", 2023, ModeloAutomovel.Palio));
        veiculos.add(new Motocicleta(Marca.Honda, Estado.DISPONIVEL, null, Categoria.POPULAR, 15000, "JAH-8B17", 2024, ModeloMotocicleta.CG_125));
        veiculos.add(new Van(Marca.VW, Estado.DISPONIVEL, null, Categoria.INTERMEDIARIO, 150000, "VAN-4G58", 2021, ModeloVan.Kombi));
    }
    
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Veiculo buscarPorPlaca(String placa) {
        String placaNormalizada = normalizarPlaca(placa);

        return veiculos.stream()
                .filter(v -> normalizarPlaca(v.getPlaca())
                .equals(placaNormalizada))
                .findFirst()
                .orElseThrow(()
                        -> new RuntimeException("Placa não encontrada: " + placa));
    }

    private String normalizarPlaca(String placa) {
        if (placa == null) {
            return "";
        }
        return placa.replace("-", "")
                .replace(" ", "")
                .toUpperCase();
    }
    
    public List<Veiculo> listarVeiculosNaoLocados() {
        return filtrarPorVeiculosSemLocacao();
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
    
    private List<Veiculo> filtrarPorVeiculosSemLocacao() {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getEstado().equals(Estado.NOVO) || veiculo.getEstado().equals(Estado.DISPONIVEL) )
                .collect(Collectors.toList());
    }
   
}
