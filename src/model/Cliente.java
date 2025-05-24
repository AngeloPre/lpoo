package model;

public class Cliente {
    
    private int id;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private String endereco;

    public Cliente(String nome, String sobreNome, String rg, String cpf, String endereco) {
        this.nome = nome;
        this.sobrenome = sobreNome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;        
    }

    public Cliente(int id, String nome, String sobreNome, String rg, String cpf, String endereco) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobreNome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }
    
    

}
