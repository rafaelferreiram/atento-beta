package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConnectionFactory;

public class ProdutoDao {
	public void create(Produtos produtos) throws SQLException{
		String sql = "INSERT INTO produtos (descricao,preco,quantidade,fornecedores_codigo) VALUES(?,?,?,?)";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, produtos.getDescicao());
		stmt.setDouble(2, produtos.getPreco());
		stmt.setInt(3, produtos.getQuantidade());
		stmt.setInt(4, produtos.getFornecedores().getCodigo());

		System.out.println(stmt);
		stmt.executeUpdate();

	}
	
	public Produtos read(Produtos produtos) throws SQLException{
		String sql = "SELECT codigo, descricao ,preco , quantidade , fornecedores_codigo FROM produtos ";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, produtos.getCodigo());
		stmt.setString(2, produtos.getDescicao());
		stmt.setDouble(3, produtos.getPreco());
		stmt.setInt(4, produtos.getQuantidade());
		stmt.setInt(5, produtos.getFornecedores().getCodigo());
		
		ResultSet rs = stmt.executeQuery();

		if(rs.next()){
			Fornecedores fornecedores = new Fornecedores();
			produtos.setCodigo(rs.getInt("codigo"));
			produtos.setDescicao(rs.getString("descricao"));
			produtos.setPreco(rs.getDouble("preco"));
			produtos.setQuantidade(rs.getInt("quantidade"));
			produtos.setFornecedores(fornecedores);
		}
		return produtos;
	}


	public void update (Produtos produtos) throws SQLException{
		String sql = "UPDATE produtos SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? WHERE idprodutos = ?";

		Connection con = ConnectionFactory.connection();
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, produtos.getDescicao());
		stmt.setDouble(2, produtos.getPreco());
		stmt.setInt(3, produtos.getQuantidade());
		stmt.setInt(4, produtos.getFornecedores().getCodigo());
		stmt.setInt(5, produtos.getCodigo());

		stmt.executeUpdate();
	}

	public void delete(Produtos produtos) throws SQLException{
		String sql = "DELETE FROM produtos WHERE idprodutos=?";
		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, produtos.getCodigo());

		stmt.executeUpdate();
	}

	public ArrayList<Produtos> listar()throws SQLException{

		String sql = "SELECT p.idprodutos, p.descricao, p.quantidade, p.preco,  f.codigo, f.descricao"
				+ " FROM produtos p INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo";

		Connection con = ConnectionFactory.connection();

		PreparedStatement stmt = con.prepareStatement(sql);


		ResultSet rs = stmt.executeQuery();

		ArrayList<Produtos>lista = new ArrayList<Produtos>();

		while(rs.next()){
			Fornecedores fornecedores = new Fornecedores();
			fornecedores.setCodigo(rs.getInt("f.codigo"));
			fornecedores.setDescricao(rs.getString("f.descricao"));

			Produtos produtos = new Produtos();
			produtos.setCodigo(rs.getInt("p.idprodutos"));
			produtos.setDescicao(rs.getString("p.descricao"));
			produtos.setQuantidade(rs.getInt("p.quantidade"));
			produtos.setPreco(rs.getDouble("p.preco"));
			produtos.setFornecedores(fornecedores);

			lista.add(produtos);
		}

		return lista;
	}

}


