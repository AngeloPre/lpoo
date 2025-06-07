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
        clientes.add(new Cliente(nextId(), "João", "Silva", "12345678", "111.222.333-44", "Rua A, 123"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
        clientes.add(new Cliente(nextId(), "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456"));
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
