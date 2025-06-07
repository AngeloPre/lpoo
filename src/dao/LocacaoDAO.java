package dao;

import java.util.ArrayList;
import java.util.List;

import model.Locacao;

public class LocacaoDAO {
    private static List<Locacao> locacoes = new ArrayList<>();
    public void salvar(Locacao l) { locacoes.add(l); }
    public List<Locacao> listar() { return locacoes; }

    
}
