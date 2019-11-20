package estrutura;

public class Edificio {

    private String id;
    private String tipo;

    public Edificio(int num, String tipo) {
        if (tipo != null) {
            if (tipo.equals("casa") || tipo.equals("hotel") || 
                tipo.equals("pista") || tipo.equals("piscina")) {
                this.id = tipo + "-" + num;
                this.tipo = tipo;
            }
        }
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Métodos
    String imprimirEdificio() {
        return tipo + " " + id + "\n";
    }



    /*@Override
    public String toString(){
        return String = tipo+" "+id;
    }*/
}
