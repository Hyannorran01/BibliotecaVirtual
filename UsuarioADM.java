package modelo;

import java.util.List;

public class UsuarioADM extends Usuario {
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
