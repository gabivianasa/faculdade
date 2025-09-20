/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.login;

import br.com.conexao.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author gabi
 */
public class ApagarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        try {
            UsuarioDao dao = new UsuarioDao(Conexao.getConexao());
            dao.apagar(idUsuario);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Usuário apagado</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class='alert alert-success' role='alert' style='margin: 1rem;'>");
            out.println("Usuário apagado com sucesso!");
            out.println("</div>");

            out.println("<a href='logado.jsp' class='btn btn-primary' style='margin:1rem;'>Voltar</a>");

            out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js\"></script>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();

            out.println("<div class='alert alert-danger' role='alert' style='margin: 1rem;'>");
            out.println("Erro ao apagar usuário.");
            out.println("</div>");

            out.println("<a href='logado.jsp' class='btn btn-primary' style='margin:1rem;'>Voltar</a>");
        } finally {
            out.close();
        }
    }

}
