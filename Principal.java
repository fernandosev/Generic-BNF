import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
Autor: Fernando Severino Almeida
Desenvolvido em: 26/04/2019
*/

public class Principal {
	private static Scanner sc;

	//LÊ A BNF INFORMADA PELO USUÁRIO
	public static ArrayList<BNF> leBNF(){
		int idOrValues = 0; //VERIFICA SE ESTA DO IDENTIFICADOR OU NOS VALORES DO IDENTIFICADOR
		int contador = 0; //VERIFICA EM QUAL POSICAO ESTÁ SENDO INSERIDO UM NOVO VALOR DA LISTA (bnf)
		ArrayList<BNF> bnf = new ArrayList<BNF>();
		
		String buffer, auxString = null;
		
		sc = new Scanner(System.in);
		
		System.out.print("<assign> ::= ");
		
		buffer = sc.nextLine();
		buffer = "<assign> ::= " + buffer;
		buffer = buffer.replace(" ", "");
		
		while(!buffer.equals("")){
			while(!buffer.equals("")){
				if(idOrValues == 0){
					buffer = buffer.replaceFirst(Pattern.quote("<"), "");
					auxString = buffer.split("\\>")[0];
					buffer = buffer.replaceFirst(Pattern.quote(auxString), "");
					buffer = buffer.replaceFirst(Pattern.quote(">"), "");
					buffer = buffer.replaceFirst(Pattern.quote("::="), "");
					
					idOrValues = 1;
					
					bnf.add(new BNF("<" + auxString + ">"));
				}else{
					if (buffer.indexOf("|") > 0){ 
						auxString = buffer.split("\\|")[0];
						buffer = buffer.replaceFirst(Pattern.quote(auxString), "");
						buffer = buffer.replaceFirst(Pattern.quote("|"), "");
						
						bnf.get(contador).add(auxString);
					}else{
						
						auxString = buffer;
						buffer = buffer.replaceFirst(Pattern.quote(auxString), "");
						
						bnf.get(contador).add(auxString);
						
						buffer = "";
					}
				}
			}
			idOrValues = 0;
			contador++;
			buffer = sc.nextLine();
			buffer = buffer.replace(" ", "");
		}
		
		/*
		for(int i = 0; i<bnf.size(); i++){
			bnf.get(i).show();
		}
		*/
				
		return bnf;
		
	}
	
	public static void main(String args[]){
		ArrayList<BNF> bnf;
		String sentenca;
		
		System.out.println("REGRAS\n");
		System.out.println("1. Utilize ::= como operador de atribuição de um identificador ex.: <digito> ::= 1 | 2");
		System.out.println("2. Caso um identificador possua mais de um valor, coloque-os todos na mesma linha e separe-os com | ex.: <numero> ::= <digito> | <digito><numero>");
		System.out.println("3. Pressione \"ENTER\" duas vezes ao terminar de informar a linguagem BNF.\n");
		System.out.println("Exemplo de gramatica para validar numeros inteiros\n");
		System.out.println("<assing> ::= <numero>\n<numero> ::= <digito> | <digito><numero>\n<digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9\n");
		bnf = leBNF();
		
		System.out.println("Informe a(s) sentença(s) a ser(em) validada(s). Pressione \"ENTER\" duas vezes para sair.\n");
		sentenca = sc.nextLine();
		
		while(!sentenca.equals("")){
			while(!sentenca.equals("")){
				Validacao validaSentenca = new Validacao(bnf, sentenca.replace(" ", ""));
				validaSentenca.show();
				sentenca = "";
			}
			
			sentenca = sc.nextLine();
		}
		
	}
}


//EXEMPLOS DE GRAMATICAS
/* 

GRAMATICA PARA VALIDAR EXPRESSOES
<assign> ::= <declaracao>
<declaracao> ::= <tipo> <var> = <expr>;
<tipo> int | float | double | boolean | char | String
<var> ::= <letras> | <letras><numero>
<letras> ::= <letra> | <letra><letras>
<letra> ::= A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z | a | b | c | d | e | f | g | h | i | j | k | l | m | n | o | p | q | r | s | t | u | v | w | x | y | z
<expr> ::= <expr> <operador> <expr> | (<expr>) | [<expr>] | {<expr>} | <numero> | true | false | '<letra>' | "<letras>" | <ponto-flutuante> | <var> | <var> <operador> <expr>
<numero> ::= <digito><numero> | <digito> 
<ponto-flutuante> ::= <numero> | <numero>.<numero>
<operador> ::= + | - | * | / | % | ^
<digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

GRAMATIVA PARA VALIDAR NUMEROS INTEIROS
<assign> ::= <numero>
<numero> ::= <digito><numero> | <digito>
<digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

GRAMATICA PARA VALIDAR PLACAS DE CARRO
<assign> ::= <placa>
<placa> ::= <letras><separador><numeros>
<letras> ::= <letra><letra><letra>
<letra> ::= A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z
<separador> ::= . | -
<numeros> ::= <numero><numero><numero><numero>
<numero> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

GRAMATICA PARA VALIDAR DECLARACAO DE TIPO - AS3
<assign> ::= <dec_tipo>
<dec_tipo> ::= <var>;
<var> ::= <tipo_primitivo> <id> | static <tipo_primitivo> <id>
<tipo_primitivo> int | float | boolean
<id> ::= <digito> | <digito>, <id>
<digito> ::= a | b | c | d | e

*/
