package xogo;

import xogadores.*;
import estrutura.*;
import Excepcions.*;

public interface Comando {

    public void aceptarTrato(String nomeTrato);

    //public void avanzar();
    public void cambiarModo(Avatar avatar);

    public void comprar(Propiedade propiedade, Xogador xogador) throws ComprarErr;

    public void describir(String tipo, String nome);

    public void edificar(Avatar avatar, String tipoEdificacion, int nEdificios) throws EdificarErr;

    public void eliminarTrato(String nomeTrato);

    //public void estatisticas();
    public void hipotecar(String nome, Xogador xogador, Xogador hipo) throws HipoPropNOn;

    public void deshipotecar(String nome, Xogador xogador, Xogador hipo) throws DeshipoPropNON;

    public void lanzarDados();

    public void listar(String comando1, String grupo) throws ListarErr;

    public void proponherTrato(String[] detalles) throws FalsaPropiedade, ErroSintaxe;

    public boolean rematarPartida();

    public void rematarTurno();

    //public void retroceder();
    public void sairCarcere();

    public void vender(String tipoEdificio, String casilla, String nEdificios);

    public void verTaboleiro();

    public void xogador();
    //public void Teletransport();

}
