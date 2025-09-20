
import br.com.conexao.Conexao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author gabi
 */
public class LoginControllers extends HttpServlet {
    // private static final long serialVersionUID = 1L;

    // pagina 25
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String emailBuscado = "";
        String senhaBuscada = "";
        Connection con;
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String sql = "Select * from usuario where email = ? and senha = ?";

        try {
            con = Conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                emailBuscado = rs.getString("email");
                senhaBuscada = rs.getString("senha");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (emailBuscado.equals(email) || senhaBuscada.equals(senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            request.setAttribute("logado", "logado.jsp");
            request.getRequestDispatcher("logado.jsp").forward(request, response);
        } else {
            System.out.println(emailBuscado + " - " + email);
            System.out.println(senhaBuscada + " - " + senha);
            request.getRequestDispatcher("errodeusuario.jsp").forward(request, response);
        }
    }
}
