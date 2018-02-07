package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.farmacia.dao.FuncionariosDao;
import br.com.farmacia.domain.Funcionario;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionario;
	private ArrayList<Funcionario>itens;
	private ArrayList<Funcionario>itensFiltrados;
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public ArrayList<Funcionario> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Funcionario> itens) {
		this.itens = itens;
	}
	public ArrayList<Funcionario> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(ArrayList<Funcionario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	@PostConstruct
	public void prepararPesquisa(){
		
		
		try {
			FuncionariosDao dao = new FuncionariosDao();
			itens = dao.listar();
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
		
	}
	
	public void prepararNovo(){
		funcionario = new Funcionario();
	}

	public void novo(){
		
		try {
			FuncionariosDao dao = new FuncionariosDao();
			dao.create(funcionario);
			
			itens = dao.listar();
			
			
			JSFUtil.adicionarMensagemSucesso("Funcionario salvo com sucesso!");
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void excluir(){
		try {
			FuncionariosDao dao = new FuncionariosDao();
			dao.delete(funcionario);
			
			itens = dao.listar();
					
			JSFUtil.adicionarMensagemSucesso("Funcionario excluido com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editar(){
		try {
			FuncionariosDao dao = new FuncionariosDao();
			dao.update(funcionario);
			
			
			itens = dao.listar();
			
			
			JSFUtil.adicionarMensagemSucesso("Funcionario editado com sucesso!");
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	
}
