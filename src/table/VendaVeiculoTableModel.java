/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Automovel;
import model.Motocicleta;
import model.Van;
import model.Veiculo;
/**
 *
 * @author victor
 */
public class VendaVeiculoTableModel extends AbstractTableModel {
    private List<Veiculo> veiculos;
    private final String[] colunas = {"Placa", "Marca", "Modelo", "Ano", "Preço de Venda"};

    public VendaVeiculoTableModel() {
        this.veiculos = new ArrayList<>();
    }
    
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() { return veiculos.size(); }

    @Override
    public int getColumnCount() { return colunas.length; }

    @Override
    public String getColumnName(int column) { return colunas[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo veiculo = veiculos.get(rowIndex);
        Object modelo = null;
        if (veiculo instanceof Automovel) {
            modelo = ((Automovel) veiculo).getModelo();
        } else if (veiculo instanceof Motocicleta) {
            modelo = ((Motocicleta) veiculo).getModelo();
        } else if (veiculo instanceof Van) {
            modelo = ((Van) veiculo).getModelo();
        }

        switch (columnIndex) {
            case 0: return veiculo.getPlaca();
            case 1: return veiculo.getMarca();
            case 2: return modelo;
            case 3: return veiculo.getAno();
            case 4: return String.format("R$%,.2f", veiculo.getValorParaVenda());
            default: return null;
        }
    }
    
    public Veiculo getVeiculo(int rowIndex) {
        return veiculos.get(rowIndex);
    }
}
