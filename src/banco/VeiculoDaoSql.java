package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.*;
import model.enums.*;

public class VeiculoDaoSql implements VeiculoDao {
    private final LocacaoDaoSql locacaoDao;

    public VeiculoDaoSql() {
        this.locacaoDao= new LocacaoDaoSql();
    }

    @Override
    public void add(Veiculo veiculo) throws Exception {
        String sql = "INSERT INTO veiculo (tipo_veiculo, marca, estado, categoria, " +
                     "valor_de_compra, placa, ano, modelo, locacao_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, getTipoVeiculo(veiculo));
            stmt.setString(2, veiculo.getMarca().name());
            stmt.setString(3, veiculo.getEstado().name());
            stmt.setString(4, veiculo.getCategoria().name());
            stmt.setDouble(5, veiculo.getValorDeCompra());
            stmt.setString(6, veiculo.getPlaca());
            stmt.setInt(7, veiculo.getAno());
            stmt.setString(8, getModelo(veiculo));
            
            if (veiculo.getLocacao() != null) {
                stmt.setInt(9, veiculo.getLocacao().getId());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir veículo, nenhuma linha afetada.");
            }
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    veiculo.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao inserir veículo, ID não obtido.");
                }
            }
        }
    }

    @Override
    public List<Veiculo> getAll() throws Exception {
        String sql = "SELECT * FROM veiculo ORDER BY placa";
        List<Veiculo> veiculos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                veiculos.add(mapearVeiculo(rs));
            }
        }
        
        return veiculos;
    }

    @Override
    public Veiculo getById(long id) throws Exception {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearVeiculo(rs);
                }
            }
        }
        
        return null;
    }

    @Override
    public void update(Veiculo veiculo) throws Exception {
        String sql = "UPDATE veiculo SET tipo_veiculo = ?, marca = ?, estado = ?, " +
                     "categoria = ?, valor_de_compra = ?, placa = ?, ano = ?, " +
                     "modelo = ?, locacao_id = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, getTipoVeiculo(veiculo));
            stmt.setString(2, veiculo.getMarca().name());
            stmt.setString(3, veiculo.getEstado().name());
            stmt.setString(4, veiculo.getCategoria().name());
            stmt.setDouble(5, veiculo.getValorDeCompra());
            stmt.setString(6, veiculo.getPlaca());
            stmt.setInt(7, veiculo.getAno());
            stmt.setString(8, getModelo(veiculo));
            
            if (veiculo.getLocacao() != null) {
                stmt.setInt(9, veiculo.getLocacao().getId());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }
            
            stmt.setInt(10, veiculo.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao atualizar veículo, nenhuma linha afetada.");
            }
        }
    }

    @Override
    public void delete(Veiculo veiculo) throws Exception {
        String sql = "DELETE FROM veiculo WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, veiculo.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao deletar veículo, nenhuma linha afetada.");
            }
        }
    }

    @Override
    public Veiculo getByLicencePlate(String placa) {
        String sql = "SELECT * FROM veiculo WHERE placa = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, placa);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearVeiculo(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public List<Veiculo> getFreeVehicles() {
        String sql = "SELECT * FROM veiculo WHERE locacao_id IS NULL ORDER BY placa";
        List<Veiculo> veiculos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                veiculos.add(mapearVeiculo(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return veiculos;
    }

    @Override
    public boolean existsByCliente(Cliente cliente) {
        String sql = "SELECT COUNT(*) FROM veiculo v " +
                     "INNER JOIN locacao l ON v.locacao_id = l.id " +
                     "WHERE l.cliente_id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, cliente.getId());
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    private String getTipoVeiculo(Veiculo veiculo) {
        if (veiculo instanceof Automovel) return "AUTOMOVEL";
        if (veiculo instanceof Van) return "VAN";
        if (veiculo instanceof Motocicleta) return "MOTOCICLETA";
        throw new IllegalArgumentException("Tipo de veículo desconhecido");
    }

    private String getModelo(Veiculo veiculo) {
        if (veiculo instanceof Automovel) {
            return ((Automovel) veiculo).getModelo().name();
        } else if (veiculo instanceof Van) {
            return ((Van) veiculo).getModelo().name();
        } else if (veiculo instanceof Motocicleta) {
            return ((Motocicleta) veiculo).getModelo().name();
        }
        throw new IllegalArgumentException("Tipo de veículo desconhecido");
    }

    private Veiculo mapearVeiculo(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String tipo = rs.getString("tipo_veiculo");
        Marca marca = Marca.fromDb(rs.getString("marca"));
        Estado estado = Estado.valueOf(rs.getString("estado"));
        Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
        double valorCompra = rs.getDouble("valor_de_compra");
        String placa = rs.getString("placa");
        int ano = rs.getInt("ano");
        String modeloStr = rs.getString("modelo");
        
        // Buscar locação se existir
        Integer locacaoId = rs.getObject("locacao_id", Integer.class);
        Locacao locacao = null;
        if (locacaoId != null) {
            locacao = locacaoDao.getById(locacaoId);
        }
        
        Veiculo veiculo;
        
        switch (tipo) {
            case "AUTOMOVEL":
                veiculo = new Automovel(marca, estado, locacao, categoria, 
                    valorCompra, placa, ano, ModeloAutomovel.valueOf(modeloStr));
                break;
            case "VAN":
                veiculo = new Van(marca, estado, locacao, categoria, 
                    valorCompra, placa, ano, ModeloVan.valueOf(modeloStr));
                break;
            case "MOTOCICLETA":
                veiculo = new Motocicleta(marca, estado, locacao, categoria, 
                    valorCompra, placa, ano, ModeloMotocicleta.valueOf(modeloStr));
                break;
            default:
                throw new SQLException("Tipo de veículo desconhecido: " + tipo);
        }
        
        veiculo.setId(id);
        return veiculo;
    }
}