
/*Clase pai das excepcions AvatarNonExiste, XogadorNonExiste, CasillaNonExiste.*/
package errosExternos.errosExistencia;

import errosExternos.ErroExterno;

public class NonExiste extends ErroExterno {

    public NonExiste(String mensaxe) {
        super(mensaxe);
    }
}
