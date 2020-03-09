package com.labegen.promebo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		Connection c = null;
		Class.forName("org.postgresql.Driver");
        c = DriverManager
           .getConnection("jdbc:postgresql://localhost:5432/promebo",
           "postgres", "123");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

		return c;
	}
}
