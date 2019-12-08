package Excepcions;

public abstract class MonoExcep extends Exception{

    public MonoExcep(String mensaxe)
    {
        super("MonoGal: Excepción" + mensaxe);
    }
}
