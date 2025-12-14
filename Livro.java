package modelo;

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
        return disponivel ? "modelo.Livro Disponível" :
                "modelo.Livro cadastrado não está disponível no momento.";
    }


    public int getID() {
        return ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getValor() {
        return valor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}


