package ClassesDAO;

import modelo.Usuario;
import modelo.UsuarioADM;
import modelo.UsuarioCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public UsuarioDAO() {
        criarTabela();
    }

    private void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS usuario (
                cpf VARCHAR(14) PRIMARY KEY,
                nome VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL,
                senha VARCHAR(100) NOT NULL,
                tipo_usuario VARCHAR(20) NOT NULL
            )
        """;

        try (Connection c = ConexaoMySQL.getConexao();
             Statement st = c.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Usuario u) {
        String sql = """
            INSERT INTO usuario (cpf, nome, email, senha, tipo_usuario)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection c = ConexaoMySQL.getConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getCpf());
            ps.setString(2, u.getNome());
            ps.setString(3, u.getEmail());
            ps.setString(4, "******"); // senha ocultada por simplicidade
            ps.setString(5, u.getTipoUsuario().name());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection c = ConexaoMySQL.getConexao();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Usuario.TipoUsuario tipo =
                        Usuario.TipoUsuario.valueOf(rs.getString("tipo_usuario"));

                Usuario u = (tipo == Usuario.TipoUsuario.CLIENTE)
                        ? new UsuarioCliente(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        "senha")
                        : new UsuarioADM(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        "senha");

                usuarios.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}

