import java.io.Serializable;


/**
 * Intermediario para facilitar comunicacion entre el cliente y el servidor
 * 
 * @author Gabriel Chacón Alfaro
 */
public class Mensaje implements Serializable{

    private String expresion, identificador;
    private int tipo;

    public Mensaje (String expresion, int tipo, String identificador){
        this.expresion = expresion;
        this.tipo = tipo;
        this.identificador = identificador;
    }

    /**
     * Obtiene la expresion que contiene un Mensaje
     * @return Retorna la expresion dentro del mensaje
     */
    public String getExpresion(){
        return this.expresion;
    }

    /**
     * Obtiene el valor del tipo que contiene un Mensaje
     * @return Retorna el valor del tipo dentro del mensaje
     */
    public int getTipo(){
        return this.tipo;
    }

    /**
     * Obtiene la expresion que contiene un Mensaje
     * @return Retorna la expresion dentro del mensaje
     */
    public String getIdentificador(){
        return this.identificador;
    }

    /**
     * Obtiene el mensaje como un String separado por comas.
     * @return Retorna todo el mensaje como un String separado por comas.
     */
    public String toString(){
        return expresion + " , " + tipo + " , " + identificador;
    }

}