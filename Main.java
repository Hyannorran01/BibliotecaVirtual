import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioIFPB = new Biblioteca();

        Livro l1 = new Livro("Java use a Cabeça!", 1004, 4.0);
        Livro l2 = new Livro("Banco de Dados", 20252010, 8.0);
        LivroDigital l3 = new LivroDigital("CD Java", 20251982,12.0);

        biblioIFPB.adicionarLivro(l1);
        biblioIFPB.adicionarLivro(l2);
        biblioIFPB.adicionarLivro(l3);
    }
}

//        biblioIFPB.adicionarEmprestimo(1004,
//                LocalDate.now(), LocalDate.now().plusDays(5));
//
//        biblioIFPB.adicionarEmprestimo(20252010,
//                LocalDate.now(), LocalDate.now().plusDays(10));
//
//        biblioIFPB.adicionarEmprestimo(20251982,
//                LocalDate.now(), LocalDate.now().plusDays(3));

//        System.out.println("\n Tentando realizar emprestimo do Livro Java");
////Novo emprestimo!!!
//        biblioIFPB.adicionarEmprestimo(1004,
//                LocalDate.now(), LocalDate.now().plusDays(5));
//
//        System.out.println("\n Tentando realizar emprestimo do CD Java");
////Novo emprestimo!!!
//        biblioIFPB.adicionarEmprestimo(20251982,
//                LocalDate.now(), LocalDate.now().plusDays(19));
//    }
//}

//