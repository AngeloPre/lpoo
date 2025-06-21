/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controller;

import dao.ClienteDAO;
import model.Cliente;
import table.ClienteTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.RegraNegocioException;

/**
 *
 * @author mrblue
 */
public class ClienteControllerTest {

    public ClienteControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testClienteSemCpfNaoPermiteSalvar() {
        ClienteTableModel table = new ClienteTableModel();
        ClienteController controller = new ClienteController(table);
        Cliente cli = new Cliente("Marcos", "", "", "", "");
        RegraNegocioException excecao = assertThrows(RegraNegocioException.class,
                () -> controller.salvarCliente(cli));
        assertEquals("CPF é obrigatório", excecao.getMessage());
    }

    @Test
    public void testClienteSemNomeNaoPermiteSalvar() {
        ClienteTableModel table = new ClienteTableModel();
        ClienteController controller = new ClienteController(table);
        Cliente cli = new Cliente("  ", "", "", "67129346051", "");
        RegraNegocioException excecao = assertThrows(RegraNegocioException.class,
                () -> controller.salvarCliente(cli));
        assertEquals("Nome é obrigatório", excecao.getMessage());
    }

    @Test
    public void testClienteDeveSalvar() throws RegraNegocioException {
        ClienteTableModel table = new ClienteTableModel();
        System.out.println(table);
        ClienteController controller = new ClienteController(table);
        Cliente cli = new Cliente("Beto", "Bom de Bola do outro testes mesmo", "177087552", "67129346051", "Rua dos Jogadores,10");
        controller.salvarCliente(cli);
        int numeroRegistrosEsperado = 21; //Depende da quantidade de clientes criados na classe ClienteDAO
        int numeroRegistrosObtido = table.getRowCount();
        assertEquals(numeroRegistrosEsperado, numeroRegistrosObtido);
    }

    @Test
    public void testClienteAtualizadoNaoCriaNovoCliente() throws RegraNegocioException {
        ClienteTableModel table = new ClienteTableModel();
        System.out.println(table);
        ClienteController controller = new ClienteController(table);
        Cliente cli = new Cliente(1, "Beto", "Atualiza", "177087552", "67129346051", "Rua dos Jogadores,10");
        controller.salvarCliente(cli);
        int numeroRegistrosEsperado = 21; //Depende da quantidade de clientes criados na classe ClienteDAO
        int numeroRegistrosObtido = table.getRowCount();
        assertEquals(numeroRegistrosEsperado, numeroRegistrosObtido);

        String sobrenomeObtido = table.getCliente(0).getSobrenome();
        String sobreNomeEsperado = "Atualiza";
        assertEquals(sobreNomeEsperado, sobrenomeObtido);

    }
}
