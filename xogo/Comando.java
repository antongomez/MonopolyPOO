package xogo;

import xogadores.*;
import estrutura.*;
import java.util.HashMap;

public interface Comando {

    //public void avanzar();
    public void cambiarModo(Avatar avatar);

    public void comprar(Propiedade propiedade, Xogador xogador);

    public void describir(String tipo, String nome);

    public void edificar(Avatar avatar, String tipoEdificacion, int nEdificios);

    //public void estatisticas();
    //public void hipotecar(Propiedade propiedade, Xogador xogador);
    //public void deshipotecar(Propiedade propiedade);
    public void lanzarDados(HashMap<String, Dado> dados);

    public void listar(String comando1, String grupo);

    public boolean rematarPartida();

    //public void rematarTurno();
    //public void retroceder();
    //public void sairCarcere();
    //public void vender();
    public void verTaboleiro();
    //public void Teletransport();

}
