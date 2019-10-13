/*
	Autor: Fernando Severino Almeida
	Desenvolvido em: 26/04/2019
*/

//ESSA CLASSE GRAVA A BNF INFORMADA PELO USUÁRIO EM UMA MATRIZ DE LISTAS

import java.util.ArrayList;

public class BNF {
	private String nomeIdentificador;
	private BNF proximo;
	private ArrayList<String> valores = new ArrayList<String>();
	
	public BNF(String nomeIdentificador){
		this.setNomeIdentificador(nomeIdentificador);
	}
	
	public BNF(String nomeIdentificador, String ...valores){
		this.setNomeIdentificador(nomeIdentificador);
		this.setValores(valores);
	}

	public String getNomeIdentificador() {
		return nomeIdentificador;
	}

	public void setNomeIdentificador(String nomeIdentificador) {
		this.nomeIdentificador = nomeIdentificador;
	}

	public BNF getProximo() {
		return proximo;
	}

	public void setProximo(BNF proximo) {
		this.proximo = proximo;
	}
	
	public void add(String valor){
		this.valores.add(valor);
	}
	
	public void setValores(String valores[]){
		for(int i = 0; i < valores.length; i++){
			this.add(valores[i]);
		}
	}
	
	public ArrayList<String> getValores(){
		return this.valores;
	}
	
	//MOSTRA A LINGUAGEM BNF DIGITADA
	public void show(){
		int tamLista = this.valores.size();
		
		System.out.print(getNomeIdentificador() + " ::= ");
		
		for(int i = 0; i < tamLista; i++){
			if(i < tamLista-1)
				System.out.print(this.valores.get(i)+ " | ");
			else
				System.out.print(this.valores.get(i));
		}
		
		System.out.println();
	}
}
