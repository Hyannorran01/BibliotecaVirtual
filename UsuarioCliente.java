package modelo;

public class UsuarioCliente extends Usuario{
    public UsuarioCliente(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha, Usuario.TipoUsuario.CLIENTE);
    }

    public void realizarEmprestimo(Biblioteca biblioteca, int livroID) {
        biblioteca.adicionarEmprestimo(livroID, this);
    }
}
