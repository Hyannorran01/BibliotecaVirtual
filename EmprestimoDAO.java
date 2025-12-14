package ClassesDAO;

import modelo.Emprestimo;

import java.sql.*;
import java.time.LocalDate;

public class EmprestimoDAO {

    public void inserir(Emprestimo emp) {
        String sql = """
            INSERT INTO emprestimo 
            (numero, livro_id, usuario_cpf, data_emprestimo, data_devolucao)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexaoMySQL.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, emp.getNumero());
            ps.setInt(2, emp.getLivro().getID());
            ps.setString(3, emp.getUsuario().getCpf());
            ps.setDate(4, Date.valueOf(emp.getDataEmprestimo()));

            if (emp.getDataDevolucao() != null) {
                ps.setDate(5, Date.valueOf(emp.getDataDevolucao()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

