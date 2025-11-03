package controller;

import banco.ClienteDaoSql;
import banco.VeiculoDaoSql;
import java.util.List;
import model.Cliente;
import table.ClienteTableModel;
import util.RegraNegocioException;

public class ClienteController {

    //private BancoDadosCliente dao = new BancoDadosCliente();
    private final ClienteDaoSql dao = new ClienteDaoSql();
    private final VeiculoDaoSql daoVeiculo = new VeiculoDaoSql();
    private final ClienteTableModel clienteTableModel;

    public ClienteController(ClienteTableModel clienteTableModel) {
        this.clienteTableModel = clienteTableModel;
    }

    public void listarClientes() {
        try {        
            List<Cliente> clientes = dao.getAll();
            clienteTableModel.setClientes(clientes);
        }
        catch (Exception ex) {}

    }

    public void salvarCliente(Cliente cliente) throws RegraNegocioException {
        try{
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
            if (!dao.getByCpf(cpf).isEmpty()) {
                throw new RegraNegocioException("CPF " + cpf + " já foi utilizado.");
            }
            dao.add(cliente);
            listarClientes();
        } else {
            dao.update(cliente);
            listarClientes();
        }}
        catch (Exception ex) {}
    }
    
    public void excluirCliente(int id)throws RegraNegocioException {
        try {
            Cliente c = dao.getById(id);

            if (daoVeiculo.existsByCliente(c)) {
                throw new RegraNegocioException("Não é possível excluir cliente com locação ativa.");
            }
            dao.delete(c);
            listarClientes();
        } catch (Exception e) {}
    }
}
