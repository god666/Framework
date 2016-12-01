package br.com.framework;

public class Testador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//TesteAbstrato ta = new TesteAbstrato();
		TesteConcreto tc = new TesteConcreto();

		tc.setNome("joao");
		tc.setSexo("M");
		tc.abstrato();
		
	}

}
