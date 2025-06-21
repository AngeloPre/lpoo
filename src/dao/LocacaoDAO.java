package dao;

import java.util.ArrayList;
import java.util.List;
import model.Locacao;

public class LocacaoDAO {
    private static List<Locacao> locacoes = new ArrayList<>();

    public void salvar(Locacao l) { 
        locacoes.add(l); 
    }

    public List<Locacao> listar() { 
        return locacoes; 
    }

    // verificação na exclusão de cliente
    public boolean clienteTemLocacaoAtiva(int idCliente) {
        for (Locacao locacao : locacoes) {
            if (locacao.getCliente().getId() == idCliente) {
                // considera qualquer locação
                // implementar verificaçao se a locacao esta "ativa".
                return true;
            }
        }
        return false;
    }
}