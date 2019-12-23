package errosExternos;

import estrutura.Casilla;

public class PropiedadesNonEdificios extends ErroExterno {

    public PropiedadesNonEdificios(String corGrupo) {
        super("O grupo de propiedades " + corGrupo + " non pode ser construído,"
                + " polo que non pode ter edificios.");
    }

    public PropiedadesNonEdificios(Casilla casilla) {
        super("Na casilla " + casilla.getNome() + " non se pode construír.");
    }
}
