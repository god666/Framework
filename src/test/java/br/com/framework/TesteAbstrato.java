package br.com.framework;

abstract class TesteAbstrato {
	
	String nome;
	String sexo;
	
	public abstract void abstrato();
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome(){
		return nome;
	}
	
	public void naoAbstrato(){
		System.out.println(nome+"===>"+sexo);
	}
	
	

}
