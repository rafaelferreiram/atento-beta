package br.com.farmacia.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.dao.FornecedoresDao;
import br.com.farmacia.dao.ProdutoDao;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {


	private Produtos produtos;
	private ArrayList<Produtos>itens;
	private ArrayList<Produtos>itensFiltrados;
	
	private ArrayList<Fornecedores>comboFornecedores;
	
	public Produtos getProdutos() {
		return produtos;
	}


	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
	
	
	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	



	public ArrayList<Produtos> getItens() {
		return itens;
	}
	
	
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	
	
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



@PostConstruct
public void prepararPesquisa(){
	
	
	try {
		ProdutoDao fdao = new ProdutoDao();
		itens = fdao.listar();
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
	
}

public void prepararNovo(){
	
	
	
	try {
		produtos = new Produtos();
		FornecedoresDao dao = new FornecedoresDao();
		comboFornecedores = dao.listar();
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}

public void novo(){
	
	try {
		ProdutoDao fdao = new ProdutoDao();
		fdao.create(produtos);
		
		
		itens = fdao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}



public void excluir(){
	try {
		ProdutoDao fdao = new ProdutoDao();
		fdao.delete(produtos);
		
		
		itens = fdao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}



public void editar(){
	try {
		ProdutoDao fdao = new ProdutoDao();
		fdao.update(produtos);
		
		
		itens = fdao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}


public void prepararEditar(){
	
	
	
	try {
		produtos = new Produtos();
		FornecedoresDao dao = new FornecedoresDao();
		comboFornecedores = dao.listar();
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}

	
}