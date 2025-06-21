package model;

import java.time.Year;
import java.util.Calendar;

import model.enums.Categoria;
import model.enums.Estado;
import model.enums.Marca;
import util.RegraNegocioException;

public abstract class Veiculo implements VeiculoI {

    private Marca marca;
    private Estado estado;
    private Locacao locacao;
    private Categoria categoria;
    private double valorDeCompra;
    private String placa;
    private int ano;

    public Veiculo(Marca marca, 
                    Estado estado, 
                    Locacao locacao, 
                    Categoria categoria, 
                    double valorDeCompra, 
                    String placa,
                    int ano) 
    {
        this.placa = placa;
        this.marca = marca;
        this.estado = estado;
        this.locacao = locacao;
        this.categoria = categoria;
        this.valorDeCompra = valorDeCompra;
        this.ano = ano;
    }

    @Override
    public void devolver() {
        this.locacao = null; //zerando a locacao
        estado = Estado.DISPONIVEL;
    }

    @Override
    public int getAno() {
        return ano;
    }

    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public Estado getEstado() {
        return estado;
    }

    @Override
    public Locacao getLocacao() {
        return locacao;
    }

    @Override
    public Marca getMarca() {
        return marca;
    }

    @Override
    public String getPlaca() {
        return placa;
    }

    @Override
    public double getValorParaVenda() {
        double valorParaVenda = valorDeCompra - (Year.now().getValue() - ano) * 0.15 * valorDeCompra;
        return valorParaVenda > valorDeCompra * 0.10 ? valorParaVenda : valorDeCompra * 0.10;
    }

    @Override
        public void locar(int dias, Calendar data, Cliente cliente) {

            double valorTotal = dias * getValorDiariaLocacao();

            this.locacao = new Locacao(dias, valorTotal, data, cliente);
            this.estado  = Estado.LOCADO;
        }


    @Override
    public void vender() {
        estado = Estado.VENDIDO;
    }
    
}
