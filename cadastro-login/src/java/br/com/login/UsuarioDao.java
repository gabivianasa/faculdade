/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.login;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gabi
 */
public class UsuarioDao {

    private final Connection conexao;

    public UsuarioDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void adicionar(Usuario u) throws SQLException {
        String sql = "insert into usuario(nome, email, senha) values (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement(sql);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenha());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(ps, null, conexao);
        }
    }

    public void alterar(Usuario u) throws SQLException {
        String sql = "update usuario set nome = ?, email = ?, senha = ? where id = ?";
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement(sql);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenha());
            ps.setInt(4, u.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(ps, null, conexao);
        }
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        Statement statement = null;

        try {
            statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("select * from usuario");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                Usuario usuario = new Usuario(id, nome, email, senha);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(null, statement, conexao);
        }

        return usuarios;
    }

    public void apagar(int idUsuario) throws SQLException {
        PreparedStatement ps = null;

        if (idUsuario != 0) {
            try {
                ps = conexao.prepareStatement("delete from usuario where id = ?");
                ps.setInt(1, idUsuario);
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                fecharConexao(ps, null, conexao);
            }
        }

    }

    private void fecharConexao(PreparedStatement ps, Statement stmt, Connection con) {
        try {
            if (ps != null) {
                ps.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }

            System.out.println("Desconectou...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
