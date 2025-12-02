public class LivroDigital extends Livro{

    public LivroDigital(String titulo, int ID, double valor, boolean disponivel) {
        super(titulo, ID, valor, disponivel);
    }

    @Override
    public void emprestar(){
        super.emprestar();
    }
}