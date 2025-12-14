package ClassesDAO;

import modelo.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserir(Livro livro) {
        String sql = "INSERT INTO livro (id, titulo, valor, disponivel) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexaoMySQL.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, livro.getID());
            ps.setString(2, livro.getTitulo());
            ps.setDouble(3, livro.getValor());
            ps.setBoolean(4, livro.isDisponivel());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (Connection con = ConexaoMySQL.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Livro l = new Livro(
                        rs.getString("titulo"),
                        rs.getInt("id"),
                        rs.getDouble("valor")
                );
                l.setDisponivel(rs.getBoolean("disponivel"));
                livros.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
}
