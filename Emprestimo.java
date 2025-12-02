import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    private final int numero;
    private final Livro livro;
    private final Usuario usuario;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    private static final int PRAZO_DIAS = 5;
    private static final double MULTA_DIA = 2.5;

    public Emprestimo(int numero, Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        this.numero = numero;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null;
        livro.setDisponivel(false);
    }

    public void registrarDevolucao() {
        registrarDevolucao(LocalDate.now());
    }

    public void registrarDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        livro.devolver();
    }

    public boolean atrasado() {
        LocalDate limite = dataEmprestimo.plusDays(PRAZO_DIAS);

        LocalDate referencia = (dataDevolucao != null)
                ? dataDevolucao
                : LocalDate.now();

        return referencia.isAfter(limite);
    }

    public double calcularMulta() {
        if (!atrasado()) return 0.0;

        LocalDate limite = dataEmprestimo.plusDays(PRAZO_DIAS);

        LocalDate referencia = (dataDevolucao != null)
                ? dataDevolucao
                : LocalDate.now();

        long diasAtraso = ChronoUnit.DAYS.between(limite, referencia);

        return diasAtraso * MULTA_DIA;
    }

    public String validarDisponibilidade() {
        return livro.isDisponivel()
                ? "Emprestimo Aprovado"
                : "O livro não está disponível para empréstimo pois já foi emprestado";
    }


    public int getnumero() {
        return numero;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

}