package banco;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() throws SQLException, IOException {
        try (Connection conn = ConnectionFactory.getConnection(); Statement stmt = conn.createStatement()) {

            stmt.execute("SET search_path TO public;");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS cliente (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    sobrenome VARCHAR(100),
                    rg VARCHAR(20),
                    cpf VARCHAR(20) UNIQUE,
                    endereco VARCHAR(255)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS locacao (
                    id SERIAL PRIMARY KEY,
                    dias INT NOT NULL,
                    valor NUMERIC(10,2),
                    data_locacao DATE,
                    cliente_id INT REFERENCES cliente(id)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS veiculo (
                    id SERIAL PRIMARY KEY,
                    tipo_veiculo VARCHAR(50),
                    marca VARCHAR(50),
                    estado VARCHAR(50),
                    categoria VARCHAR(50),
                    valor_de_compra NUMERIC(10,2),
                    placa VARCHAR(20) UNIQUE,
                    ano INT,
                    modelo VARCHAR(100),
                    locacao_id INT REFERENCES locacao(id)
                );
            """);

            System.out.println("Tabelas verificadas/criadas com sucesso.");

            System.out.println("Iniciando carga inicial do banco.");

            stmt.executeUpdate("""
                INSERT INTO cliente (nome, sobrenome, rg, cpf, endereco) VALUES
                    ('João','Silva','12345678','81304555011','Rua A, 123'),
                    ('Maria','Souza','87654321','45884115008','Av B, 456'),
                    ('Pedro','Gomes','98765432','61326985078','Praça C, 789'),
                    ('Ana','Lima','23456789','54574804025','Travessa D, 101'),
                    ('Carlos','Santos','34567890','11390462099','Rua E, 202'),
                    ('Mariana','Costa','45678901','70686901061','Av F, 303'),
                    ('Fernando','Almeida','56789012','39189157001','Praça G, 404'),
                    ('Isabela','Pereira','67890123','15581632049','Rua H, 505'),
                    ('Lucas','Martins','78901234','92516976046','Av I, 606'),
                    ('Sofia','Rodrigues','89012345','04773868066','Travessa J, 707'),
                    ('Gabriel','Fernandes','90123456','52903645019','Rua K, 808'),
                    ('Larissa','Carvalho','01234567','58597354054','Av L, 909'),
                    ('Rafael','Oliveira','10987654','14299750080','Praça M, 111'),
                    ('Beatriz','Barbosa','21098765','36915283099','Rua N, 222'),
                    ('Vinicius','Castro','32109876','10276237005','Av O, 333'),
                    ('Manuela','Machado','43210987','18658517012','Travessa P, 444'),
                    ('Thiago','Dias','54321098','78816496070','Rua Q, 555'),
                    ('Paula','Ferreira','65432109','03122060035','Av R, 666'),
                    ('André','Pinto','76543210','34072520055','Praça S, 777'),
                    ('Juliana','Rocha','87654321','56851888033','Rua T, 888')
                    ON CONFLICT (cpf) DO NOTHING;
            """);
            stmt.executeUpdate("""
                INSERT INTO veiculo (tipo_veiculo, marca, estado, categoria, valor_de_compra, placa, ano, modelo, locacao_id) VALUES
                ('AUTOMOVEL','FIAT','NOVO','INTERMEDIARIO',1500,'RSC-6885',1995,'Palio',NULL),
                ('AUTOMOVEL','GM','NOVO','POPULAR',1500,'CAP-6775',2005,'Celta',NULL),
                ('AUTOMOVEL','FIAT','NOVO','POPULAR',48000,'BRA-2E19',2023,'Palio',NULL),
                ('MOTOCICLETA','HONDA','DISPONIVEL','POPULAR',15000,'JAH-8B17',2024,'CG_125',NULL),
                ('VAN','VW','DISPONIVEL','INTERMEDIARIO',150000,'VAN-4G58',2021,'Kombi',NULL)
                ON CONFLICT (placa) DO NOTHING;
            """);

            System.out.println("Carga inicial do banco realizada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
