package controller;

import banco.ClienteDaoSql;
import banco.LocacaoDaoSql;
import banco.VeiculoDaoSql;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import model.Cliente;
import model.Veiculo;
import model.enums.Estado;
import table.ClienteTableModel;
import table.VeiculoTableModel;
import util.RegraNegocioException;

public class LocacaoController {

    private final VeiculoDaoSql veiculoDAO = new VeiculoDaoSql();
    private final ClienteDaoSql clienteDAO = new ClienteDaoSql();
    private final LocacaoDaoSql locacaoDAO = new LocacaoDaoSql();
    private final ClienteTableModel clienteTableModel;
    private final VeiculoTableModel veiculoTableModel;

    public LocacaoController(ClienteTableModel clienteTableModel, VeiculoTableModel veiculoTableModel) {
        this.clienteTableModel = clienteTableModel;
        this.veiculoTableModel = veiculoTableModel;
    }

    public void locar(String placaSelecionada, String cpfSelecionado,
            int dias, Calendar dataLocacao) throws RegraNegocioException {
        Veiculo v = veiculoDAO.getByLicencePlate(placaSelecionada);
        Cliente c = clienteDAO.getByCpf(cpfSelecionado).orElseThrow(() -> new RegraNegocioException("CPF não encontrado"));

        if (veiculoDAO.existsByCliente(c)) {
            throw new RegraNegocioException("Este cliente já possui um veículo locado");
        }

        if (c == null) {
            throw new RegraNegocioException("Cliente não pode ser nulo");
        }
        
        if (dias <= 0) {
            throw new RegraNegocioException("Número de dias deve ser maior que zero");
        }
        
        if (v.getEstado() == Estado.LOCADO) {
            throw new RegraNegocioException("Veículo já está locado");
        }
        
        if (v.getEstado() == Estado.VENDIDO) {
            throw new RegraNegocioException("Veículo já foi vendido");
        }
        
        if (dataLocacao.before(getDataHoje())) {
            throw new RegraNegocioException("Não é permitido data de locação no passado.");
        }

        v.locar(dias, dataLocacao, c);
        try {
            locacaoDAO.add(v.getLocacao());
        } catch (Exception e) {}
    }

    public void listarClientes() {
        try {
            List<Cliente> clientes = clienteDAO.getAll();
            clienteTableModel.setClientes(clientes);
        } catch (Exception e) {}
    }

    public void listarVeiculos() {
        List<Veiculo> veiculos = veiculoDAO.getFreeVehicles();
        veiculoTableModel.setVeiculos(veiculos);
    }

    public void buscarPorCpf(String cpf) throws RegraNegocioException {
        if (cpf == null || cpf.isBlank()) {
            listarClientes();
            return;
        }

        Cliente c = clienteDAO.getByCpf(cpf.trim())
                .orElseThrow(()
                        -> new RegraNegocioException("CPF não encontrado"));

        clienteTableModel.setClientes(Arrays.asList(c));
    }

    public void buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            listarClientes();
            return;
        }
        clienteTableModel.setClientes(clienteDAO.getByName(nome.trim()));
    }

    public void buscarPorSobrenome(String sobrenome) {
        if (sobrenome == null || sobrenome.isBlank()) {
            listarClientes();
            return;
        }
        clienteTableModel.setClientes(clienteDAO.getBySurname(sobrenome.trim()));
    }

    private Calendar getDataHoje() {
        Calendar hoje = Calendar.getInstance();
        hoje.set(Calendar.HOUR_OF_DAY, 0);
        hoje.set(Calendar.MINUTE, 0);
        hoje.set(Calendar.SECOND, 0);
        hoje.set(Calendar.MILLISECOND, 0);
        return hoje;

    }
}
