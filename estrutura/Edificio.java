package estrutura;

public abstract class Edificio {

    private String id;
    private Solar solar;
    private float valor;

    public Edificio(Solar solar, String id, float factor) {
        this.id = id;
        this.solar = solar;
        this.valor = solar.getValor() * factor;
    }

    //Getters e Setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Solar getSolar() {
        return this.solar;
    }

    public void setSolar(Solar solar) {
        this.solar = solar;
    }

    public float getValor() {
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float vender() {
        for (int i = 0; i < solar.getEdificios().size(); i++)
        {
            if (solar.getEdificios().get(i).getId().equals(this.id))
                solar.getEdificios().remove(i);
        }
        return (float) (valor*0.5);
    }

    //Métodos
    //public abstract void construir(Solar solar);
    public abstract void destruir(Solar solar);


    /*@Override
    public String toString(){
        return String = tipo+" "+id;
    }*/
}
