# Sistema de Locação de Veículos

Projeto desenvolvido em Java com interface Swing para gerenciar o ciclo de vida dos veículos (automóveis, motos e vans) de uma empresa de locação.

---

## Estrutura do Projeto

O projeto segue a arquitetura **MVC (Model-View-Controller)** para organizar o código de forma clara e modular.

### Pacotes principais:

- **model/**  
  Contém as classes do modelo que representam os dados e regras de negócio do sistema, como `Cliente`, `Veiculo` (classe abstrata), `Automovel`, `Motocicleta`, `Van`, `Locacao` e os enums auxiliares (`Marca`, `Estado`, `Categoria`).

- **view/**  
  Responsável pela interface gráfica usando Java Swing. Contém as telas para cadastro e visualização de clientes, veículos, locações, devoluções e vendas.

- **controller/**  
  Contém as classes controladoras que fazem a ponte entre as views e o modelo, processando ações do usuário e atualizando os dados e interfaces.

- **main/**  
  Contém a classe principal `Main.java` que inicia a aplicação.

---

## Tecnologias utilizadas

- Java SE
- Java Swing para interface gráfica
- Programação Orientada a Objetos (POO) com herança, polimorfismo e encapsulamento
- Arquitetura MVC para organização do código

---

## Como executar

1. Abra o projeto na sua IDE Java preferida (Eclipse, NetBeans, IntelliJ).
2. Compile o projeto.
3. Execute a classe `main.Main` para iniciar a aplicação.

---

### Tabelas no BD

 - As tabelas podem ser criadas com esse comando no PostgreSQL rodando na porta 5432:

 ```sql
 -- Tabela de Clientes
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    rg VARCHAR(20) UNIQUE NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    endereco VARCHAR(255)
);

-- Tabela de Locações
CREATE TABLE locacao (
    id SERIAL PRIMARY KEY,
    dias INTEGER NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_locacao DATE NOT NULL,
    cliente_id INTEGER NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE RESTRICT
);

-- Tabela principal de Veículos (Single Table Inheritance)
CREATE TABLE veiculo (
    id SERIAL PRIMARY KEY,
    tipo_veiculo VARCHAR(20) NOT NULL, -- 'AUTOMOVEL', 'VAN', 'MOTOCICLETA'
    marca VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    valor_de_compra DECIMAL(12,2) NOT NULL,
    placa VARCHAR(10) UNIQUE NOT NULL,
    ano INTEGER NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    locacao_id INTEGER,
    FOREIGN KEY (locacao_id) REFERENCES locacao(id) ON DELETE SET NULL,
    CHECK (tipo_veiculo IN ('AUTOMOVEL', 'VAN', 'MOTOCICLETA'))
);
 ```

## Funcionalidades principais

- Cadastro, edição e exclusão de clientes.
- Cadastro de veículos (automóveis, motos, vans) com herança e interface para métodos específicos.
- Locação de veículos para clientes com controle de datas e valores.
- Devolução de veículos locados.
- Venda de veículos disponíveis.

---

## Contato

Para dúvidas ou sugestões, entre em contato.

---

