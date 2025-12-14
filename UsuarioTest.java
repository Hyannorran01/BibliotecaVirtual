package Testes;

import modelo.Usuario;
import modelo.UsuarioCliente;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioTest {
    @ParameterizedTest
    @CsvSource({
            "Luciano, lucionava@email.com, 12345678900, 000000, false",
            "'', joao@email.com, 12345678900, 123567, true",
            "Maria, '', 98765432100, Mariamariama, true",
            "Carlos, carlinhos13@email.com, '', carlitos9, true",
            "Clodoaldo, clod05@email.com, 11122233344, '', true",
    })
    void testDadosNaoPreenchidos(String nome, String email, String cpf, String senha, boolean saidaEsperada) {

        Usuario usuario = new UsuarioCliente(nome, email, cpf, senha);

        boolean resultado = usuario.possuiDadosNaoPreenchidos();

        assertEquals(saidaEsperada, resultado);
    }
}