package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Cliente;
import model.Locacao;

public class LocacaoDaoSql implements LocacaoDao{
    private final ClienteDaoSql clienteDAO;

    public LocacaoDaoSql() {
        this.clienteDAO = new ClienteDaoSql();
    }

    @Override
    public void add(Locacao locacao) throws Exception {
        String sql = "INSERT INTO locacao (dias, valor, data_locacao, cliente_id) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, locacao.getDias());
            stmt.setDouble(2, locacao.getValor());
            stmt.setDate(3, new java.sql.Date(locacao.getData().getTimeInMillis()));
            stmt.setInt(4, locacao.getCliente().getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir locação, nenhuma linha afetada.");
            }
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    locacao.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao inserir locação, ID não obtido.");
                }
            }
        }
    }

    @Override
    public List<Locacao> getAll() throws Exception {
        String sql = "SELECT * FROM locacao ORDER BY data_locacao DESC";
        List<Locacao> locacoes = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                locacoes.add(mapearLocacao(rs));
            }
        }
        
        return locacoes;
    }

    @Override
    public Locacao getById(long id) throws Exception {
        String sql = "SELECT * FROM locacao WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearLocacao(rs);
                }
            }
        }
        
        return null;
    }

    @Override
    public void update(Locacao locacao) throws Exception {
        String sql = "UPDATE locacao SET dias = ?, valor = ?, data_locacao = ?, " +
                     "cliente_id = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, locacao.getDias());
            stmt.setDouble(2, locacao.getValor());
            stmt.setDate(3, new java.sql.Date(locacao.getData().getTimeInMillis()));
            stmt.setInt(4, locacao.getCliente().getId());
            stmt.setInt(5, locacao.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao atualizar locação, nenhuma linha afetada.");
            }
        }
    }

    @Override
    public void delete(Locacao locacao) throws Exception {
        // Primeiro, remove a referência em veículos
        String sqlUpdateVeiculo = "UPDATE veiculo SET locacao_id = NULL WHERE locacao_id = ?";
        String sqlDelete = "DELETE FROM locacao WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdateVeiculo);
                 PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {
                
                // Remove referência em veículos
                stmtUpdate.setInt(1, locacao.getId());
                stmtUpdate.executeUpdate();
                
                // Deleta a locação
                stmtDelete.setInt(1, locacao.getId());
                int affectedRows = stmtDelete.executeUpdate();
                
                if (affectedRows == 0) {
                    conn.rollback();
                    throw new SQLException("Falha ao deletar locação, nenhuma linha afetada.");
                }
                
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public boolean existsByCliente(Cliente cliente) {
        String sql = "SELECT COUNT(*) FROM locacao WHERE cliente_id = ?";
        
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

    private Locacao mapearLocacao(ResultSet rs) throws Exception {
        Calendar data = Calendar.getInstance();
        data.setTime(rs.getDate("data_locacao"));
        
        Cliente cliente = clienteDAO.getById(rs.getInt("cliente_id"));
        
        Locacao locacao = new Locacao(
            rs.getInt("dias"),
            rs.getDouble("valor"),
            data,
            cliente
        );
        locacao.setId(rs.getInt("id"));
        
        return locacao;
    }
}