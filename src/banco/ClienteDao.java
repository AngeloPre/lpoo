package banco;

import java.util.List;
import java.util.Optional;
import model.Cliente;

public interface ClienteDao extends Dao<Cliente>{
    Optional<Cliente> getByCpf(String cpf);
    List<Cliente> getByName(String nome);
    List<Cliente> getBySurname(String sobrenome);
}
