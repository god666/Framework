package br.com.framework.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtil {
	
	public final static String EXLUSAO_SUCESSO = "Excluído com Sucesso!";
	public final static String EXLUSAO_ERRO = "Exclusão Falhou!";
	public static final String SALVO_SUCESSO = "Salvo com Sucesso!";
	public static final String FALHA_SALVAR = "Falha ao Salvar";
	
	public static void mensagemSucesso(String msg){
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public static void mensagemErro(String msg){
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public static void mensagemAviso(String msg){
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN, 
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
}
