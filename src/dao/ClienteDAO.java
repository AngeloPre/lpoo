/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Cliente;

public class ClienteDAO {

    private static List<Cliente> clientes = new ArrayList<>();
    private static int id = 1;

    static {
        clientes.add(new Cliente(nextId(), "João", "Silva", "12345678", "81304555011", "Rua A, 123"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "45884115008", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Pedro", "Gomes", "98765432", "61326985078", "Praça C, 789"));
        clientes.add(new Cliente(nextId(), "Ana", "Lima", "23456789", "54574804025", "Travessa D, 101"));
        clientes.add(new Cliente(nextId(), "Carlos", "Santos", "34567890", "11390462099", "Rua E, 202"));
        clientes.add(new Cliente(nextId(), "Mariana", "Costa", "45678901", "70686901061", "Av F, 303"));
        clientes.add(new Cliente(nextId(), "Fernando", "Almeida", "56789012", "39189157001", "Praça G, 404"));
        clientes.add(new Cliente(nextId(), "Isabela", "Pereira", "67890123", "15581632049", "Rua H, 505"));
        clientes.add(new Cliente(nextId(), "Lucas", "Martins", "78901234", "92516976046", "Av I, 606"));
        clientes.add(new Cliente(nextId(), "Sofia", "Rodrigues", "89012345", "04773868066", "Travessa J, 707"));
        clientes.add(new Cliente(nextId(), "Gabriel", "Fernandes", "90123456", "52903645019", "Rua K, 808"));
        clientes.add(new Cliente(nextId(), "Larissa", "Carvalho", "01234567", "58597354054", "Av L, 909"));
        clientes.add(new Cliente(nextId(), "Rafael", "Oliveira", "10987654", "14299750080", "Praça M, 111"));
        clientes.add(new Cliente(nextId(), "Beatriz", "Barbosa", "21098765", "36915283099", "Rua N, 222"));
        clientes.add(new Cliente(nextId(), "Vinicius", "Castro", "32109876", "10276237005", "Av O, 333"));
        clientes.add(new Cliente(nextId(), "Manuela", "Machado", "43210987", "18658517012", "Travessa P, 444"));
        clientes.add(new Cliente(nextId(), "Thiago", "Dias", "54321098", "78816496070", "Rua Q, 555"));
        clientes.add(new Cliente(nextId(), "Paula", "Ferreira", "65432109", "03122060035", "Av R, 666"));
        clientes.add(new Cliente(nextId(), "André", "Pinto", "76543210", "34072520055", "Praça S, 777"));
        clientes.add(new Cliente(nextId(), "Juliana", "Rocha", "87654321", "56851888033", "Rua T, 888"));
    }

    private static int nextId() {
        return id++;
    }

    public static int getId() {
        return id;
    }

    public int buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return clientes.indexOf(cliente);
            }
        }
        throw new RuntimeException("Nenhum resultado encontrado");
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        String cpfDaBusca = normalizarCpf(cpf);

        for (Cliente cliente : clientes) {
            String cpfCliente = normalizarCpf(cliente.getCpf());
            if (cpfCliente.equals(cpfDaBusca)) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    private String normalizarCpf(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                resultado.add(cliente);
            }
        }
        return resultado;
    }

    public List<Cliente> buscarPorSobrenome(String sobrenome) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getSobrenome().equalsIgnoreCase(sobrenome)) {
                resultado.add(cliente);
            }
        }
        return resultado;
    }

    public void adicionar(Cliente cliente) {
        cliente.setId(nextId());
        clientes.add(cliente);
//        debug
//        for (Cliente cliente1 : clientes) {
//            System.out.println(cliente1.getNome());
//        }
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public void atualizar(Cliente clienteAtualiza) {
        int indiceCliente = buscarPorId(clienteAtualiza.getId());
        clientes.set(indiceCliente, clienteAtualiza);
    }

    public void excluir(int id) {
        int indiceCliente = buscarPorId(id);
        clientes.remove(indiceCliente);
    }
    
    
}
