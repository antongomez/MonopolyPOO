#Entrega 3

Exemplo comandos tartos
trato Anton: cambiar Lugo por Santiago
    trato Anton: cambiar Lugo por 500
    trato Anton: cambiar 500 por Santiago
    trato Anton: cambiar Lugo e 500 por Santiago
    trato Anton: cambiar Lugo por Santiago e 500
    trato Anton: cambiar Lugo por Santiago e nonalquiler(Meanho,3)


__________________________
CASILLA

 - Atributo frecuentada: leva a conta das visitas dos Avatares (é un ArrayList que almacena)
 - Métodos frecuenciaVisita: serve para calcular as visitas dun avatar ou de todos os avatares
 - O método imprimirCasilla devolve o texto para imprimir. Falta por engadir, en cada subclase, os xogadores que están nesa casilla. En GRUPO quizais non fai falta. NON FAI FALTa
__________________________
XOGO

 - Os comandos están na interface Comandos, hai que ir descomentándoos a medida que se implementan.
 - As funcions quéroas por ORDE ALFABÉTICA no xogo
 - Non hai clase partida, esta substituina.
 - A maioría das comprobacións débense facer nas funcións
 - Hai un vector de xogadores e avatares
 - Decidin crear dende esta clase os avatares e despois asignarllos aos xogadores. Desta maneira é máis doado facer a comprobación de se o ID do avatar ou o nome do xogador coincide con outro.
 - Aqui hai unha funcion existeCasilla

 - Hai que ir altualizando o alquiler das propiedades cando os xogadores constrúan, compren,..., mediante o método calculoAlquiler. Aínda no o usei.
 - Tamén hai que ir actualizando as estatísticas. Aínda que se pode facer a posterior.
__________________________
XOGADOR

 - O cárcere farémolo como o tiñamos, hai un atributo estadoPreso aquí que levará a conta dos turnos no cárcere
 - Hai unha función modificarFortuna aquí

__________________________
CONSOLA

 - Hai un método para ler directamente, sen pasar ningunha descrición antes de ler o dato
 - Hai un método para ler toda a liña. Usase para os comandos. Pode valer para algunha outra cousa.
 - Para ler un int, hai que lelo coma se fose un String e pasalo a Integer coa funcion, por exemplo Integer.parseInt(String mensaxe)

__________________________
ESPECIAL

 - Ten un atributo valor onde se almacena o bote do Parking, o pago para sair do carcere ou o cobro por saida. Non ten funcionalidade para IrCarcere.

__________________________
EDIFICIO

 - O id calcúlase nas subclases.
 - O edificio está nun solar SEMPRE. O seu valor é o valor nese SOLAR.

__________________________
PROPIEDADE

 - Convertin o método imprimirPropiedade en imprimirCasilla.
 - O método actualizarValor é o que pide Penin chamado "valor"

__________________________
SOLAR

 - Implementei novas funcións que calculan o alquiler dunha casa, dúas,... , un hotel,... dese solar.
 - Hai funcions para calcular o número de casas, hoteis, etc.
 - Hai unha función que está por implementar que debe dicir o que falta por construir nese solar.
 - En imprimirCasilla, falta por incorporar o valor do alquiler dunha casa, dúas... Hai que usar as funcións do primeiro apartado desta clase

__________________________
DECISIÓNS  TOMADAS

- Banca
    * A fortuna deste xogador será 0.
    * Terá todas as propiedades en venta.

 - Os dados almacenaranse nun HashMap en Xogo, cuxas claves serán dado1 e dado2. Desta forma será máis doado calcular a suma dos dados, xa que se pasan xuntos.
    