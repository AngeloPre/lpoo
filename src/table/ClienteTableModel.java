/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cliente;

/**
 *
 * @author mrblue
 */
public class ClienteTableModel extends AbstractTableModel {

    private List<Cliente> clientes;

    private String[] colunas = {"id", "nome", "sobrenome", "rg", "cpf", "endereço"};

    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
    }

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged(); 

    }

    public Cliente getCliente(int rowIndex) {
        return clientes.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return clientes.size();
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
        Cliente cliente = clientes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getSobrenome();
            case 3:
                return cliente.getRg();
            case 4:
                return cliente.getCpf();
            case 5:
                return cliente.getEndereco();
            default:
                return null;
        }

    }
}
