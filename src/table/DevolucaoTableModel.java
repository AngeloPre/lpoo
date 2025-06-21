/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

/**
 *
 * @author mrVictor
 */

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Locacao;
import model.Veiculo;

public class DevolucaoTableModel extends AbstractTableModel {
    private List<Veiculo> veiculosLocados;
    private final String[] colunas = {"Nome Cliente", "Placa", "Marca", "Data Locação", "Preço Diária", "Dias Locado", "Valor Locação"};
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public DevolucaoTableModel() {
        this.veiculosLocados = new ArrayList<>();
    }

    @Override
    public int getRowCount() { return veiculosLocados.size(); }

    @Override
    public int getColumnCount() { return colunas.length; }
    
    @Override
    public String getColumnName(int column) { return colunas[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo veiculo = veiculosLocados.get(rowIndex);
        Locacao locacao = veiculo.getLocacao();
        if (locacao == null) return null;

        switch (columnIndex) {
            case 0: return locacao.getCliente().getNome() + " " + locacao.getCliente().getSobrenome();
            case 1: return veiculo.getPlaca();
            case 2: return veiculo.getMarca();
            case 3: return sdf.format(locacao.getData().getTime());
            case 4: return String.format("R$%,.2f", veiculo.getValorDiariaLocacao());
            case 5: return locacao.getDias(); // Precisa adicionar o getDias() em Locacao.java - adicionado
            case 6: return String.format("R$%,.2f", locacao.getValor());
            default: return null;
        }
    }
    
    public void setVeiculosLocados(List<Veiculo> veiculos) {
        this.veiculosLocados = veiculos;
        fireTableDataChanged(); // Notifica a JTable que os dados mudaram
    }
    
    public Veiculo getVeiculo(int rowIndex) {
        return veiculosLocados.get(rowIndex);
    }
}