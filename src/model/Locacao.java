package model;

import java.util.Calendar;

public class Locacao {
    private int dias;
    private double valor;
    private Calendar data;
    private Cliente cliente;

    public Locacao(int dias, double valor, Calendar data, Cliente cliente) {
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
    }

    public double getValor() {
        return this.valor;
    }

    public Calendar getData() {
        return this.data;
    }

    public Cliente getCliente() {
        return this.cliente;
    }
}
