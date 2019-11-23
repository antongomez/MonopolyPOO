package estrutura;

public abstract class Edificio {

    private String id;
    String tipo;
    private Solar solar;
    private float valor;
    
    public Edificio(Solar solar){
        this.solar=solar;

        switch (this.tipo)
        {
            case "Casa": this.id= tipo + "-" + solar.getPosicion() + solar.getNCasas() ;
                break;
            case "Hotel": this.id= tipo + "-" + solar.getPosicion() + solar.getNHoteis();
                break;
            case "Piscina": this.id= tipo + "-" + solar.getPosicion() + solar.getNPiscinas();
                break;
            case "Pista": this.id= tipo + "-" + solar.getPosicion() + solar.getNPistas();
                break;
        }

        switch (this.tipo)
        {
            case "Casa": this.valor = (float) (solar.getValor()*0.6);
                break;
            case "Hotel": this.valor = (float) (solar.getValor()*0.6);
                break;
            case "Piscina": this.valor = (float) (solar.getValor()*0.4);
                break;
            case "Pista":  this.valor = (float) (solar.getValor()*1.25);
                break;
        }




    }

    //public abstract void construir(Solar solar);
    public abstract void destruir(Solar solar);

    /*public Edificio(String num) {
        this.id = num;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }*/

    //Métodos
    String imprimirEdificio() {
        return id + "\n";
    }

    /*@Override
    public String toString(){
        return String = tipo+" "+id;
    }*/
}
