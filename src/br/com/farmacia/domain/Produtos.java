package br.com.farmacia.domain;

public class Produtos {
	private int codigo;
	private String descricao;
	private int quantidade;
	private double preco;
	private Fornecedores fornecedores = new Fornecedores();
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescicao() {
		return descricao;
	}
	public void setDescicao(String descicao) {
		this.descricao = descicao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Fornecedores getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	
}
