package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConnectionFactory;

public class FornecedoresDao {
	public void create(Fornecedores fornecedores) throws SQLException{
		String sql = "INSERT INTO fornecedores (descricao) VALUES(?)";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);


		stmt.setString(1, fornecedores.getDescricao());


		System.out.println(stmt);
		stmt.executeUpdate();

	}

	public Fornecedores read(Fornecedores fornecedores) throws SQLException{
		String sql = "SELECT codigo, descricao FROM fornecedores WHERE codigo =?";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setLong(1, fornecedores.getCodigo());
		ResultSet rs = stmt.executeQuery();

		if(rs.next()){
			fornecedores.setCodigo(rs.getInt("codigo"));
			fornecedores.setDescricao(rs.getString("descricao"));

		}
		return fornecedores;
	}
	
	public void update (Fornecedores fornecedores) throws SQLException{
		String sql = "UPDATE fornecedores SET descricao = ? WHERE codigo = ?";
		
		Connection con = ConnectionFactory.connection();
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, fornecedores.getDescricao());
		stmt.setInt(2, fornecedores.getCodigo());
		stmt.executeUpdate();
	}
	
	public void delete(Fornecedores fornecedores) throws SQLException{
		String sql = "DELETE FROM fornecedores WHERE codigo=?";
		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, fornecedores.getCodigo());

		stmt.executeUpdate();
	}




	public ArrayList<Fornecedores>buscarPorDescricao(Fornecedores fornecedores)throws SQLException{
		String sql = "SELECT codigo,descricao FROM fornecedores WHERE descricao LIKE ? ORDER BY descricao ASC";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%"+ fornecedores.getDescricao()+"%");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();

		while(rs.next()){
			Fornecedores f = new Fornecedores();
			f.setCodigo(rs.getInt("codigo"));
			f.setDescricao(rs.getString("descricao"));
			lista.add(f);
		}
		return lista;

	}
	public ArrayList<Fornecedores>buscarPorCodigo(Fornecedores fornecedores)throws SQLException{
		String sql = "SELECT codigo,descricao FROM fornecedores WHERE codigo LIKE ? ORDER BY codigo ASC";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "%"+ fornecedores.getCodigo()+"%");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();

		while(rs.next()){
			Fornecedores f = new Fornecedores();
			f.setCodigo(rs.getInt("codigo"));
			f.setDescricao(rs.getString("descricao"));
			lista.add(f);
		}
		return lista;
	}

	public ArrayList<Fornecedores> listar() throws SQLException{
		String sql = "SELECT codigo,descricao FROM fornecedores ORDER BY descricao ASC ";
		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();

		while(rs.next()){
			Fornecedores fornecedores = new Fornecedores();
			fornecedores.setCodigo(rs.getInt("codigo"));
			fornecedores.setDescricao(rs.getString("descricao"));
			lista.add(fornecedores);
		}
		return lista;
	}


}










