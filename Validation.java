import java.util.ArrayList;
import java.util.regex.Pattern;

public class Validation {
	private ArrayList<BNF> bnf;
	private String sentenca;
	private ArrayList<String> derivacoes = new ArrayList<String>();

	//CONSTRUTOR RECEBE A LINGUAGEM INFORMADA E A SENTENCA A SER VALIDADA
	public Validation(ArrayList<BNF> bnf, String sentenca){
		this.setBnf(bnf);
		this.setSentenca(sentenca);
	}

	public ArrayList<BNF> getBnf() {
		return bnf;
	}

	public void setBnf(ArrayList<BNF> bnf) {
		this.bnf = bnf;
	}

	public String getSentenca() {
		return sentenca;
	}

	public void setSentenca(String sentenca) {
		this.sentenca = sentenca;
	}
	
	//DERIVA O PRIMEIRO IDENTIFICADOR ENCONTRADO MAIS A ESQUERDA 
	public String deriva(String id, int indice){
		if(id.indexOf("<") == -1)
			return id;
		
		String derivacaoMaisEsquerda = id;
		String s = id;
		String primeiraParte = id.split("\\<")[0]; 
		String ultimaParte;
		int i;
		
		
		derivacaoMaisEsquerda = derivacaoMaisEsquerda.replaceFirst(Pattern.quote(primeiraParte), "");
		derivacaoMaisEsquerda = derivacaoMaisEsquerda.split("\\>")[0];
		derivacaoMaisEsquerda += ">";
		
		ultimaParte = id.replaceFirst(Pattern.quote(primeiraParte + derivacaoMaisEsquerda), "");
		
		for(i = 1; i < this.getBnf().size(); i++){
			if(this.getBnf().get(i).getNomeIdentificador().equals(derivacaoMaisEsquerda)){
				s = primeiraParte + this.getBnf().get(i).getValores().get(indice) + ultimaParte;
			}
		}
		
		return s;
	}
	
	//RETORNA TRUE SE EXISTE MAIS TERMOS PARA A DERIVACAO EX: <NUMERO> ::= <DIGITO><NUMERO> | <DIGITO>
	public boolean existeNoIrmao(String id, int indice){
		if(id.indexOf("<") == -1)
			return false;
		
		String derivacaoMaisEsquerda;
		String primeiraParte = id.split("\\<")[0]; 
		int indicePai;
		
		
		derivacaoMaisEsquerda = id.replaceFirst(Pattern.quote(primeiraParte), "");
		derivacaoMaisEsquerda = derivacaoMaisEsquerda.split("\\>")[0];
		derivacaoMaisEsquerda += ">";
		
		for(indicePai = 1; indicePai < this.getBnf().size(); indicePai++){
			if(this.getBnf().get(indicePai).getNomeIdentificador().equals(derivacaoMaisEsquerda)){
				break;
			}
		}
		
		if(this.getBnf().get(indicePai).getValores().size() - indice < 2)
			return false;
			
		return true;
	}

	//FUNÇÃO RECURSIVA PARA A VALIDAÇÃO DA SENTENÇA USANDO AS DUAS FUNÇÕES ANTERIORES
	public int valida(String id, int indice){
		int totalDeIdentificadores = 0;
		
		for(int i = 0; i < id.length(); i++){
			if(id.charAt(i) == '<'){
				totalDeIdentificadores++;
			}
		}
		
		if(((id.indexOf("<")-1) + totalDeIdentificadores) >= getSentenca().length())
			return 0;
		
		String novaDerivacao = deriva(id, indice);
		
		if(novaDerivacao.equals(getSentenca())){
			//System.out.println(novaDerivacao);
			derivacoes.add(novaDerivacao);
			return 1;
		}
		
		if(novaDerivacao.indexOf("<") > 0 && getSentenca().indexOf(novaDerivacao.split("\\<")[0]) != 0){
			if(existeNoIrmao(id, indice)){
				try{
					return valida(id, indice+1);
				}catch(IndexOutOfBoundsException e){
					return 0;
				}
			}else{
				return 0;
			}
			
		}
 	
		if(novaDerivacao.indexOf("<") != -1){
			if(valida(novaDerivacao, 0) == 1){
					//System.out.println(novaDerivacao);
					derivacoes.add(novaDerivacao);
					return 1;
			}else{
				if(existeNoIrmao(id, indice) && valida(id, indice+1) == 1){
					return 1;
				}else{
					return 0;
				}
			}
		}else{
			if(existeNoIrmao(id, indice) && valida(id, indice+1) == 1){
				return 1;
			}else{
				return 0;
			}
		}
	}
	
	public void derivacao(int indice, int length){
		if(indice < derivacoes.size()){
			derivacao(indice+1, length);
			System.out.println(derivacoes.get(indice));
		}
	}
	
	public void mostraDerivacoes(){
		derivacao(0, derivacoes.size());
	}
	
	public void show(){
		if(valida(this.getBnf().get(0).getValores().get(0), 0) == 1){
			mostraDerivacoes();
			System.out.println("A gramática reconhece a sentença!\n");
		}else{
			System.out.println("A gramática NÃO reconhece a sentença!\n");
		}
	}
}
