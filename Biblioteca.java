import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<UsuarioCliente> clientes = new ArrayList<>();
    private int geradorIdEmprestimo = 1;

    public void adicionarLivro(Livro livro) {
        for (Livro l : livros) {
            if (l.getID() == livro.getID()) {
                System.out.println("Não é possível adicionar: já existe um livro com este ID.");
                return;
            }
        }
        livros.add(livro);
    }

    public void adicionarUsuario(UsuarioCliente usuario) {

        if (usuario == null) {
            System.out.println("Usuário inválido.");
            return;
        }

        if (usuario.possuiDadosNaoPreenchidos()) {
            System.out.println("Usuário possui dados incompletos e não foi adicionado.");
            return;
        }

        for (Usuario u : clientes) {
            if (u.getCpf().equals(usuario.getCpf())) {
                System.out.println("Não foi possível adicionar o usuário: CPF já cadastrado.");
                return;
            }
        }

        clientes.add(usuario);
        System.out.println("Usuário registrado: " + usuario.getNome());
    }

    public List<UsuarioCliente> getUsuarios() {
        return clientes;
    }

    public void listarLivros() {
        for (Livro l : livros) {
            System.out.println(l.getID() + " - " + l.getTitulo() +
                    " | Disponível: " + l.isDisponivel());
        }
    }

    public Livro buscarLivroPorId(int id) {
        for (Livro l : livros) {
            if (l.getID() == id) {
                return l;
            }
        }
        return null;
    }

    public String adicionarEmprestimo(int livroId, Usuario usuario) {

        Livro livroEncontrado = buscarLivroPorId(livroId);

        if (livroEncontrado == null) {
            return "Livro não encontrado!";
        }

        if (!livroEncontrado.isDisponivel()) {
            return "Livro está indisponível!";
        }

        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getID() == livroId && e.getDataDevolucao() == null) {
                System.out.println("Não é possível emprestar: este livro já está em um empréstimo ativo.");
                return "Verficação sem problemas";
            }
        }

        livroEncontrado.emprestar();

        Emprestimo novoEmprestimo = new Emprestimo(
                geradorIdEmprestimo++,
                livroEncontrado,
                usuario,
                LocalDate.now()
        );

        emprestimos.add(novoEmprestimo);

        return "Empréstimo realizado com sucesso!";
    }

    public String devolverLivro(int idEmprestimo) {

        for (Emprestimo emp : emprestimos) {
            if (emp.getNumero() == idEmprestimo) {
                LocalDate hoje = LocalDate.now();
                String resultado = emp.registrarDevolucaoBiblioteca(hoje);
                return "Devolução foi realizada na data: " + resultado;
            }
        }
        return "Empréstimo não encontrado!";
    }
}