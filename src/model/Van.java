package model;

import model.enums.Categoria;
import model.enums.Estado;
import model.enums.Marca;
import model.enums.ModeloVan;

public class Van extends Veiculo{

    private ModeloVan modelo;

    public Van(Marca marca, 
            Estado estado, 
            Locacao locacao, 
            Categoria categoria, 
            double valorDeCompra, 
            String placa,
            int ano,
            ModeloVan modelo) 
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
                valor = 200;
                break;
            case INTERMEDIARIO:
                valor = 400;
                break;
            case LUXO:
                valor = 600;
                break;
            default:
                throw new IllegalArgumentException("Categoria desconhecida: " + categoria);
        }
        return valor;
    }

    ModeloVan getModelo(){
        return modelo;
    }

}
