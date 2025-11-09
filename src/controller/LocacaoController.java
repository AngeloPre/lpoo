package controller;

import banco.ClienteDao;
import banco.LocacaoDao;
import banco.VeiculoDao;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import model.Cliente;
import model.Veiculo;
import model.enums.Estado;
import util.RegraNegocioException;
import view.LocacaoPanelView;

public class LocacaoController {

    private LocacaoPanelView view;
    private VeiculoDao veiculoDAO;
    private ClienteDao clienteDAO;
    private LocacaoDao locacaoDAO;

    public LocacaoController(LocacaoPanelView view,
                             VeiculoDao veiculoDAO,
                             ClienteDao clienteDAO,
                             LocacaoDao locacaoDAO) {
        this.view = view;
        this.veiculoDAO = veiculoDAO;
        this.clienteDAO = clienteDAO;
        this.locacaoDAO = locacaoDAO;
        initController();
    }

    private void initController() {
        this.view.setController(this);
        listarClientes();
        listarVeiculos();
    }
    
    public void locar() {
        try {
            String placaSelecionada = view.getPlacaSelecionada();
            String cpfSelecionado   = normalizarCpf(view.getCpfSelecionado());
            Integer dias            = view.getDiasSelecionados();
            Calendar dataLocacao    = view.getDataLocacaoSelecionada();

            if (placaSelecionada == null || placaSelecionada.isBlank())
                throw new RegraNegocioException("Selecione um veículo.");

            if (cpfSelecionado == null || cpfSelecionado.isBlank())
                throw new RegraNegocioException("Selecione uma pessoa.");

            if (dias == null || dias <= 0)
                throw new RegraNegocioException("Número de dias deve ser maior que zero.");

            if (dataLocacao == null)
                throw new RegraNegocioException("Informe a data da locação.");

            if (dataLocacao.before(getDataHoje()))
                throw new RegraNegocioException("Não é permitido data de locação no passado.");

            Veiculo v = veiculoDAO.getByLicencePlate(placaSelecionada);
//            System.out.println("CPF:" + cpfSelecionado);
            Optional<Cliente> opt = clienteDAO.getByCpf(cpfSelecionado);
            Cliente c = opt.orElseThrow(() -> new RegraNegocioException("CPF não encontrado."));

            if (veiculoDAO.existsByCliente(c))
                throw new RegraNegocioException("Este cliente já possui um veículo locado.");

            if (v.getEstado() == Estado.LOCADO)
                throw new RegraNegocioException("Veículo já está locado.");

            if (v.getEstado() == Estado.VENDIDO)
                throw new RegraNegocioException("Veículo já foi vendido.");

            v.locar(dias, dataLocacao, c);
            
            locacaoDAO.add(v.getLocacao());
            veiculoDAO.update(v);

            listarVeiculos();
            view.apresentaInfo("Locação registrada com sucesso.");
            view.limparFormulario();

        } catch (RegraNegocioException e) {
            view.apresentaErro(e.getMessage());
        } catch (Exception e) {
            view.apresentaErro("Erro ao registrar locação.");
            e.printStackTrace();
        }
    }

    public void listarClientes() {
        try {
            List<Cliente> clientes = clienteDAO.getAll();
            view.mostrarClientes(clientes);
        } catch (Exception e) {
            view.apresentaErro("Erro ao listar clientes.");
            e.printStackTrace();
        }
    }

    public void listarVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoDAO.getFreeVehicles();
            view.mostrarVeiculos(veiculos);
        } catch (Exception e) {
            view.apresentaErro("Erro ao listar veículos.");
            e.printStackTrace();
        }
    }
    
    private static String normalizarCpf(String v) {
        return v == null ? null : v.replaceAll("\\D", "");
    }
    
    public void buscarPorCpf() {
        try {
            String cpf = view.getFiltroCpf();
            if (cpf == null || cpf.isBlank()) {
                listarClientes();
                return;
            }
            Cliente c = clienteDAO.getByCpf(normalizarCpf(cpf))
                                  .orElseThrow(() -> new RegraNegocioException("CPF não encontrado."));
            view.mostrarClientes(Arrays.asList(c));
        } catch (RegraNegocioException e) {
            view.apresentaErro(e.getMessage());
        } catch (Exception e) {
            view.apresentaErro("Erro ao buscar por CPF.");
            e.printStackTrace();
        }
    }

    public void buscarPorNome() {
        try {
            String nome = view.getFiltroNome();
            if (nome == null || nome.isBlank()) {
                listarClientes();
                return;
            }
            view.mostrarClientes(clienteDAO.getByName(nome.trim()));
        } catch (Exception e) {
            view.apresentaErro("Erro ao buscar por nome.");
            e.printStackTrace();
        }
    }

    public void buscarPorSobrenome() {
        try {
            String sobrenome = view.getFiltroSobrenome();
            if (sobrenome == null || sobrenome.isBlank()) {
                listarClientes();
                return;
            }
            view.mostrarClientes(clienteDAO.getBySurname(sobrenome.trim()));
        } catch (Exception e) {
            view.apresentaErro("Erro ao buscar por sobrenome.");
            e.printStackTrace();
        }
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
