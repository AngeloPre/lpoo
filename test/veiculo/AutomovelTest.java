package veiculo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Automovel;
import model.enums.Categoria;
import model.enums.Estado;
import model.enums.Marca;
import model.enums.ModeloAutomovel;

public class AutomovelTest {
    @Test
    public void testCriarAutomovel(){
        Automovel a = new Automovel(Marca.Fiat, Estado.NOVO, null, Categoria.LUXO, 10000, "ARX-9999", 2010, ModeloAutomovel.Palio);
        Marca marcaEsperada = a.getMarca();
        Estado estadoEsperado = a.getEstado();
        Categoria categoriaEsperada = a.getCategoria();
        int valorEsperadoAno = a.getAno();
        ModeloAutomovel modeloEsperado = a.getModelo();

        assertEquals(marcaEsperada, Marca.Fiat);
        assertEquals(estadoEsperado, Estado.NOVO);
        assertEquals(categoriaEsperada, Categoria.LUXO);
        assertEquals(valorEsperadoAno, 2010);
        assertEquals(modeloEsperado, ModeloAutomovel.Palio);
    }

    @Test
    public void testGetValorDiariaLocacao() {
        Automovel a = new Automovel(Marca.Fiat, Estado.NOVO, null, Categoria.LUXO, 10000, "ARX-9999", 2010, ModeloAutomovel.Palio);
        double valorDiaraEsperado = a.getValorDiariaLocacao();
        assertEquals(valorDiaraEsperado, 450, 0);
    }
}
