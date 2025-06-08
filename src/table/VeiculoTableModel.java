/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Automovel;
import model.Motocicleta;
import model.Van;
import model.Veiculo;

/**
 *
 * @author mrAngarius
 */
public class VeiculoTableModel extends AbstractTableModel {

    private List<Veiculo> veiculos;

    private String[] colunas = {"Placa", "Marca","Modelo", "Ano", "Preço da diária"};

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
        Formatter f = new Formatter();

        switch (columnIndex) {
            case 0:
                return veiculo.getPlaca();
            case 1:
                return veiculo.getMarca();
            case 2:
                if (veiculo instanceof Automovel) {
                    return ((Automovel) veiculo).getModelo();
                } else if (veiculo instanceof Motocicleta) {
                    return ((Motocicleta) veiculo).getModelo();
                } else if (veiculo instanceof Van) {
                    return ((Van) veiculo).getModelo();
                }
            case 3:
                return veiculo.getAno();
            case 4:
                return f.format("R$%,.2f", veiculo.getValorDiariaLocacao());
            default:
                return null;
        }

    }
}
