package controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import dao.ClienteDAO;
import dao.LocacaoDAO;
import dao.VeiculoDAO;
import model.Cliente;
import model.Veiculo;
import model.enums.Categoria;
import model.enums.Marca;
import table.ClienteTableModel;
import table.VeiculoTableModel;

public class LocacaoController {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private LocacaoDAO locacaoDAO;
    private ClienteTableModel clienteTableModel;
    private VeiculoTableModel veiculoTableModel;

    public LocacaoController(ClienteTableModel clienteTableModel, VeiculoTableModel veiculoTableModel) {
        this.clienteTableModel = clienteTableModel;
        this.veiculoTableModel = veiculoTableModel;
    }
    
    public void locar(String placaSelecionada, String cpfSelecionado,
               int dias, Calendar data){
        Veiculo v = veiculoDAO.buscarPorPlaca(placaSelecionada);
        Cliente c = clienteDAO.buscarPorCpf(cpfSelecionado).orElseThrow(() -> new RuntimeException("CPF não encontrado"));

        v.locar(dias, data, c);
        locacaoDAO.salvar(v.getLocacao());
    }

    public void listarClientes() {
        List<Cliente> clientes = clienteDAO.listarTodos();
        clienteTableModel.setClientes(clientes);
    }

    public void listarVeiculos() {
        List<Veiculo> veiculos = veiculoDAO.listarTodos();
        veiculoTableModel.setVeiculos(veiculos);
    }

    public void buscarPorCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            listarClientes();
            return;
        }

        Cliente c = clienteDAO.buscarPorCpf(cpf.trim())
                            .orElseThrow(() ->
                                new RuntimeException("CPF não encontrado"));

        clienteTableModel.setClientes(Arrays.asList(c));
    }

    public void buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            listarClientes();
            return;
        }
        clienteTableModel.setClientes(clienteDAO.buscarPorNome(nome.trim()));
    }

    public void buscarPorSobrenome(String sobrenome) {
        if (sobrenome == null || sobrenome.isBlank()) {
            listarClientes();
            return;
        }
        clienteTableModel.setClientes(clienteDAO.buscarPorSobrenome(sobrenome.trim()));
    }

    public String[] obterTiposVeiculo() {
        // se você não tiver um enum específico para tipo, devolve “na mão”
        return new String[]{"AUTOMOVEL", "MOTOCICLETA", "VAN"};
    }

        public String[] obterMarcas() {
        return Arrays.stream(Marca.values())
                     .map(marca -> marca.name())
                     .toArray(size -> new String[size]);
    }

    public String[] obterCategorias() {
        return Arrays.stream(Categoria.values())
                     .map(categoria -> categoria.name())
                     .toArray(size -> new String[size]);
    }
}

