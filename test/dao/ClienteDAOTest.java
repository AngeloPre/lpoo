/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mrblue
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
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
    public void testIncrementoId() {
        int idEsperado = 3;
        int idObtido = ClienteDAO.getId();
        
        assertEquals(idEsperado, idObtido);
    }
    
    @Test
    public void testListaInicialClientes() {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> listaObtida = dao.listarTodos();
        int numeroClientesEsperado = 2;
        int numeroClientesObtido = listaObtida.size();
        assertEquals(numeroClientesEsperado, numeroClientesObtido);
       
        List<Cliente> listaEsperada = new ArrayList<>();
        Cliente cliente1 = new Cliente (1, "João", "Silva", "12345678", "111.222.333-44", "Rua A, 123");
        Cliente cliente2 = new Cliente (2, "Maria", "Souza", "87654321", "555.666.777-88", "Av B, 456");
        listaEsperada.add(cliente1);
        listaEsperada.add(cliente2);
        assertEquals(listaEsperada, listaObtida);
    }
}
