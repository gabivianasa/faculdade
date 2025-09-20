/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.login;

import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author gabi
 */
public class CadastroLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CadastroLoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Connection con;
        try {
            con = Conexao.getConexao();

            Usuario u = new Usuario(nome, email, senha);
            
            UsuarioDao dao = new UsuarioDao(con);
            dao.adicionar(u);
            
            request.setAttribute("email", u.getEmail());
            request.getRequestDispatcher("cadastrado.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
