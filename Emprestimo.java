import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Emprestimo {
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private int numero;


    public Emprestimo(Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
            this.livro = livro;
            this.dataEmprestimo = dataEmprestimo;
            this.dataDevolucao = dataDevolucao;
    }

    public void exibirDetalhesEmprestimo(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd - MM - yyyy");
        System.out.println("Livro: "+ livro.getTitulo());
        System.out.println("Data do Emprestimo: " + dtf.format(dataEmprestimo));
        System.out.println("Data de deevolução: " +  dtf.format(dataDevolucao));
    }


    public String DevolucaoData(Emprestimo emprestimo) {
    if (emprestimo.dataDevolucao.isBefore(emprestimo.dataEmprestimo)) {
        return "Não é possível devolver um emprestimo antes da data de sua realização";
    }else
        return "Data Emprestimo OK!";
    }

    public String GeracaoDeMulta(Emprestimo emprestimo) {
        if (emprestimo.dataDevolucao.isAfter(dataEmprestimo.plusDays(5))) {
            System.out.println("Data ultrapassou o limite minimo, será adicionada multa pelo tempo de atraso");
            double NovoValor = tempoDeAtraso(emprestimo.livro, 3);
            System.out.println("O valor pós atraso ficou: " + NovoValor);
            return "O valor do Livro sofreu um ajuste devido a ultrapassar o prazo minimo";
        }
        else return "Não houve atraso na devolução do livro.";
    }


    public double tempoDeAtraso(Livro l , int diasDeAtraso){
       double multa = diasDeAtraso;
       double Novamulta = (multa*2.4) + l.getValor();
       return Novamulta;
    }

    public String ConfirmacaoDeEmprestimoValido(Livro livro){
        if (livro.isDisponivel() == false){
            return "O livro não está disponível para a venda";
        }
        else return "Emprestimo Aprovado";
    }

    public void devolverLivro(Emprestimo emprestimo){
        Livro l = emprestimo.livro;
        LocalDate datadoDevolvido = l.devolvido();
        setDataDevolucao(datadoDevolvido);
    }
    

//GET E SETTERS
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
