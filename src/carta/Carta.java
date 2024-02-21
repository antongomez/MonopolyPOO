/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carta;

import xogadores.*;
import estrutura.*;
import java.util.ArrayList;
import errosExternos.*;
import static xogo.Xogo.consola;

public abstract class Carta {

    private String nome;

    public Carta(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract void accion(Xogador xogador, Taboleiro taboleiro,
            ArrayList<Xogador> xogadores) throws CartosInsuficientes;

    public void comprobarCasilla(Xogador xogador, Casilla casilla,
            ArrayList<Xogador> xogadores, Taboleiro taboleiro)
            throws CartosInsuficientes {

        Avatar avatar = xogador.getAvatar();

        if (casilla instanceof Solar) {
            Solar solar = (Solar) casilla;
            if (solar.getDono().getNome().equals("Banca")) {
                consola.imprimir("O solar " + solar.getNome() + " non ten "
                        + "dono, pódese comprar.\n");
            } else if (!solar.getExentos().isEmpty()) {
                if (solar.getExentos().get(xogador.getNome()) != null) {
                    consola.imprimir("O xogador " + xogador.getNome() + " está"
                            + " exento de pagar o aluguer.\n");
                }
            } else {
                if (!solar.getDono().equals(xogador)) {
                    float alquiler = solar.calculoAlquiler();
                    if (xogador.getFortuna() >= alquiler) {
                        xogador.modificarFortuna(-alquiler);
                        solar.getDono().modificarFortuna(alquiler);
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " pagulle ao xogador "
                                + solar.getDono().getNome() + " " + alquiler
                                + " GM. A súa fortuna actual é de "
                                + xogador.getFortuna() + "\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("alquiler/"
                                    + alquiler + "/" + solar.getDono().getNome()
                                    + "/" + solar.getNome());
                        } else if (xogador.getAvatar() instanceof Chapeu) {
                            ((Chapeu) avatar).sumarHistorial("alquiler/"
                                    + alquiler + "/" + solar.getDono().getNome()
                                    + "/" + solar.getNome());
                        }
                    } else {
                        throw new CartosInsuficientes(xogador.getNome());
                    }
                } else {
                    if (solar.frecuenciaVisita(xogadores.indexOf(xogador)) == 2) {
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " caeu dúas veces no solar "
                                + solar.getNome() + ". A partir de agora "
                                + "pode edificar.");
                    }
                }
            }
        } else if (casilla instanceof Transporte) {
            Transporte transporte = (Transporte) casilla;
            if (transporte.getDono().getNome().equals("Banca")) {
                consola.imprimir("A casilla de transporte "
                        + transporte.getNome() + " non ten dono, pódese"
                        + " comprar.\n");
            } else {
                if (!transporte.getDono().equals(xogador)) {
                    //Págase o dobre
                    float alquiler = 2 * transporte.calculoAlquiler();
                    if (xogador.getFortuna() >= alquiler) {
                        xogador.modificarFortuna(-alquiler);
                        transporte.getDono().modificarFortuna(alquiler);
                        consola.imprimir("O xogador " + xogador.getNome()
                                + " pagulle ao xogador " + transporte.getDono().getNome()
                                + " " + alquiler + " GM. A súa fortuna actual é "
                                + "de " + xogador.getFortuna() + "\n");

                        if (avatar instanceof Esfinxe) {
                            ((Esfinxe) avatar).sumarHistorial("alquiler/"
                                    + alquiler + "/" + transporte.getDono().getNome()
                                    + "/" + transporte.getNome());
                        } else if (xogador.getAvatar() instanceof Chapeu) {
                            ((Chapeu) avatar).sumarHistorial("alquiler/"
                                    + alquiler + "/" + transporte.getDono().getNome()
                                    + "/" + transporte.getNome());
                        }
                    } else {
                        throw new CartosInsuficientes(xogador.getNome());
                    }
                }
            }
        } else if (casilla instanceof Especial) {
            if (casilla.getNome().equals("IrCarcere")) {
                casilla = taboleiro.getCasilla("IrCarcere");
                avatar.getPosicion().eliminarAvatar(avatar);
                avatar.setPosicion(casilla);
                xogador.setEstadoPreso(4);
            }
        }
    }
}
