/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dao;

import banco.BancoDadosCliente;
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
public class BancoDadosClienteTest {
    
    public BancoDadosClienteTest() {
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
        int idEsperado = 21;
        int idObtido = BancoDadosCliente.getId();
        
        assertEquals(idEsperado, idObtido);
    }
    
    @Test
    public void testListaInicialClientes() {
        BancoDadosCliente dao = new BancoDadosCliente();
        List<Cliente> listaObtida = dao.listarTodos();
        int numeroClientesEsperado = 20;
        int numeroClientesObtido = listaObtida.size();
        assertEquals(numeroClientesEsperado, numeroClientesObtido);
    }
}
