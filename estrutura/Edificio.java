package estrutura;

public abstract class Edificio {

    private String id;
    
    public Edificio(){
        
    }

    public Edificio(String num) {
        this.id = num;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    //Métodos
    String imprimirEdificio() {
        return id + "\n";
    }

    /*@Override
    public String toString(){
        return String = tipo+" "+id;
    }*/
}
