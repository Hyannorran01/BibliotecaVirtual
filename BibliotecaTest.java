import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BibliotecaTest {

    Livro l1 = new Livro("Java use a Cabeça!", 1004, 4.0);
    Livro l2 = new Livro("Banco de Dados", 20252010, 8.0);
    LivroDigital l3 = new LivroDigital("CD Java", 20251982, 12.0);


    @Test
    public void TestEmprestimoCriado() {

        Emprestimo emp = new Emprestimo(1, l2, null, LocalDate.now());

        String resposta = emp.validarDisponibilidade();

        assertEquals("O livro não está disponível para empréstimo pois já foi emprestado", resposta);
    }

//    @Test
//    public void TestDataDevolucaoAnteriorACriacao() {
//        Emprestimo emp = new Emprestimo(l1, LocalDate.now().plusDays(2), LocalDate.now());
//        String respostaDoTeste = emp.DevolucaoData(emp);
//        assertEquals("Não é possível devolver um emprestimo antes da data de sua realização", respostaDoTeste);
//    }
//
//    @Test
//    public void TestMultaporAtrasoDevolucao() {
//        Emprestimo emp = new Emprestimo(l3, LocalDate.now(), LocalDate.now().plusDays(8));
//        String respostaDoTeste = emp.GeracaoDeMulta(emp);
//        assertEquals("O valor do Livro sofreu um ajuste devido a ultrapassar o prazo minimo", respostaDoTeste);
//    }
//
//    @ParameterizedTest
//    @CsvSource(
//            {"10345,true,Livro Disponível",
//                    "234550,true,Livro Disponível",
//                    "932049,false,Livro Disponível",
//                    "542932,false,Livro cadastrado não está disponível no momento.",
//                    "542932,true,Livro cadastrado não está disponível no momento."
//            }
//    )
//    public void TestLivroDisponivel(int ID, boolean disponivel, String resultadoEsperado) {
//        Livro livro = new Livro(ID, disponivel);
//        assertEquals(resultadoEsperado, livro.analisandoDisponibilidadeLivro());
//    }
//
}
