package br.com.framework.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.entidade.Usuario;
import br.com.framework.service.ServiceException;
import br.com.framework.service.UsuarioService;

@Controller
public class UsuarioController {
	
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuario;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostConstruct /*Durante o construtor, o UsuarioService ainda não 
	será instanciado pelo framework*/
	public void init(){
		listaUsuario = usuarioService.buscarTodos();
	}
	

	public void excluir(Usuario usuario){
		System.out.println("ENTROU NO EXCLUIR");
		try {
			usuarioService.excluir(usuario);
			listaUsuario.remove(usuario); //EU ADICIONEI ISSO
			
			MensagemUtil.mensagemSucesso(MensagemUtil.EXLUSAO_SUCESSO);
		} catch (ServiceException e) {
			
			MensagemUtil.mensagemSucesso(MensagemUtil.EXLUSAO_ERRO);
			
			e.printStackTrace();
		}
	}
	
	public void editar(Usuario usuario){
		System.out.println("=============================================================================================");
		this.usuario = usuario;
	}
	
	
	public void salvar(){
		System.out.println("ENTORU NO SALVAR ===========================================");
		try {
			Usuario usuarioSalvo = usuarioService.salvar(usuario);
			if(usuario.getId()==null){
				listaUsuario.add(usuarioSalvo);
				MensagemUtil.mensagemSucesso(MensagemUtil.SALVO_SUCESSO);
			}
			
			usuario = new Usuario();
			
		} catch (ServiceException e) {
			MensagemUtil.mensagemSucesso(MensagemUtil.FALHA_SALVAR);
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
	
	
}
