package model;

import java.util.Calendar;

public class Locacao {

    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    //retorna somente os dias
    public int getDias() {
        return this.dias;
    }

    @Override
    public String toString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = (data != null) ? sdf.format(data.getTime()) : "sem data";
        String clienteNome = (cliente != null) ? cliente.getNome() : "sem cliente";

        return String.format(
                "Locacao{id=%d, cliente='%s', dias=%d, data=%s, valor=R$ %.2f}",
                id,
                clienteNome,
                dias,
                dataFormatada,
                valor
        );
    }
}
