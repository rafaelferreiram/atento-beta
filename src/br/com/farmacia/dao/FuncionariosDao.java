package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.farmacia.domain.Funcionario;
import br.com.farmacia.factory.ConnectionFactory;

public class FuncionariosDao {

	public void create(Funcionario funcionario) throws SQLException{
		String sql = "INSERT INTO funcionarios (nome, re) VALUES(?,?)";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);


		stmt.setString(1, funcionario.getNome());
		stmt.setInt(2, funcionario.getRe());


		System.out.println(stmt);
		stmt.executeUpdate();

	}

	public Funcionario read(Funcionario funcionario) throws SQLException{
		String sql = "SELECT nome, re FROM funcionarios WHERE re = ?";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, funcionario.getNome());
		stmt.setInt(2, funcionario.getRe());
		
		ResultSet rs = stmt.executeQuery();

		if(rs.next()){
			funcionario.setNome(rs.getString("nome"));
			funcionario.setRe(rs.getInt("re"));
		
		}
		return funcionario;
	}
	
	public void update (Funcionario funcionario) throws SQLException{
		String sql = "UPDATE funcionarios SET nome = ? WHERE re = ?";
		
		Connection con = ConnectionFactory.connection();
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, funcionario.getNome());
		stmt.setInt(2, funcionario.getRe());
		stmt.executeUpdate();
	}
	
	public void delete(Funcionario funcionario) throws SQLException{
		String sql = "DELETE FROM funcionarios WHERE re=?";
		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, funcionario.getRe());

		stmt.executeUpdate();
	}




	public ArrayList<Funcionario>buscarPorDescricao(Funcionario funcionario)throws SQLException{
		String sql = "SELECT nome,re FROM funcionarios WHERE re LIKE ? ORDER BY nome ASC";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%"+ funcionario.getNome()+"%");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Funcionario>lista = new ArrayList<Funcionario>();

		while(rs.next()){
			Funcionario f = new Funcionario();
			f.setNome(rs.getString("nome"));
			f.setRe(rs.getInt("re"));
			lista.add(f);
		}
		return lista;

	}
	public ArrayList<Funcionario>buscarPorCodigo(Funcionario funcionario)throws SQLException{
		String sql = "SELECT nome,re FROM funcionarios WHERE re LIKE ? ORDER BY re ASC";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%"+ funcionario.getRe()+"%");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Funcionario>lista = new ArrayList<Funcionario>();

		while(rs.next()){
			Funcionario f = new Funcionario();
			f.setNome(rs.getString("nome"));
			f.setRe(rs.getInt("re"));
			lista.add(f);
		}
		return lista;
	}

	public ArrayList<Funcionario> listar() throws SQLException{
		String sql = "SELECT nome,re FROM funcionarios ORDER BY nome ASC ";
		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		ArrayList<Funcionario>lista = new ArrayList<Funcionario>();

		while(rs.next()){
			Funcionario f = new Funcionario();
			f.setNome(rs.getString("nome"));
			f.setRe(rs.getInt("re"));
			lista.add(f);
		}
		return lista;
	}

}
