package br.com.farmacia.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import br.com.farmacia.dao.ProdutoDao;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;


@ManagedBean
public class FileUploadView {

	private UploadedFile file;

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }


	 public void upload() throws IOException, SQLException {
        if(file != null) {
        	BufferedReader br = new BufferedReader(new InputStreamReader((file.getInputstream())));
        	leitura(br);
        	
        	
			
            FacesMessage message = new FacesMessage("Arquivo", file.getFileName() + " adicionado com sucesso !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public static void leitura(BufferedReader br ) throws IOException, SQLException {
    	
    	/* ArrayList<Produtos>itens;*/
    	
		try {

			String s = br.readLine();

			while (s != null) {

				String descricao;
				double preco;
				int qtd, id;
				StringTokenizer st = null;

				 st = new StringTokenizer(s, ";");

				descricao = st.nextToken();
				preco = Double.parseDouble(st.nextToken());
				qtd = Integer.parseInt(st.nextToken());
				id = Integer.parseInt(st.nextToken());

				Fornecedores f = new Fornecedores();
				f.setCodigo(id);

				Produtos p1 = new Produtos();
				p1.setDescicao(descricao);
				p1.setPreco(preco);
				p1.setQuantidade(qtd);
				p1.setFornecedores(f);

				ProdutoDao dao = new ProdutoDao();
				dao.create(p1);
				
				dao.listar();
				
				System.out.println(dao);
				s = br.readLine(); // pular a linha para voltar
			}

			br.close();


		} catch (Exception e) {
			System.out.println(e);
		}
	}
}