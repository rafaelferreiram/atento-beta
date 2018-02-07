package br.com.farmacia.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Usuario {

	  private String usuario = "";
	    private String senha = "";
	    
	    public String logar(){
	        if(usuario.equals("rafael.ferreira") && senha.equals("admin")){
	            return "principal";
	        }
	        FacesContext ctx = FacesContext.getCurrentInstance();
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Senha inválida");
	        ctx.addMessage(null, msg);
	        return "";              
	    }
	    
	    
	    public String getUsuario() {
	        return usuario;
	    }
	    public void setUsuario(String usuario) {
	        this.usuario = usuario;
	    }
	    public String getSenha() {
	        return senha;
	    }
	    public void setSenha(String senha) {
	        this.senha = senha;
	    }
}
