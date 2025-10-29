package banco;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Cliente;

public class ClienteDaoSql implements ClienteDao {
    final String addSql = "INSERT INTO cliente (nome, sobrenome, rg, cpf, endereco) VALUES (?, ?, ?, ?, ?)";
    final String listAllSql = "SELECT * FROM cliente";
    final String getByIdSql = "SELECT * FROM cliente WHERE id = ?";
    final String updateSql = "UPDATE cliente SET nome = ?, sobrenome = ?, rg = ?, cpf = ?, endereco = ? WHERE id = ?";
    final String deleteSql = "DELETE FROM cliente WHERE id = ?";
    final String getByCpfSql = "SELECT * FROM cliente WHERE cpf = ?";
    final String getByName = "SELECT * FROM cliente WHERE nome LIKE ?";
    final String getBySurname = "SELECT * FROM cliente WHERE sobrenome LIKE ?";
    
    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("sobrenome"),
            rs.getString("rg"),
            rs.getString("cpf"),
            rs.getString("endereco")
        );
    }

    @Override
    public void add(Cliente cliente) throws Exception {
        String sql = addSql;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getSobrenome());
            pstmt.setString(3, cliente.getRg());
            pstmt.setString(4, cliente.getCpf());
            pstmt.setString(5, cliente.getEndereco());
            
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException | IOException e) {
            throw new Exception("Error adding client: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Cliente> getAll() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        String sql = listAllSql;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
        } catch (SQLException | IOException e) {
            throw new Exception("Error getting all clients: " + e.getMessage(), e);
        }
        return clientes;
    }

    @Override
    public Cliente getById(long id) throws Exception {
        String sql = getByIdSql;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCliente(rs);
                }
            }
        } catch (SQLException | IOException e) {
            throw new Exception("Error getting client by ID: " + e.getMessage(), e);
        }
        return null; 
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        String sql = updateSql;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getSobrenome());
            pstmt.setString(3, cliente.getRg());
            pstmt.setString(4, cliente.getCpf());
            pstmt.setString(5, cliente.getEndereco());
            pstmt.setInt(6, cliente.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new Exception("Error updating client: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Cliente cliente) throws Exception {
        String sql = deleteSql;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, cliente.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new Exception("Error deleting client: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Cliente> getByCpf(String cpf) {
        String sql = getByCpfSql;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cpf);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCliente(rs));
                }
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Cliente> getByName(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        // Using LIKE for partial matching (e.g., "jo" finds "João" and "Joana")
        String sql = getByName;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nome + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    clientes.add(mapResultSetToCliente(rs));
                }
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public List<Cliente> getBySurname(String sobrenome) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = getBySurname;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + sobrenome + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    clientes.add(mapResultSetToCliente(rs));
                }
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
        return clientes;
    }
}