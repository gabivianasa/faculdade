/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.login;

import br.com.conexao.Conexao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author gabi
 */
public class AlteraCadastroLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AlteraCadastroLoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        try {
            Usuario u = new Usuario(id, nome, email, senha);
            UsuarioDao dao = new UsuarioDao(Conexao.getConexao());
            dao.alterar(u);
            req.setAttribute("email", u.getEmail());
            req.getRequestDispatcher("logado.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
