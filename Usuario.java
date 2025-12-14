package modelo;

import java.util.List;

public abstract class Usuario {

    public enum TipoUsuario {CLIENTE, ADMIN}

    protected final String nome;
    protected final String email;
    protected final String cpf;
    private final String senha;
    private final TipoUsuario tipoUsuario;

    public Usuario(String nome, String email, String cpf, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public boolean possuiDadosNaoPreenchidos() {
        return nome == null || nome.isEmpty()
                || email == null || email.isEmpty()
                || cpf == null || cpf.isEmpty()
                || senha == null || senha.isEmpty();
    }

    public void visualizarLivros(List<Livro> livros) {
        System.out.println("\nLivros disponíveis:");
        for (Livro l : livros) {
            System.out.println("- " + l.getTitulo() + " (ID: " + l.getID() + ")");
        }
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}

