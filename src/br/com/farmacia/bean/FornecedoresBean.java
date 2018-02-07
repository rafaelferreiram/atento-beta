package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.farmacia.dao.FornecedoresDao;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;


@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	
	private Fornecedores fornecedores;
	private ArrayList<Fornecedores>itens;
	private ArrayList<Fornecedores>itensFiltrados;
	
public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}


	public ArrayList<Fornecedores> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	
	
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



@PostConstruct
public void prepararPesquisa(){
	
	
	try {
		FornecedoresDao dao = new FornecedoresDao();
		itens = dao.listar();
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
	
}

public void prepararNovo(){
	fornecedores = new Fornecedores();
}

public void novo(){
	
	try {
		FornecedoresDao dao = new FornecedoresDao();
		dao.create(fornecedores);
		
		itens = dao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}


public void excluir(){
	try {
		FornecedoresDao dao = new FornecedoresDao();
		dao.delete(fornecedores);
		
		itens = dao.listar();
				
		JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado!");
		e.printStackTrace();
	}
}





public void editar(){
	try {
		FornecedoresDao dao = new FornecedoresDao();
		dao.update(fornecedores);
		
		
		itens = dao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}

}
