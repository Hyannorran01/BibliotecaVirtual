public class Livro {
    private final int ID;
    private String titulo;
    private double valor;
    private boolean disponivel;

    public Livro(String titulo, int ID, double valor) {
        this.titulo = titulo;
        this.ID = ID;
        this.valor = valor;
        this.disponivel = true;
    }

    public Livro(int ID, boolean disponivel) {
        this.ID = ID;
        this.disponivel = disponivel;
    }

    public boolean emprestar() {
        if (!disponivel) {
            return false;
        }
        disponivel = false;
        return true;
    }

    public void devolver() {
        disponivel = true;
    }

    public String analisarDisponibilidade() {
        return disponivel ? "Livro disponível" :
                "Livro cadastrado não está disponível no momento.";
    }

    // Getters

    public int getID() {
        return ID;
    }

    public String getTitulo() {
        return titulo;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}


class LivroDigital extends Livro{

    public LivroDigital(String titulo, int ID, double valor) {
        super(titulo, ID, valor);
    }

    @Override
    public boolean emprestar() {
        super.emprestar();
        return false;
    }
}


