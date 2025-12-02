import java.util.List;

public abstract class Usuario {
    protected final String nome;
    protected final String email;
    protected final String cpf;
    private final String senha;

    public Usuario(String nome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    public boolean autenticar(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
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
}

// ----------------------
// Classe UsuarioCliente
// ----------------------
class UsuarioCliente extends Usuario {

    public UsuarioCliente(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha);
    }

    public void visualizarLivros(java.util.List<Livro> livros) {
        System.out.println("\n📚 Livros disponíveis:");
        for (Livro l : livros) {
            System.out.println("- " + l.getTitulo() + " (ID: " + l.getID() + ")");
        }
    }
}

// ----------------------
// Classe UsuarioADM
// ----------------------
class UsuarioADM extends Usuario {

    public UsuarioADM(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha);
    }

    public void adicionarLivro(java.util.List<Livro> livros, Livro novoLivro) {
        boolean existe = livros.stream().anyMatch(l -> l.getID() == novoLivro.getID());

        if (existe) {
            System.out.println("⚠ Já existe um livro com esse ID!");
            return;
        }

        livros.add(novoLivro);
        System.out.println("📘 Livro adicionado: " + novoLivro.getTitulo());
    }

    public void removerLivro(java.util.List<Livro> livros, int idLivro) {
        boolean removido = livros.removeIf(l -> l.getID() == idLivro);

        if (removido) {
            System.out.println("📕 Livro removido (ID: " + idLivro + ")");
        } else {
            System.out.println("⚠ Nenhum livro encontrado com esse ID.");
        }
    }
}
