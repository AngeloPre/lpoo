package banco;

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

    @Override
    public void add(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente (nome, sobrenome, rg, cpf, endereco) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir cliente, nenhuma linha afetada.");
            }
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao inserir cliente, ID não obtido.");
                }
            }
        }
    }

    @Override
    public List<Cliente> getAll() throws Exception {
        String sql = "SELECT * FROM cliente ORDER BY nome, sobrenome";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }
        }
        
        return clientes;
    }

    @Override
    public Cliente getById(long id) throws Exception {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        }
        
        return null;
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        String sql = "UPDATE cliente SET nome = ?, sobrenome = ?, rg = ?, " +
                     "cpf = ?, endereco = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());
            stmt.setInt(6, cliente.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao atualizar cliente, nenhuma linha afetada.");
            }
        }
    }

    @Override
    public void delete(Cliente cliente) throws Exception {
        String sql = "DELETE FROM cliente WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, cliente.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao deletar cliente, nenhuma linha afetada.");
            }
        }
    }

    @Override
    public Optional<Cliente> getByCpf(String cpf) {
//        System.out.println("CPF NO DAO:" + cpf);
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearCliente(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return Optional.empty();
    }

    @Override
    public List<Cliente> getByName(String nome) {
        String sql = "SELECT * FROM cliente WHERE nome ILIKE ? ORDER BY nome, sobrenome";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    clientes.add(mapearCliente(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return clientes;
    }

    @Override
    public List<Cliente> getBySurname(String sobrenome) {
        String sql = "SELECT * FROM cliente WHERE sobrenome ILIKE ? ORDER BY sobrenome, nome";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + sobrenome + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    clientes.add(mapearCliente(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return clientes;
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("sobrenome"),
            rs.getString("rg"),
            rs.getString("cpf"),
            rs.getString("endereco")
        );
    }
}