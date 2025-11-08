package controller;

import banco.ClienteDao;
import banco.VeiculoDao;
import java.util.List;
import java.util.Optional;
import model.Cliente;
import util.RegraNegocioException;
import util.Util;
import view.ClientePanel;

public class ClienteController {

    private final ClientePanel view;
    private final ClienteDao dao;
    private final VeiculoDao daoVeiculo;

    public ClienteController(ClientePanel view, ClienteDao dao, VeiculoDao daoVeiculo) {
        this.view = view;
        this.dao = dao;
        this.daoVeiculo = daoVeiculo;
        initController();
    }

    private void initController() {
        this.view.setController(this);
        this.view.initView();
        listarClientes();
    }

    public void listarClientes() {
        try {
            List<Cliente> clientes = dao.getAll();
            view.mostrarClientes(clientes);
        } catch (Exception ex) {
            view.apresentaErro("Erro ao listar clientes.");
            ex.printStackTrace();
        }
    }

    public void salvarCliente() {
        try {
            Cliente cliente = view.getClienteFormulario();
            validarCliente(cliente);

            String cpf = normalizarCpf(cliente.getCpf());

            if (cliente.getId() == 0) {
                
                Optional<Cliente> existente = dao.getByCpf(cpf);
                if (existente.isPresent()) {
                    throw new RegraNegocioException("CPF " + formatCpfForMsg(cpf) + " já foi utilizado.");
                }
                cliente.setCpf(cpf);
                dao.add(cliente);
                view.apresentaInfo("Cliente criado com sucesso.");
            } else {
                // atualização
                Optional<Cliente> outro = dao.getByCpf(cpf);
                if (outro.isPresent() && outro.get().getId() != cliente.getId()) {
                    throw new RegraNegocioException("CPF " + formatCpfForMsg(cpf) + " já está em uso por outro cliente.");
                }
                cliente.setCpf(cpf);
                dao.update(cliente);
                view.apresentaInfo("Cliente atualizado com sucesso.");
            }

            listarClientes();
            view.limparFormulario();

        } catch (RegraNegocioException e) {
            view.apresentaErro(e.getMessage());
        } catch (Exception e) {
            view.apresentaErro("Erro ao salvar cliente.");
            e.printStackTrace();
        }
    }

    public void excluirCliente() {
        try {
            Integer id = view.getClienteIdParaExcluir();
            if (id == null || id == 0) {
                view.apresentaInfo("Selecione um cliente para excluir.");
                return;
            }

            Cliente c = dao.getById(id);
            if (c == null) {
                view.apresentaErro("Cliente não encontrado.");
                return;
            }

            if (daoVeiculo.existsByCliente(c)) {
                throw new RegraNegocioException("Não é possível excluir cliente com locação ativa.");
            }

            dao.delete(c);
            view.apresentaInfo("Cliente excluído com sucesso.");
            listarClientes();
            view.limparFormulario();

        } catch (RegraNegocioException e) {
            view.apresentaErro(e.getMessage());
        } catch (Exception e) {
            view.apresentaErro("Erro ao excluir cliente.");
            e.printStackTrace();
        }
    }

    private void validarCliente(Cliente cliente) throws RegraNegocioException {
        if (cliente == null) throw new RegraNegocioException("Dados do cliente são obrigatórios.");

        String cpfDigits = normalizarCpf(cliente.getCpf());
        if (cpfDigits == null || cpfDigits.isBlank())
            throw new RegraNegocioException("CPF é obrigatório.");
        if (cpfDigits.length() != 11)
            throw new RegraNegocioException("CPF deve possuir 11 dígitos.");
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty())
            throw new RegraNegocioException("Nome é obrigatório.");
    }

    private static String normalizarCpf(String v) {
        return v == null ? null : v.replaceAll("\\D", "");
    }

    private static String formatCpfForMsg(String cpf) {
        return Util.formatarCPF(cpf);
    }
}
