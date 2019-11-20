package estrutura;

public class Constantes {

    public final static int NSOLARES = 22;

    //Constantes valores grupos
    public final static float GRUPO1 = 200;
    public final static float GRUPO2 = 330;
    public final static float GRUPO3 = 420;
    public final static float GRUPO4 = 540;
    public final static float GRUPO5 = 690;
    public final static float GRUPO6 = 900;
    public final static float GRUPO7 = 1200;
    public final static float GRUPO8 = 1500;

    //Constantes para o numero maximo de hoteis, piscinas e pistas de deporte
    public final static int G1_MAX_CONS = 2;
    public final static int G2_MAX_CONS = 3;
    public final static int G3_MAX_CONS = 3;
    public final static int G4_MAX_CONS = 3;
    public final static int G5_MAX_CONS = 3;
    public final static int G6_MAX_CONS = 3;
    public final static int G7_MAX_CONS = 3;
    public final static int G8_MAX_CONS = 2;

    //Constantes para o numero maximo de edificacions nun grupo
    public final static int MAXCONS_2 = 10; //de duas casillas
    public final static int MAXCONS_3 = 15; //de tres casillas

    //Constantes valores solares
    public final static float CASILLA_GRUPO1 = GRUPO1 / 2; //100
    public final static float CASILLA_GRUPO2 = GRUPO2 / 3; //110
    public final static float CASILLA_GRUPO3 = GRUPO3 / 3; //140
    public final static float CASILLA_GRUPO4 = GRUPO4 / 3; //180
    public final static float CASILLA_GRUPO5 = GRUPO5 / 3; //230
    public final static float CASILLA_GRUPO6 = GRUPO6 / 3; //300
    public final static float CASILLA_GRUPO7 = GRUPO7 / 3; //400
    public final static float CASILLA_GRUPO8 = GRUPO8 / 2; //750

    public final static float ACASILLA_GRUPO1 = (float) ((GRUPO1 / 2) * 0.1); //10
    public final static float ACASILLA_GRUPO2 = (float) ((GRUPO2 / 3) * 0.1); //11
    public final static float ACASILLA_GRUPO3 = (float) ((GRUPO3 / 3) * 0.1); //14
    public final static float ACASILLA_GRUPO4 = (float) ((GRUPO4 / 3) * 0.1); //18
    public final static float ACASILLA_GRUPO5 = (float) ((GRUPO5 / 3) * 0.1); //23
    public final static float ACASILLA_GRUPO6 = (float) ((GRUPO6 / 3) * 0.1); //30
    public final static float ACASILLA_GRUPO7 = (float) ((GRUPO7 / 3) * 0.1); //40
    public final static float ACASILLA_GRUPO8 = (float) ((GRUPO8 / 2) * 0.1); //75

    //Constantes respectivas a dinamica do xogo
    public final static float SUMA_SOLARES = GRUPO1 + GRUPO2 + GRUPO3 + GRUPO4 + GRUPO5 + GRUPO6 + GRUPO7 + GRUPO8; //5780
    public final static float VALOR_VOLTA = 250; //VALOR_VOLTA = SUMA_SOLARES / NSOLARES
    public final static float FORTUNA_INICIAL = 2000; //FORTUNA_INICIAL = SUMA_SOLARES / 3

    //Constantes valores transportes e servizos
    public final static float CASILLA_TRANSPORTES = VALOR_VOLTA; //250
    public final static float CASILLA_SERVIZOS = 185; //CASILLA_SERVIZOS = VALOR_VOLTA * (3f / 4f)

    //Constantes respectivas aos factores
    public final static float FACTOR_SERVIZOS = VALOR_VOLTA / 200; //1.25
    public final static float FACTOR_TRANSPORTE = VALOR_VOLTA; //250

    //Constante sair do carcere
    public final static float SAIR_CARCERE = 60; //SAIR_CARCERE = VALOR_VOLTA / 4

    //Constantes impostos
    public final static float IMPOSTO1 = VALOR_VOLTA;
    public final static float IMPOSTO2 = VALOR_VOLTA / 2; //125

    //Constantes caiza e sorte
    public final static float NCARTAS_SORTE = 14;
    public final static float NCARTAS_CAIXA = 10;

    // Colores de letraspublicfinal
    public final static String NEGRO = "\033[0;30m";
    public final static String VERMELLO = "\033[0;31m";
    public final static String VERDE = "\033[0;32m";
    public final static String AMARELO = "\033[0;33m";
    public final static String LARANXA = "\033[1;33m";
    public final static String AZUL = "\033[0;34m";
    public final static String ROSA = "\033[0;35m";
    public final static String CIAN = "\033[0;36m";
    public final static String GRIS = "\033[1;37m";
    public final static String RESET = "\u001B[0m";

}
