/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Veiculo;

/**
 *
 * @author mrAngarius
 */
public class VeiculoTableModel extends AbstractTableModel {

    private List<Veiculo> veiculos;

    private String[] colunas = {"marca", "estado", "categoria", "valor diario", "placa", "ano"};

    public VeiculoTableModel() {
        this.veiculos = new ArrayList<>();
    }

    public VeiculoTableModel(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
        fireTableDataChanged(); 

    }

    public Veiculo getVeiculos(int rowIndex) {
        return veiculos.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return veiculos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo veiculo = veiculos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return veiculo.getMarca();
            case 1:
                return veiculo.getEstado();
            case 2:
                return veiculo.getCategoria();
            case 3:
                return veiculo.getValorDiariaLocacao();
            case 4:
                return veiculo.getPlaca();
            case 5:
                return veiculo.getAno();
            default:
                return null;
        }

    }
}
