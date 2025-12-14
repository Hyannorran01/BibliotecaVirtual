package modelo;

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

    public String validarDataDevolucao(LocalDate dataDevolucaoInformada) {
        if (dataDevolucaoInformada.isBefore(dataEmprestimo)) {
            return "Não é possível devolver um emprestimo antes da data de sua realização";
        }
        return "Data de devolução válida";
    }


    public void registrarDevolucao(LocalDate data) {
        this.dataDevolucao = data;
        livro.devolver();
    }

    public String registrarDevolucaoBiblioteca(LocalDate hoje) {
        this.dataDevolucao = LocalDate.now();
        livro.devolver();
        return "Devolução realizada com sucesso!";
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
                ? "modelo.Emprestimo Aprovado"
                : "O livro não está disponível para empréstimo pois já foi emprestado";
    }

    public int getNumero() {
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