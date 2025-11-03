package banco;

import model.Automovel;
import model.Cliente;
import model.Locacao;

public interface LocacaoDao extends Dao<Locacao>{
    public boolean existsByCliente(Cliente c);
}
