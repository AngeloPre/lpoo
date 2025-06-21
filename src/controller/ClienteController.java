package controller;

import dao.ClienteDAO;
import dao.VeiculoDAO;
import java.util.List;
import model.Cliente;
import table.ClienteTableModel;
import util.RegraNegocioException;

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

    public void salvarCliente(Cliente cliente) throws RegraNegocioException {
        if (cliente.getId() == 0) {
            String cpf = cliente.getCpf();

            if (cpf == null || cpf.trim().isEmpty()) {
                throw new RegraNegocioException("CPF é obrigatório");
            }
            if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
                throw new RegraNegocioException("Nome é obrigatório");
            }
            if (cpf.length() < 11) {   
                throw new RegraNegocioException("CPF deve possuir 11 dígitos");
            }
            if (!dao.buscarPorCpf(cpf).isEmpty()) {
                throw new RegraNegocioException("CPF " + cpf + " já foi utilizado.");
            }
            dao.adicionar(cliente);
            listarClientes();
        } else {
            dao.atualizar(cliente);
            listarClientes();
        }
    }
    
    public void excluirCliente(int id)throws RegraNegocioException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        
        if (veiculoDAO.clientePossuiVeiculoLocado(id)) {
            throw new RegraNegocioException("Não é possível excluir cliente com locação ativa.");
        }
        dao.excluir(id);
        listarClientes();
    }
}
