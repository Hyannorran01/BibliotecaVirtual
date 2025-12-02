import java.util.List;

public class Usuario {
    protected String nome;
    protected String email;
    protected String cpf;
    protected String senha;

    public Usuario(String nome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

class UsuarioCliente extends Usuario {

    public UsuarioCliente(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha);
    }

    public void visualizarLivros(List<Livro> livros) {
        System.out.println("Livros disponíveis:");
        livros.forEach(System.out::println);
    }
}

class UsuarioADM extends Usuario {

    public UsuarioADM(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha);
    }

    public void adicionarLivro(List<Livro> livros, Livro novoLivro) {
        livros.add(novoLivro);
        System.out.println("Livro adicionado: " + novoLivro.getTitulo());
    }

    public void removerLivro(List<Livro> livros, String titulo) {
        livros.removeIf(l -> l.getTitulo().equalsIgnoreCase(titulo));
        System.out.println("Livro removido: " + titulo);
    }
}
