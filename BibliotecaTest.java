import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {

    Biblioteca biblioteca = new Biblioteca();

    UsuarioCliente u1 = new UsuarioCliente("Rogerio", "RogerioCardoso@tmail.com", "123.456.789.00", "senhatest000");
    UsuarioCliente u2 = new UsuarioCliente("Malcom", null, "111.222.333-44", "1234");
    UsuarioCliente u3 = new UsuarioCliente("Percival", "PercivalLeite@tmail.com", "222.333.888-11", "PercyJackson99");

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