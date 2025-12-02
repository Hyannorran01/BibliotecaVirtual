import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private int geradorIdEmprestimo = 1;

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void listarLivros() {
        for (Livro l : livros) {
            System.out.println(l.getID() + " - " + l.getTitulo() +
                    " | Disponível: " + l.isDisponivel());
        }
    }

    public void adicionarEmprestimo(int livroId, Usuario usuario) {

        Livro livroEncontrado = buscarLivroPorId(livroId);

        if (livroEncontrado == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        if (!livroEncontrado.isDisponivel()) {
            System.out.println("Livro está indisponível!");
            return;
        }

        livroEncontrado.emprestar();

        Emprestimo novoEmprestimo = new Emprestimo(
                geradorIdEmprestimo++,
                livroEncontrado,
                usuario,
                LocalDate.now()
        );

        emprestimos.add(novoEmprestimo);

        System.out.println("Empréstimo realizado com sucesso!");
    }

    public Livro buscarLivroPorId(int id) {
        for (Livro l : livros) {
            if (l.getID() == id) {
                return l;
            }
        }
        return null;
    }

    public void devolverLivro(int idEmprestimo) {
        for (Emprestimo emp : emprestimos) {
            if (emp.getnumero() == idEmprestimo) {
                emp.registrarDevolucao();
                System.out.println("Devolução realizada!");
                return;
            }
        }
        System.out.println("Empréstimo não encontrado!");
    }
}

