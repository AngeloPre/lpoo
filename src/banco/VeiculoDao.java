package banco;

import java.util.List;
import model.Veiculo;

public interface VeiculoDao extends Dao<Veiculo> {
    public Veiculo getByLicencePlate(String placa);
    public List<Veiculo> getFreeVehicles();
    //public boolean existsByCliente
}
