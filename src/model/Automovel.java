package model;

import model.enums.Categoria;
import model.enums.Estado;
import model.enums.Marca;
import model.enums.ModeloAutomovel;

public class Automovel extends Veiculo {

    private ModeloAutomovel modelo;

    public Automovel(Marca marca, 
                    Estado estado, 
                    Locacao locacao,
                    Categoria categoria, 
                    double valorDeCompra,
                    String placa, 
                    int ano,
                    ModeloAutomovel modelo) 
    {
        super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }

    @Override
    public double getValorDiariaLocacao() {
        double valor = 0;
        Categoria categoria = super.getCategoria();

        if (categoria == null) {
            throw new IllegalStateException("Categoria não pode ser nula.");
        }

        switch (categoria) {
            case POPULAR:
                valor = 100;
                break;
            case INTERMEDIARIO:
                valor = 300;
                break;
            case LUXO:
                valor = 450;
                break;
            default:
                throw new IllegalArgumentException("Categoria desconhecida: " + categoria);
        }
        return valor;
    }

    public ModeloAutomovel getModelo(){
        return modelo;
    }

}
