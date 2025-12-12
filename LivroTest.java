import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LivroTest {
    @ParameterizedTest
    @CsvSource(
            {
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
}
