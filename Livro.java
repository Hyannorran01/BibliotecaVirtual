import java.time.LocalDate;

public class Livro {
    private String titulo;
    private int ID;
    private double valor;
    private boolean disponivel;

    public Livro(int ID, boolean disponivel) {
        this.ID = ID;
        this.disponivel = disponivel;
    }

    public Livro(String titulo, int ID, double valor, boolean disponivel) {
        this.titulo = titulo;
        this.ID = ID;
        this.valor = valor;
        this.disponivel = disponivel;
    }

    public String analisandoDisponibilidadeLivro(){
        if(disponivel){
            return "Livro Disponível";
        }else return "Livro cadastrado não está disponível no momento.";
    }

    public LocalDate devolvido(){
        LocalDate dataDevolucao = LocalDate.now();
        disponivel=true;
        return dataDevolucao;
    }
    

    public void emprestar(){
        if(!disponivel){
            System.out.println("O livro não está disponível");
        }
        disponivel = false;
    }
//GETS e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDisponivel(){
        return disponivel;
    }
}


