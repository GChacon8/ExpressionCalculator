import java.io.Serializable;

public class Mensaje implements Serializable{

    private String expresion, identificador;
    private int tipo;

    public Mensaje (String expresion, int tipo, String identificador){
        this.expresion = expresion;
        this.tipo = tipo;
        this.identificador = identificador;
    }

    public String getExpresion(){
        return this.expresion;
    }

    public int getTipo(){
        return this.tipo;
    }

    public String getIdentificador(){
        return this.identificador;
    }

    public String toString(){
        return expresion + " , " + tipo + " , " + identificador;
    }

}