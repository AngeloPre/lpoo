package model;

import model.enums.Categoria;
import model.enums.Estado;
import model.enums.Marca;
import model.enums.ModeloMotocicleta;

public class Motocicleta extends Veiculo{

    private ModeloMotocicleta modelo;

    public Motocicleta(Marca marca, 
                        Estado estado, 
                        Locacao locacao, 
                        Categoria categoria, 
                        double valorDeCompra,
                        String placa, 
                        int ano, 
                        ModeloMotocicleta modelo) 
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
                valor = 70;
                break;
            case INTERMEDIARIO:
                valor = 200;
                break;
            case LUXO:
                valor = 350;
                break;
            default:
                throw new IllegalArgumentException("Categoria desconhecida: " + categoria);
        }
        return valor;
    }

    public ModeloMotocicleta getModelo(){
        return modelo;
    }

}
