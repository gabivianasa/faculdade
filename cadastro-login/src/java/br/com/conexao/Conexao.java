/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabi
 */
public class Conexao {

    public static Connection getConexao() throws SQLException {
        try {
            // `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastroLogin?useSSL=false&allowPublicKeyRetrieval=true", "root", "root1");
            System.out.println("Conectou...");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }
}
