package xogo;

import errosExternos.NonPropiedade;
import xogadores.*;
import estrutura.*;
import Excepcions.*;
import errosInternos.*;
import errosExternos.*;
import errosExternos.errosExistencia.*;

public interface Comando {

    public void aceptarTrato(String nomeTrato)
            throws TratoNonExiste, TratoNonPropostoA, VariableNull, TratoNonAceptado;

    //public void avanzar();
    public void cambiarModo(Avatar avatar);

    public void comprar(Propiedade propiedade, Xogador xogador) throws PropNonComprable, CartosInsuficientes;

    public void crearXogador(String nomeXogador);
    
    public void describir(String tipo, String nome) throws ErroSintaxe, NonExiste;

    public void edificar(Avatar avatar, String tipoEdificacion, int nEdificios)
            throws CasillaNonEdificable, ErroInterno,
            NonPropiedade, CartosInsuficientes, NonPodeEdificar;

    public void eliminarTrato(String nomeTrato) throws TratoNonExiste;

    //public void estatisticas();
    public void hipotecar(String nome)
            throws PropiedadeNonPertenceA, PropNonComprable;

    public void deshipotecar(String nomePropiedade)
            throws CartosInsuficientes, PropiedadeNonPertenceA,
            PropiedadeNonHipotecada, PropNonComprable;

    public void lanzarDados() throws NonPodeLanzar, VariableNull;

    public void listar(String comando1, String grupo)
            throws ErroSintaxe, NonHaiTratos, GrupoNonExiste, PropiedadesNonEdificios;

    public void proponherTrato(String[] detalles)
            throws NonPropiedade, ErroSintaxe, VariableNull;

    public boolean rematarPartida();

    public void rematarTurno() throws VariableNull;

    //public void retroceder();
    public void sairCarcere() throws XogadorNonPreso, VariableNull;

    public void vender(String tipoEdificio, String casilla, String nEdificios)
            throws PropiedadesNonEdificios;

    public void verTaboleiro();

    public void xogador();
    //public void Teletransport();

}
