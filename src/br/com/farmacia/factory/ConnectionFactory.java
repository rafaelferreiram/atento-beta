package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.farmacia.factory.ConnectionFactory;

public class ConnectionFactory {

	private static final String USUARIO ="root";
	private static final String SENHA ="root";
	private static final String URL ="jdbc:mysql://localhost:3306/farmacia";

	public static Connection connection() throws SQLException{

		//Referencia ao Driver mysql para conexao
		//Usado em versoes do Java mais antiga
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());

		Connection con = DriverManager.getConnection(URL,USUARIO,SENHA);
		return con;
	}

	public static void main(String[] args) throws SQLException {

		try{
			ConnectionFactory.connection();
			System.out.println("Conexao aberta com sucesso !");
		}catch (SQLException e) {
			System.out.println("Falha ao abrir conexao \n"+e);
		}

	}


}
