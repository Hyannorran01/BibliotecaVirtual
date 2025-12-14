package Testes;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.UsuarioCliente;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmprestimoTest {

    Livro l1 = new Livro("Java use a Cabeça!", 10345, 4.0);
    Livro l2 = new Livro("Banco de Dados", 234550, 8.0);
    Livro l3 = new Livro("CD Java", 932049, 12.0);

    UsuarioCliente uc1 = new UsuarioCliente("Ronaldo", "RonaldinhoCCG12@tmail.com", "400.411.511-12", "Senhatest2");
    UsuarioCliente uc2 = new UsuarioCliente("Mario", "MarioCesar0@tmail.com", "000.666.555-28", "987654321");
    UsuarioCliente uc3 = new UsuarioCliente("Pedro", "Pedro02052001@tmail.com", "999.000.444-11", "Facultativo28");

    @Test
    public void TestEmprestimoCriado() {

        Emprestimo emp = new Emprestimo(1, l2, uc1, LocalDate.now());
        String resposta = emp.validarDisponibilidade();

        assertEquals("O livro não está disponível para empréstimo pois já foi emprestado", resposta);
    }

    @Test
    public void TestDataDevolucaoAnteriorACriacao() {

        Emprestimo emp = new Emprestimo(2, l1, uc2, LocalDate.now().plusDays(2));
        String resposta = emp.validarDataDevolucao(LocalDate.now());

        assertEquals("Não é possível devolver um emprestimo antes da data de sua realização", resposta);
    }

    @Test
    public void TestMultaporAtrasoDevolucao() {

        l2.setDisponivel(true);
        LocalDate dataEmprestimo = LocalDate.now().minusDays(8);
        Emprestimo emp = new Emprestimo(3, l3, uc3, dataEmprestimo);
        emp.registrarDevolucao(LocalDate.now());
        double multa = emp.calcularMulta();

        assertEquals(7.5, multa, 0.0001);
    }
}
