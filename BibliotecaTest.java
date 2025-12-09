import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {

    Livro l1 = new Livro("Java use a Cabeça!", 10345, 4.0);
    Livro l2 = new Livro("Banco de Dados", 234550, 8.0);
    Livro l3 = new Livro("CD Java", 932049, 12.0);
    Livro l4 = new Livro("Aprenda JavaScript", 542932, 15.0);
    Livro l5 = new Livro("Iniciantes para Python", 542933, 18.0);

    UsuarioCliente u1 = new UsuarioCliente("Rogerio", "RogerioCardoso@gmail.com", "123.456.789.00", "senhatest000");
    UsuarioCliente u2 = new UsuarioCliente("Malcom", null, "111.222.333-44", "1234");
    UsuarioCliente u3 = new UsuarioCliente("Percival", "PercivalLeite@gmail.com", "222.333.888-11", "PercyJackson99");

    Biblioteca biblioteca = new Biblioteca();

//Emprestimo
    @Test
    public void TestEmprestimoCriado() {

        Emprestimo emp = new Emprestimo(1, l2, u1, LocalDate.now());
        String resposta = emp.validarDisponibilidade();

        assertEquals("O livro não está disponível para empréstimo pois já foi emprestado", resposta);
    }

    @Test
    public void TestDataDevolucaoAnteriorACriacao() {

        Emprestimo emp = new Emprestimo(2, l1, u2, LocalDate.now().plusDays(2));
        String resposta = emp.validarDataDevolucao(LocalDate.now());

        assertEquals("Não é possível devolver um emprestimo antes da data de sua realização", resposta);
    }

    @Test
    public void TestMultaporAtrasoDevolucao() {

        l2.setDisponivel(true);
        LocalDate dataEmprestimo = LocalDate.now().minusDays(8);
        Emprestimo emp = new Emprestimo(3, l3, u3, dataEmprestimo);
        emp.registrarDevolucao(LocalDate.now());
        double multa = emp.calcularMulta();

        assertEquals(7.5, multa, 0.0001);
    }

//Livro
    @ParameterizedTest
    @CsvSource(
            {"10345,true,Livro Disponível",
                    "234550,true,Livro Disponível",
                    "932049,false,Livro Disponível",
                    "542932,false,Livro cadastrado não está disponível no momento.",
                    "542933,true,Livro cadastrado não está disponível no momento."
            }
    )
    public void TestLivroDisponivel(int ID, boolean disponivel, String resultadoEsperado) {
        Livro livro = new Livro(ID, disponivel);
        assertEquals(resultadoEsperado, livro.analisarDisponibilidade());
    }

//Cliente
    @ParameterizedTest
    @CsvSource({
            "Joao, joao@email.com, 12345678900, 123, false",
            "'', joao@email.com, 12345678900, 123, true",
            "Maria, '', 98765432100, 123, true",
            "Carlos, carlos@email.com, '', 123, true",
            "Ana, ana@email.com, 11122233344, '', true",
            "null, teste@email.com, 55566677788, 123, true",
            "Jose, null, 99988877766, abc, true",
            "Paulo, paulo@email.com, null, 123, true",
            "Laura, laura@email.com, 12121212121, null, true"
    })
    void testDadosNaoPreenchidos(String nome, String email, String cpf, String senha, boolean saidaEsperada) {

        Usuario usuario = new UsuarioCliente(nome, email, cpf, senha);

        boolean resultado = usuario.possuiDadosNaoPreenchidos();

        assertEquals(saidaEsperada, resultado);
    }

//Biblioteca
    @Test
    public void TestUsuariosRegistrados() {

        biblioteca.adicionarUsuario(u1);
        biblioteca.adicionarUsuario(u2); // deve ser rejeitado
        biblioteca.adicionarUsuario(u3);

        var usuariosRegistrados = biblioteca.getUsuarios();

        assertEquals(2, usuariosRegistrados.size(), "A lista deve conter apenas 2 usuários válidos");
        assertTrue(usuariosRegistrados.contains(u1));
        assertFalse(usuariosRegistrados.contains(u2));
        assertTrue(usuariosRegistrados.contains(u3));
    }
}
