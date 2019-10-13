import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
author: Fernando Severino Almeida
Developed in: 26/04/2019
*/

public class Main {
	private static Scanner sc;

	//Reads the user-informed BNF
	public static ArrayList<BNF> leBNF(){
		int idOrValues = 0;
		int count = 0;
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
						
						bnf.get(count).add(auxString);
					}else{
						
						auxString = buffer;
						buffer = buffer.replaceFirst(Pattern.quote(auxString), "");
						
						bnf.get(count).add(auxString);
						
						buffer = "";
					}
				}
			}
			idOrValues = 0;
			count++;
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
		String sentence;
		
		System.out.println("RULES\n");
		System.out.println("1. Use ::= with assignment operator ex.: <digit> ::= 1 | 2");
		System.out.println("2. If an identifier has more than one value, put them all on the same line and separate them with | ex.: <number> ::= <digit> | <digit><number>");
		System.out.println("3. Press \"ENTER\" 2x if finalize the BNF rules information.\n");
		System.out.println("Example grammatical of integer numbers\n");
		System.out.println("<assing> ::= <number>\n<number> ::= <digit> | <digit><number>\n<digit> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9\n");
		bnf = leBNF();
		
		System.out.println("Inform the sentence or press enter twice to exit.\n");
		sentence = sc.nextLine();
		
		while(!sentence.equals("")){
			while(!sentence.equals("")){
				Validation validaSentenca = new Validation(bnf, sentence.replace(" ", ""));
				validaSentenca.show();
				sentence = "";
			}
			
			sentence = sc.nextLine();
		}
		
	}
}

