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
import model.Automovel;
import model.Motocicleta;
import model.Van;

public class DevolucaoTableModel extends AbstractTableModel {
    private List<Veiculo> veiculosLocados;
    private final String[] colunas = {"Nome Cliente", "Placa", "Marca", "Modelo", "Ano", "Data Locação", "Preço Diária", "Dias Locado", "Valor Locação"};
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
            case 0: //nome cliente
                return locacao.getCliente().getNome() + " " + locacao.getCliente().getSobrenome();
            case 1: //placa
                return veiculo.getPlaca();
            case 2: //marca
                return veiculo.getMarca();
            case 3: //modelo
                if (veiculo instanceof Automovel){
                    return ((Automovel) veiculo).getModelo();
                } else if (veiculo instanceof Motocicleta){
                    return ((Motocicleta) veiculo).getModelo();
                } else if (veiculo instanceof Van){
                    return ((Van) veiculo).getModelo();
                }
                return "N/A";
            case 4: //ano
                return veiculo.getAno();
            case 5: //data
                return sdf.format(locacao.getData().getTime());
            case 6: //preço da diaria
                return String.format("R$%,.2f", veiculo.getValorDiariaLocacao());
            case 7: //dias locado
                return locacao.getDias(); 
            case 8: //valor locacao
                return String.format("R$%,.2f", locacao.getValor());
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