# GRAMATICA PARA VALIDAR EXPRESSOES
# <assign> ::= <declaracao>
# <declaracao> ::= <tipo> <var> = <expr>;
# <tipo> int | float | double | boolean | char | String
# <var> ::= <letras> | <letras><numero>
# <letras> ::= <letra> | <letra><letras>
# <letra> ::= A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z | a | b | c | d | e | f | g | h | i | j | k | l | m | n | o | p | q | r | s | t | u | v | w | x | y | z
# <expr> ::= <expr> <operador> <expr> | (<expr>) | [<expr>] | {<expr>} | <numero> | true | false | '<letra>' | "<letras>" | <ponto-flutuante> | <var> | <var> <operador> <expr>
# <numero> ::= <digito><numero> | <digito> 
# <ponto-flutuante> ::= <numero> | <numero>.<numero>
# <operador> ::= + | - | * | / | % | ^
# <digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

# GRAMATIVA PARA VALIDAR NUMEROS INTEIROS
# <assign> ::= <numero>
# <numero> ::= <digito><numero> | <digito>
# <digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

# GRAMATICA PARA VALIDAR PLACAS DE CARRO
# <assign> ::= <placa>
# <placa> ::= <letras><separador><numeros>
# <letras> ::= <letra><letra><letra>
# <letra> ::= A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z
# <separador> ::= . | -
# <numeros> ::= <numero><numero><numero><numero>
# <numero> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

# GRAMATICA PARA VALIDAR DECLARACAO DE TIPO - AS3
# <assign> ::= <dec_tipo>
# <dec_tipo> ::= <var>;
# <var> ::= <tipo_primitivo> <id> | static <tipo_primitivo> <id>
# <tipo_primitivo> int | float | boolean
# <id> ::= <digito> | <digito>, <id>
# <digito> ::= a | b | c | d | e