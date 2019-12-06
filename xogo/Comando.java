package xogo;

import xogadores.*;
import estrutura.*;

public interface Comando {
    
    //public void avanzar();
    public void cambiarModo(Avatar avatar);
    public void comprar(Propiedade propiedade, Xogador xogador);
    public void describir(String tipo, String nome);
    public void edificar(Avatar avatar, String tipoEdificacion, int nEdificios);
    //public void estatisticas();
    //public void hipotecar(Propiedade propiedade, Xogador xogador);
    //public void deshipotecar(Propiedade propiedade);
    //public void lanzarDados();
    //public void listar();
    public boolean rematarPartida();
    //public void rematarTurno();
    //public void retroceder();
    //public void sairCarcere();
    //public void vender();
    public void verTaboleiro();
    //public void Teletransport();
     
}
