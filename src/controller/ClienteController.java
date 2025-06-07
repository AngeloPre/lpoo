package controller;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;
import table.ClienteTableModel;

public class ClienteController {

    private ClienteDAO dao = new ClienteDAO();
    private ClienteTableModel clienteTableModel;

    public ClienteController(ClienteTableModel clienteTableModel) {
        this.clienteTableModel = clienteTableModel;
    }

    public void listarClientes() {
        List<Cliente> clientes = dao.listarTodos();
        clienteTableModel.setClientes(clientes);
    }

    public void salvarCliente(Cliente cliente) {
        if (cliente.getId() == 0) {

            if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
                throw new IllegalArgumentException("CPF é obrigatório");
            }
            if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome é obrigatório");
            }
            dao.adicionar(cliente);
            listarClientes();
        } else {
            dao.atualizar(cliente);
            listarClientes();
        }
    }
    
    public void excluirCliente(int id) {
        dao.excluir(id);
        listarClientes();
    }

}
