/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Automovel;
import model.Cliente;
import model.Locacao;
import model.Veiculo;
import model.enums.Categoria;
import model.enums.Estado;
import model.enums.Marca;
import model.enums.ModeloAutomovel;

public class ClienteDAO {

    private static List<Cliente> clientes = new ArrayList<>();
    private static int id = 1;
    private static Cliente cli = new Cliente(100, "João", "Silva", "12345678", "111.222.333-44", "Rua A, 123");
    private static Locacao l = new Locacao(1, 5000.0, Calendar.getInstance(), cli);
    private static Veiculo v = new Automovel(Marca.Honda, Estado.LOCADO, l, Categoria.POPULAR, 1, "xxx-7777", 1, ModeloAutomovel.Gol);

    static {
        clientes.add(cli);
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

    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        throw new RuntimeException("Nenhum resultado encontrado");
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
        int indiceCliente = buscarPorId(clienteAtualiza.getId()).getId();
        clientes.set(indiceCliente, clienteAtualiza);
    }

    public void excluir(int id) {
        Cliente clienteParaExclusao = buscarPorId(id);
        if (clienteParaExclusao.getVeiculo() != null) {
            clientes.remove(clienteParaExclusao.getId());
        }
        throw new RuntimeException("Cliente possui veículo locado e não pode ser excluído");

    }
}
