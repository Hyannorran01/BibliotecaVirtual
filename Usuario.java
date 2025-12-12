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

class UsuarioCliente extends Usuario {

    public UsuarioCliente(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha, TipoUsuario.CLIENTE);
    }

    public void realizarEmprestimo(Biblioteca biblioteca, int livroID) {
        biblioteca.adicionarEmprestimo(livroID, this);
    }
}
class UsuarioADM extends Usuario {

    public UsuarioADM(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha, TipoUsuario.ADMIN);
    }

    public void adicionarLivro(List<Livro> livros, Livro novoLivro) {
        boolean existe = livros.stream().anyMatch(l -> l.getID() == novoLivro.getID());

        if (existe) {
            System.out.println("Já existe um livro com esse ID.");
            return;
        }

        livros.add(novoLivro);
        System.out.println("O livro foi adicionado: " + novoLivro.getTitulo());
    }

    public void removerLivro(List<Livro> livros, int idLivro) {
        boolean removido = livros.removeIf(l -> l.getID() == idLivro);

        if (removido) {
            System.out.println("O livro foi removido (ID: " + idLivro + ")");
        } else {
            System.out.println("Nenhum livro possui esse ID.");
        }
    }
}